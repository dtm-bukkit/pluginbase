import org.gradle.api.artifacts.Configuration
import org.gradle.api.artifacts.ConfigurationContainer

fun findRootConfigs(configurations: ConfigurationContainer): List<Configuration> {
    val allChildren = mutableSetOf<Configuration>()

    configurations.forEach {
        allChildren.addAll(it.extendsFrom)
    }

    return configurations.filter { !allChildren.contains(it) }
}

fun printConfiguration(configuration: Configuration, indent: Int = 0) {
    println("${" ".repeat(indent)}:${configuration.name}")
    configuration.extendsFrom.forEach {
        printConfiguration(it, indent + 2)
    }
}
