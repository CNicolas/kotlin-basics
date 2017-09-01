package fizzbuzz

import org.assertj.core.api.Assertions.assertThat
import org.testng.annotations.Test

class FizzBuzzLuckyWithReportTest {
    val fizzBuzzLuckyWithReport = FizzBuzzLuckyWithReport()

    @Test
    fun should_transform_multiples_of_three_too_fizz() {
        assertThat(fizzBuzzLuckyWithReport.transformList(listOf(1, 2, 3, 4, 6, 7, 8, 9)))
                .isEqualTo("1 2 lucky 4 fizz 7 8 fizz" +
                        "\nfizz: 2" +
                        "\nlucky: 1" +
                        "\ninteger: 5")
    }

    @Test
    fun should_transform_multiples_of_five_too_buzz() {
        assertThat(fizzBuzzLuckyWithReport.transformList(listOf(1, 2, 4, 5, 7, 8, 10)))
                .isEqualTo("1 2 4 buzz 7 8 buzz" +
                        "\nbuzz: 2" +
                        "\ninteger: 5")
    }

    @Test
    fun should_transform_multiples_of_fifteen_too_fizzbuzz() {
        assertThat(fizzBuzzLuckyWithReport.transformList(listOf(1, 7, 11, 13, 15, 17, 30)))
                .isEqualTo("1 7 11 lucky fizzbuzz 17 lucky" +
                        "\nfizzbuzz: 1" +
                        "\nlucky: 2" +
                        "\ninteger: 4")
    }

    @Test
    fun should_transform_list() {
        assertThat(fizzBuzzLuckyWithReport.transformList(listOf(1..20).flatten()))
                .isEqualTo("1 2 lucky 4 buzz fizz 7 8 fizz buzz 11 fizz lucky 14 fizzbuzz 16 17 fizz 19 buzz" +
                        "\nfizz: 4" +
                        "\nbuzz: 3" +
                        "\nfizzbuzz: 1" +
                        "\nlucky: 2" +
                        "\ninteger: 10")
    }
}