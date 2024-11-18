import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    id("pluginbase.java-conventions")
}

dependencies {
    relocatedImplementation(libs.gentyref)
    api(project(":pluginbase-core:messaging"))
    shadowApi(libs.configurate.core)
    shadow(libs.configurate.hocon)
    shadow(libs.configurate.json)
    shadow(libs.configurate.gson)
    shadow(libs.configurate.yaml)
}

tasks.named("shadowJar", ShadowJar::class) {
    relocate("com.googlecode.gentyref", "pluginbase.gentyref")
}
