/*

algorithm: https://en.wikipedia.org/wiki/Shunting_yard_algorithm

*/

class Parser(nodes: List<Node>) {
    private val outputStack: MutableList<Node> = mutableListOf()
    private val operatorStack: MutableList<Node> = mutableListOf()
    private val nodeIterator: NodeIterator = NodeIterator(nodes)
    private lateinit var currentNode: Node

    init {
        this.next()
    }

    fun next() {
        currentNode = nodeIterator.next()
    }

    fun parse(): MutableList<Node> {
        while (currentNode.type != Type.None) {

            if (currentNode.type == Type.Number) {
                outputStack.add(currentNode)
            }
            if (currentNode.type == Type.Operator) {
                if (operatorStack.isEmpty()) {
                    operatorStack.add(currentNode)
                    next()
                    continue
                }

                while (
                    operatorStack.isNotEmpty() &&
                    operatorStack.last().type != Type.LParent &&
                    toOperandPoint(operatorStack.last().opType) >= toOperandPoint(currentNode.opType)
                ) {
                    val op2 = operatorStack.removeLast()
                    outputStack.add(op2)
                }
                operatorStack.add(currentNode)
            }
            if (currentNode.type == Type.LParent) {
                operatorStack.add(currentNode)
            }
            if (currentNode.type == Type.RParent) {
                while (operatorStack.last().type != Type.LParent) {
                    outputStack.add(operatorStack.removeLast())
                }
                operatorStack.removeLast()
            }
            next()

//            print("Output: ")
//            outputStack.forEach {
//                it.print()
//            }
//            println()
//            print("Operat: ")
//            operatorStack.reversed().forEach {
//                it.print()
//            }
//            println()

        }
        while (operatorStack.isNotEmpty()) {
            outputStack.add(operatorStack.removeLast())
        }

        return outputStack
    }
}
