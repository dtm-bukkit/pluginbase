val libs = the<org.gradle.accessors.dm.LibrariesForLibs>()

plugins {
    id("pluginbase.java-conventions")
}

repositories {
    maven {
        name = "spigotmc"
        url = uri("https://hub.spigotmc.org/nexus/content/groups/public/")
    }
}

dependencies {
    configurations.getByName("shadowApi")(libs.spigot.api)
}
