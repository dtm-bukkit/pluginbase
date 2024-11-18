plugins {
    id("pluginbase.spigot-conventions")
}

dependencies {
    implementation(project(":pluginbase-core:testplugin"))
    implementation(project(":pluginbase-core:jdbc-spring"))
    implementation(project(":pluginbase-bukkit:plugin-bukkit"))
    implementation(libs.configurate.hocon)
    implementation(libs.h2.driver)

    testImplementation(libs.commons.io)
}
