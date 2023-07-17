import kotlin.properties.Delegates

class Lexer(strInput: String) {
    private var input: InputIterator = InputIterator(strInput)
    private var currentChar by Delegates.notNull<Char>()
    private var nodes: MutableList<Node> = mutableListOf()

    init {
        this.next()
    }

    fun next() {
        currentChar = input.next()
    }


    fun parse(): List<Node> {
        while (currentChar.code != 0) {
            if (currentChar.code == 9 /* tab */ || currentChar.code == 32 /* space */) next()
            else if (isNumber(currentChar)) getNumber()
            /*  +,-,*,/  */
            else if (intArrayOf(43, 45, 42, 47).contains(currentChar.code)) getOperator()
            // (
            else if (currentChar.code == 40) {
                nodes.add(Node(Type.LParent))
                next()
            }
            // )
            else if (currentChar.code == 41) {
                nodes.add(Node(Type.RParent))
                next()
            }
            else {
                println("Syntax error")
                return emptyList()
            }
        }

        return nodes.toList()
    }

    private fun getOperator() {
        when (currentChar.code) {
            43 -> nodes.add(Node(Type.Operator, opType = OperatorType.Add))
            45 -> nodes.add(Node(Type.Operator, opType = OperatorType.Subtract))
            42 -> nodes.add(Node(Type.Operator, opType = OperatorType.Multiply))
            47 -> nodes.add(Node(Type.Operator, opType = OperatorType.Divide))
        }
        next()
    }

    private fun getNumber() {
        var num = ""
        while (true) {
            if (isNumber(currentChar) || currentChar.code == 46 /* '.' */) num += currentChar
            else break
            next()
        }
        nodes.add(Node(Type.Number, num))
    }

}

fun isNumber(character: Char): Boolean {
    // char code of 0-9
    for (i in 46..57) {
        if (i != 47 && character.code == i) return true
    }
    return false
}