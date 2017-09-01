package fizzbuzz

import org.assertj.core.api.Assertions.assertThat
import org.testng.annotations.Test

class FizzBuzzTest {
    val fizzBuzz = FizzBuzz()

    @Test
    fun should_return_fizz_for_multiple_of_three() {
        assertThat(fizzBuzz.transformNumber(6)).isEqualTo("fizz")
    }

    @Test
    fun should_return_buzz_for_multiple_of_five() {
        assertThat(fizzBuzz.transformNumber(10)).isEqualTo("buzz")
    }

    @Test
    fun should_return_fizzbuzz_for_multiple_of_fifteen() {
        assertThat(fizzBuzz.transformNumber(30)).isEqualTo("fizzbuzz")
    }

    @Test
    fun should_transform_multiples_of_three_too_fizz() {
        assertThat(fizzBuzz.transformList(listOf(1, 2, 3, 4, 6, 7, 8, 9)))
                .isEqualTo("1 2 fizz 4 fizz 7 8 fizz")
    }

    @Test
    fun should_transform_multiples_of_five_too_buzz() {
        assertThat(fizzBuzz.transformList(listOf(1, 2, 4, 5, 7, 8, 10)))
                .isEqualTo("1 2 4 buzz 7 8 buzz")
    }

    @Test
    fun should_transform_multiples_of_fifteen_too_fizzbuzz() {
        assertThat(fizzBuzz.transformList(listOf(1, 7, 11, 13, 15, 17, 30)))
                .isEqualTo("1 7 11 13 fizzbuzz 17 fizzbuzz")
    }

    @Test
    fun should_transform_list() {
        assertThat(fizzBuzz.transformList(listOf(1..20).flatten()))
                .isEqualTo("1 2 fizz 4 buzz fizz 7 8 fizz buzz 11 fizz 13 14 fizzbuzz 16 17 fizz 19 buzz")
    }
}