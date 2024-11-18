plugins {
    id("pluginbase.java-conventions")
}

dependencies {
    api(project(":pluginbase-core:logging"))
    shadowApi(libs.configurate.core)

    testImplementation(libs.configurate.hocon)
}

//findRootConfigs(configurations).forEach {
//    printConfiguration(it)
//    println()
//}
