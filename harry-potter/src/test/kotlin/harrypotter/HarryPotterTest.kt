package harrypotter

import org.assertj.core.api.Assertions.assertThat
import org.testng.annotations.Test

class HarryPotterTest {
    @Test()
    fun each_book_should_cost_8_euros() {
        assertThat(Book(HarryPotterBook.THE_PHILOSOPHERS_STONE).price).isEqualTo(8)
        assertThat(Book(HarryPotterBook.THE_CHAMBER_OF_SECRETS).price).isEqualTo(8)
        assertThat(Book(HarryPotterBook.THE_PRISONER_OF_AZKABAN).price).isEqualTo(8)
        assertThat(Book(HarryPotterBook.THE_GOBELET_OF_FIRE).price).isEqualTo(8)
        assertThat(Book(HarryPotterBook.THE_ORDER_OF_THE_PHENIX).price).isEqualTo(8)
    }
}