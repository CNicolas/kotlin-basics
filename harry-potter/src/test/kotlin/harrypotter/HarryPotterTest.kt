package harrypotter

import harrypotter.HarryPotterBook.*
import org.assertj.core.api.Assertions.assertThat
import org.testng.annotations.Test

class HarryPotterTest {
    @Test()
    fun each_book_should_cost_8() {
        assertThat(Book(THE_PHILOSOPHERS_STONE).price).isEqualTo(8)
        assertThat(Book(THE_CHAMBER_OF_SECRETS).price).isEqualTo(8)
        assertThat(Book(THE_PRISONER_OF_AZKABAN).price).isEqualTo(8)
        assertThat(Book(THE_GOBELET_OF_FIRE).price).isEqualTo(8)
        assertThat(Book(THE_ORDER_OF_THE_PHENIX).price).isEqualTo(8)
    }

    @Test()
    fun two_equal_books_should_cost_16() {
        val cart = Cart(listOf(Book(THE_PHILOSOPHERS_STONE), Book(THE_PHILOSOPHERS_STONE)))
        assertThat(cart.priceCart()).isEqualTo(16.0)
    }

    @Test()
    fun three_equal_books_should_cost_24() {
        val cart = Cart(listOf(Book(THE_PHILOSOPHERS_STONE), Book(THE_PHILOSOPHERS_STONE), Book(THE_PHILOSOPHERS_STONE)))
        assertThat(cart.priceCart()).isEqualTo(24.0)
    }

    @Test()
    fun two_differents_books_should_cost_5_percent_less() {
        val cart = Cart(listOf(Book(THE_PHILOSOPHERS_STONE), Book(THE_CHAMBER_OF_SECRETS)))
        assertThat(cart.priceCart()).isEqualTo(16 * 0.95)
    }

    @Test()
    fun three_differents_books_should_cost_10_percent_less() {
        val cart = Cart(listOf(Book(THE_PHILOSOPHERS_STONE),
                Book(THE_CHAMBER_OF_SECRETS),
                Book(THE_PRISONER_OF_AZKABAN)))
        assertThat(cart.priceCart()).isEqualTo(24 * 0.90)
    }

    @Test()
    fun four_differents_books_should_cost_15_percent_less() {
        val cart = Cart(listOf(Book(THE_PHILOSOPHERS_STONE),
                Book(THE_CHAMBER_OF_SECRETS),
                Book(THE_PRISONER_OF_AZKABAN),
                Book(THE_GOBELET_OF_FIRE)))
        assertThat(cart.priceCart()).isEqualTo(32 * 0.80)
    }

    @Test()
    fun five_differents_books_should_cost_25_percent_less() {
        val cart = Cart(listOf(Book(THE_PHILOSOPHERS_STONE),
                Book(THE_CHAMBER_OF_SECRETS),
                Book(THE_PRISONER_OF_AZKABAN),
                Book(THE_GOBELET_OF_FIRE),
                Book(THE_ORDER_OF_THE_PHENIX)))
        assertThat(cart.priceCart()).isEqualTo(40 * 0.75)
    }

    @Test()
    fun should_cost_51_20() {
        val cart = Cart(listOf(Book(THE_PHILOSOPHERS_STONE),
                Book(THE_PHILOSOPHERS_STONE),
                Book(THE_CHAMBER_OF_SECRETS),
                Book(THE_CHAMBER_OF_SECRETS),
                Book(THE_PRISONER_OF_AZKABAN),
                Book(THE_PRISONER_OF_AZKABAN),
                Book(THE_GOBELET_OF_FIRE),
                Book(THE_ORDER_OF_THE_PHENIX)))
        assertThat(cart.priceCart()).isEqualTo(51.20)
    }

    @Test()
    fun should_cost_29_60() {
        val cart = Cart(listOf(
                Book(THE_PHILOSOPHERS_STONE),
                Book(THE_PHILOSOPHERS_STONE),
                Book(THE_CHAMBER_OF_SECRETS),
                Book(THE_PRISONER_OF_AZKABAN)))
        assertThat(cart.priceCart()).isEqualTo(29.60)
    }

    @Test()
    fun should_cost_23_20() {
        val cart = Cart(listOf(
                Book(THE_PHILOSOPHERS_STONE),
                Book(THE_PHILOSOPHERS_STONE),
                Book(THE_CHAMBER_OF_SECRETS)))
        assertThat(cart.priceCart()).isEqualTo(23.20)
    }
}