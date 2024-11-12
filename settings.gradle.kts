plugins {
    // Apply the foojay-resolver plugin to allow automatic download of JDKs
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.8.0"
}

rootProject.name = "pluginbase"

include("pluginbase-core", "pluginbase-bukkit")

fun includeSubmodulesRecursively(moduleDir: File, parentModuleName: String) {
    moduleDir.listFiles()?.forEach {
        if (it.isDirectory()) {
            if (File(it, "build.gradle.kts").exists()) {
                include("$parentModuleName:${moduleDir.name}:${it.name}")
            }
            if (!File(it, "src").exists()) {
                includeSubmodulesRecursively(it, "$parentModuleName:${moduleDir.name}")
            }
        }
    }
}

includeSubmodulesRecursively(File(rootDir, "pluginbase-core"), "")
includeSubmodulesRecursively(File(rootDir, "pluginbase-bukkit"), "")
