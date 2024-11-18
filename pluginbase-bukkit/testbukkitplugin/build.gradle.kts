plugins {
    id("pluginbase.spigot-conventions")
}

dependencies {
    implementation(project(":pluginbase-core:testplugin"))
    implementation(project(":pluginbase-core:jdbc-spring"))
    implementation(project(":pluginbase-bukkit:plugin-bukkit"))
    implementation(libs.bstats.bukkit)
    implementation(libs.spring.jdbc) {
        exclude("aopalliance", "aopalliance")
    }
    implementation(libs.configurate.hocon) {
        exclude("com.google.guava", "guava")
    }
    implementation(libs.h2.driver)

    testImplementation(libs.commons.io)
}
