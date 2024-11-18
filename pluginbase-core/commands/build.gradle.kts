plugins {
    id("pluginbase.java-conventions")
}

dependencies {
    api(project(":pluginbase-core:messaging"))
    api(project(":pluginbase-core:permissions"))
    api(project(":pluginbase-core:minecraft-link"))
    implementation(project(":pluginbase-core:utilities"))
    shadowApi(libs.configurate.core)
}
