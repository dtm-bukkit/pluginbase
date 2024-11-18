plugins {
    id("pluginbase.java-conventions")
}

dependencies {
    api(project(":pluginbase-core:jdbc"))
    shadowApi(libs.spring.jdbc)
}
