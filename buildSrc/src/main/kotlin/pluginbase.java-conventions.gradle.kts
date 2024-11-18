import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar
import groovy.util.Node
import groovy.util.NodeList

val libs = the<org.gradle.accessors.dm.LibrariesForLibs>()

plugins {
    `java-library`
    `maven-publish`
    id("com.gradleup.shadow")
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

group = "com.dumptruckman.minecraft.pluginbase"
version = System.getenv("GITHUB_VERSION") ?: "local"

val shadowApi: NamedDomainObjectProvider<Configuration> by configurations.registering {
    isCanBeResolved = true
}
val relocatedImplementation: NamedDomainObjectProvider<Configuration> by configurations.registering {
    isCanBeResolved = true
}

configurations.apiElements {
    extendsFrom(shadowApi.get())
}
configurations.compileClasspath {
    extendsFrom(configurations.shadow.get())
    extendsFrom(relocatedImplementation.get())
}
configurations.mainSourceElements {
    extendsFrom(relocatedImplementation.get())
}
configurations.runtimeClasspath {
    extendsFrom(relocatedImplementation.get())
}
configurations.shadow {
    extendsFrom(shadowApi.get())
}
configurations.shadowRuntimeElements {
    extendsFrom(configurations.shadow.get())
}
configurations.testImplementation {
    extendsFrom(configurations.shadow.get())
    extendsFrom(relocatedImplementation.get())
}

repositories {
    mavenLocal()
    mavenCentral()
}

tasks.named<Test>("test") {
    useJUnitPlatform()
    jvmArgs(
        "--add-opens=java.base/java.lang=ALL-UNNAMED",
        "--add-opens=java.base/java.lang.reflect=ALL-UNNAMED",
        "--add-opens=java.base/java.io=ALL-UNNAMED",
        "--add-opens=java.base/java.util=ALL-UNNAMED",
        "--add-opens=java.logging/java.util.logging=ALL-UNNAMED",
        "--add-opens=java.xml/jdk.xml.internal=ALL-UNNAMED",
    )
}

dependencies {
    annotationProcessor(libs.intellij.annotations)

    shadow(libs.intellij.annotations)

    testCompileOnly(libs.junit.legacy)

    testImplementation(libs.junit.jupiter)
    testImplementation(libs.bundles.mocking)

    testRuntimeOnly(libs.junit.platform)
    testRuntimeOnly(libs.junit.vintage)
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            from(components["shadow"])

            artifact(tasks.shadowJar.get())

            pom.withXml {
                val pom = pomxml.Pom(asNode())
                pom.dependencies.filter { dependency ->
                    dependency.scope == "runtime"
                }.forEach {dependency ->
                    dependency.scope = if (dependency.artifactId == libs.spigot.api.get().name) "provided" else "compile"
                }
            }
        }
    }
    repositories {
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/dumptruckman/pluginbase")
            credentials {
                username = System.getenv("GITHUB_ACTOR")
                password = System.getenv("GITHUB_TOKEN")
            }
        }
    }
}


tasks.named("shadowJar", ShadowJar::class) {
    archiveClassifier.set("")
}

tasks.named("build") {
    dependsOn(tasks.shadowJar)
}

tasks.named("jar") {
    dependsOn(tasks.shadowJar)
    enabled = false
}
