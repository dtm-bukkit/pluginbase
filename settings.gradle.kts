plugins {
    // Apply the foojay-resolver plugin to allow automatic download of JDKs
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.8.0"
}

rootProject.name = "pluginbase"

fun includeSubmodules(parentDir: File) {
    parentDir.listFiles()?.forEach {
        if (it.isDirectory && File(it, "build.gradle.kts").exists()) {
            include(":${parentDir.name}:${it.name}")
        }
    }
}

includeSubmodules(File(rootDir, "pluginbase-core"))
includeSubmodules(File(rootDir, "pluginbase-bukkit"))
