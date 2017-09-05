package stringcalculator

import org.assertj.core.api.Assertions.assertThat
import org.testng.annotations.DataProvider
import org.testng.annotations.Test

class StringCalculatorTest {
    @DataProvider(name = "PairProvider")
    fun pairsProvider(): Array<Array<Any>> =
            arrayOf(arrayOf("", 0),
                    arrayOf("1", 1),
                    arrayOf("2", 2),
                    arrayOf("1,2", 3),
                    arrayOf("2,2", 4),
                    arrayOf("1,2,3", 6),
                    arrayOf("1\n2/3", 6),
                    arrayOf("1\n2/3;4", 10),
                    arrayOf("1\n2/3;-4", 2),
                    arrayOf("1\n,2", 3))


    @Test(dataProvider = "PairProvider")
    fun should_return_sum_of_numbers_in_the_string(entryString: String, expectedOutput: Int) {
        val calculator = StringCalculator()

        assertThat(calculator.add(entryString)).isEqualTo(expectedOutput)
    }
}
