package fizzbuzz

/**
 * Created by nicolas on 01/09/2017.
 */
class FizzBuzzLuckyWithReport : FizzBuzzLucky() {
    override fun transformList(numbers: List<Int>): String {
        val resList: List<String> = numbers.map { it: Int -> transformNumber(it) }

        var fizz: Int = 0
        var buzz: Int = 0
        var fizzbuzz: Int = 0
        var lucky: Int = 0
        var integer: Int = 0

        resList.forEach {
            if (it == "fizz") fizz++
            else if (it == "buzz") buzz++
            else if (it == "fizzbuzz") fizzbuzz++
            else if (it == "lucky") lucky++
            else integer++
        }

        var transformedString: String = resList.joinToString(" ")
        transformedString += concatenateReport(fizz, buzz, fizzbuzz, lucky, integer)

        return transformedString
    }

    private fun concatenateReport(fizz: Int, buzz: Int, fizzbuzz: Int, lucky: Int, integer: Int): String {
        var res: String = ""

        if (fizz > 0) res += "\nfizz: $fizz"
        if (buzz > 0) res += "\nbuzz: $buzz"
        if (fizzbuzz > 0) res += "\nfizzbuzz: $fizzbuzz"
        if (lucky > 0) res += "\nlucky: $lucky"
        if (integer > 0) res += "\ninteger: $integer"

        return res
    }

}