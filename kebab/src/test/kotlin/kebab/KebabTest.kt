package kebab

import kebab.Ingredient.*
import org.assertj.core.api.Assertions.assertThat
import org.testng.annotations.Test

class KebabTest {

    @Test
    fun should_return_true_when_kebab_has__only_salad() {
        val kebab = Kebab(listOf(SALAD))

        assertThat(kebab.isVegetarian()).isTrue()
    }

    @Test
    fun should_return_false_when_kebab_has_meat() {
        val kebab = Kebab(listOf(MEAT))

        assertThat(kebab.isVegetarian()).isFalse()
    }

    @Test
    fun should_return_false_when_kebab_has_meat_among_other_ingredients() {
        val kebab = Kebab(listOf(CHEESE, SALAD, TOMATO, MEAT))

        assertThat(kebab.isVegetarian()).isFalse()
    }
}