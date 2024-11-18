plugins {
    id("pluginbase.spigot-conventions")
}

dependencies {
    api(project(":pluginbase-core:plugin"))
    api(project(":pluginbase-bukkit:commands-bukkit"))
    shadowApi(libs.bstats.bukkit)
    shadowApi(libs.configurate.hocon)
    implementation(project(":pluginbase-bukkit:permissions-bukkit"))
}
