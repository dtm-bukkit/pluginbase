package pomxml

import groovy.util.Node
import groovy.util.NodeList

class Dependency(val node: Node) {

    private fun getNode(name: String): Node? {
        val nodes = (node[name] as NodeList?) ?: return null
        return nodes[0] as Node?
    }

    var groupId: String?
        get() = getNode("groupId")?.text()
        set(value) {
            getNode("groupId")?.setValue(value)
        }

    var artifactId: String?
        get() = getNode("artifactId")?.text()
        set(value) {
            getNode("artifactId")?.setValue(value)
        }

    var version: String?
        get() = getNode("version")?.text()
        set(value) {
            getNode("version")?.setValue(value)
        }

    var scope: String?
        get() = getNode("scope")?.text()
        set(value) {
            getNode("scope")?.setValue(value)
        }
}
