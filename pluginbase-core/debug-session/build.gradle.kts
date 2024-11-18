plugins {
    id("pluginbase.java-conventions")
}

dependencies {
    api(project(":pluginbase-core:messaging"))

    testImplementation(libs.configurate.core)
}
