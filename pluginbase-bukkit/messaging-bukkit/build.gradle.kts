plugins {
    id("pluginbase.spigot-conventions")
}

dependencies {
    api(project(":pluginbase-core:messaging"))
    shadowApi(libs.configurate.core)
}
