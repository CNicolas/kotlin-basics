package fizzbuzz

/**
 * Created by nicolas on 01/09/2017.
 */
open class FizzBuzzLucky : FizzBuzz() {
    override fun transformNumber(number: Int): String {
        if (number.toString().contains('3')) {
            return "lucky"
        }
        return super.transformNumber(number)
    }
}