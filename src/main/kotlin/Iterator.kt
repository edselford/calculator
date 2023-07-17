open class Iterator {
    protected var pos: Int = -1
}


class InputIterator(private val input: String): Iterator() {
    fun next(): Char {
        pos++
        if (this.pos < input.length) return input[pos]
        return 0.toChar()
    }
}


class NodeIterator(private val nodes: List<Node>): Iterator() {
    fun next(): Node {
        pos++
        if(this.pos < nodes.size) return nodes[pos]
        return Node(Type.None)
    }
}