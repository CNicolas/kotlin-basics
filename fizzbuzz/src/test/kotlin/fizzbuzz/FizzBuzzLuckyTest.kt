package fizzbuzz

import org.assertj.core.api.Assertions.assertThat
import org.testng.annotations.Test

class FizzBuzzLuckyTest {
    val fizzBuzzLucky = FizzBuzzLucky()

    @Test
    fun should_return_fizz_for_multiple_of_three() {
        assertThat(fizzBuzzLucky.transformNumber(6)).isEqualTo("fizz")
    }

    @Test
    fun should_return_buzz_for_multiple_of_five() {
        assertThat(fizzBuzzLucky.transformNumber(10)).isEqualTo("buzz")
    }

    @Test
    fun should_return_fizzbuzz_for_multiple_of_fifteen() {
        assertThat(fizzBuzzLucky.transformNumber(45)).isEqualTo("fizzbuzz")
    }

    @Test
    fun should_return_lucy_when_number_contains_3() {
        assertThat(fizzBuzzLucky.transformNumber(3)).isEqualTo("lucky")
        assertThat(fizzBuzzLucky.transformNumber(13)).isEqualTo("lucky")
    }

    @Test
    fun should_transform_multiples_of_three_too_fizz() {
        assertThat(fizzBuzzLucky.transformList(listOf(1, 2, 3, 4, 6, 7, 8, 9)))
                .isEqualTo("1 2 lucky 4 fizz 7 8 fizz")
    }

    @Test
    fun should_transform_multiples_of_five_too_buzz() {
        assertThat(fizzBuzzLucky.transformList(listOf(1, 2, 4, 5, 7, 8, 10)))
                .isEqualTo("1 2 4 buzz 7 8 buzz")
    }

    @Test
    fun should_transform_multiples_of_fifteen_too_fizzbuzz() {
        assertThat(fizzBuzzLucky.transformList(listOf(1, 7, 11, 13, 15, 17, 30)))
                .isEqualTo("1 7 11 lucky fizzbuzz 17 lucky")
    }

    @Test
    fun should_transform_list() {
        assertThat(fizzBuzzLucky.transformList(listOf(1..20).flatten()))
                .isEqualTo("1 2 lucky 4 buzz fizz 7 8 fizz buzz 11 fizz lucky 14 fizzbuzz 16 17 fizz 19 buzz")
    }
}