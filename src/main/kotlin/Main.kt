import java.text.DecimalFormat

fun main() {
    while (true) {
        print(">> ")
        val input = readln()
        if (input == ".exit") return
        val tokens = Lexer(input).parse()
        val rpn = Parser(tokens).parse()
        val res = Calculate(rpn).calc() ?: continue
        println(DecimalFormat("#."+("#".repeat(15))).format(res))
    }
}