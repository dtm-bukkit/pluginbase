plugins {
    id("pluginbase.spigot-conventions")
}

dependencies {
    api(project(":pluginbase-core:commands"))
    api(project(":pluginbase-bukkit:messaging-bukkit"))
    api(project(":pluginbase-bukkit:minecraft-link-bukkit"))
}
