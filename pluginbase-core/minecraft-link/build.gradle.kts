plugins {
    id("pluginbase.java-conventions")
}

dependencies {
    api(project(":pluginbase-core:permissions"))
    api(project(":pluginbase-core:messaging"))
}
