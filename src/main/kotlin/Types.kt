enum class Type {
    Number,
    Operator,
    LParent,
    RParent,
    None
}

enum class OperatorType {
    Add,
    Subtract,
    Multiply,
    Divide,
    None
}

fun toOperandPoint(op: OperatorType): Int = when (op) {
    OperatorType.Add -> 0
    OperatorType.Subtract -> 0
    OperatorType.Multiply -> 1
    OperatorType.Divide -> 1
    OperatorType.None -> -1
}

