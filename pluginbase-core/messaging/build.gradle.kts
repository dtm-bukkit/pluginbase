plugins {
    id("pluginbase.java-conventions")
}

dependencies {
    api(project(":pluginbase-core:messages"))

    testImplementation(libs.configurate.core)
}
