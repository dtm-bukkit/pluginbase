plugins {
    id("pluginbase.java-conventions")
}

dependencies {
    api(project(":pluginbase-core:jdbc"))
    api(libs.spring.jdbc) {
        exclude(group = "aopalliance", module = "aopalliance")
    }
}
