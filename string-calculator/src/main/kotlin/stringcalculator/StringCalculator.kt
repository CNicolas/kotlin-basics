package stringcalculator

class StringCalculator {
    fun add(numbers: String): Int {
        return numbers.split(',')
                .map { it -> if (it !== "") it.toInt() else 0 }
                .reduce { acc, i -> acc + i }
    }
}