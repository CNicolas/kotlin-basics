package stringcalculator

import java.util.regex.Pattern

class StringCalculator {
    fun add(numbers: String): Int {
        return numbers.split(Pattern.compile("[,;/_\\n]+"))
                .map { it -> if (it !== "") it.toInt() else 0 }
                .reduce { acc, i -> acc + i }
    }
}