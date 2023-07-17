import java.math.BigDecimal
import java.math.MathContext

open class Node(
    val type: Type,
    val value: String = "0",
    val opType: OperatorType = OperatorType.None
) {
    fun print() {
        print(
            if (type == Type.Operator)
                "{$opType}"
            else if (type == Type.LParent || type == Type.RParent)
                "{$type}"
            else "{$type: $value}"
        )
    }

    fun calc(left: String, right: String): Double {
        return when (opType) {
            OperatorType.Add -> BigDecimal(left).add(BigDecimal(right)).toDouble()
            OperatorType.Subtract -> BigDecimal(left).minus(BigDecimal(right)).toDouble()
            OperatorType.Multiply -> BigDecimal(left).times(BigDecimal(right)).toDouble()
            OperatorType.Divide -> BigDecimal(left).divide(BigDecimal(right), MathContext.DECIMAL128).toDouble()
            OperatorType.None -> 0.0
        }
    }
}

