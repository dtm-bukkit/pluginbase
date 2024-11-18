plugins {
    id("pluginbase.spigot-conventions")
}

dependencies {
    api(project(":pluginbase-core:utilities"))
    api(project(":pluginbase-core:minecraft-link"))
    implementation(project(":pluginbase-core:logging"))
}
