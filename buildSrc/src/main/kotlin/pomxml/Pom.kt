package pomxml

import groovy.util.Node
import groovy.util.NodeList

class Pom(val node: Node) {

    val dependencies: List<Dependency>
        get() = ((node["dependencies"] as NodeList)[0] as Node).children().map { Dependency(it as Node) }
}
