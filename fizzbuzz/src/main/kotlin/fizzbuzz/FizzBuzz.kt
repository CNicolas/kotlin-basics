package fizzbuzz

/**
 * Created by nicolas on 01/09/2017.
 */
open class FizzBuzz {
    open fun transformList(numbers: List<Int>): String {
        val resList: List<String> = numbers.map { it: Int -> transformNumber(it) }
        return resList.joinToString(" ")
    }

    open fun transformNumber(number: Int): String {
        if (isFizzBuzzable(number)) {
            return "fizzbuzz"
        } else if (isBuzzable(number)) {
            return "buzz"
        } else if (isFizzable(number)) {
            return "fizz"
        } else {
            return "$number"
        }
    }

    protected fun isFizzable(number: Int) = number % 3 == 0

    protected  fun isBuzzable(number: Int) = number % 5 == 0

    protected fun isFizzBuzzable(number: Int) = number % 15 == 0
}
