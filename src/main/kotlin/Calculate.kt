class Calculate(nodes: List<Node>) {
    private val stack: MutableList<Node> = mutableListOf()
    private val nodeIterator: NodeIterator = NodeIterator(nodes)
    private lateinit var currentNode: Node

    init {
        next()
    }

    fun next() {
        currentNode = nodeIterator.next()
    }

    fun calc(): Double? {
        while (currentNode.type != Type.None) {
            if (currentNode.type == Type.Number) stack.add(currentNode)
            if (currentNode.type == Type.Operator) {
                val right = stack.removeLast()
                val left:Node = if (stack.isEmpty())
                    Node(Type.Number, "0")
                else stack.removeLast()

                val result = currentNode.calc(left.value, right.value)
                stack.add(Node(Type.Number, result.toString()))
            }
            next()
        }
        if (stack.isEmpty()) return null
        return stack.last().value.toDouble()
    }
}