plugins {
    id("pluginbase.java-conventions")
}

dependencies {
    api(project(":pluginbase-core:commands"))
    api(project(":pluginbase-core:debug-session"))
    api(project(":pluginbase-core:jdbc"))
    implementation(project(":pluginbase-core:utilities"))
    shadow(libs.configurate.yaml)
    shadow(libs.configurate.gson)
    shadow(libs.configurate.json)
    shadow(libs.configurate.hocon)
}
