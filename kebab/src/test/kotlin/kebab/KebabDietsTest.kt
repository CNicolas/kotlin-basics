package kebab

import kebab.Ingredient.*
import org.assertj.core.api.Assertions.assertThat
import org.testng.annotations.Test

class KebabDietsTest {

    @Test
    fun should_be_vegetarian_when_kebab_has__only_salad() {
        val kebab = Kebab(listOf(SALAD))

        assertThat(kebab.isVegetarian()).isTrue()
    }

    @Test
    fun should_not_be_vegetarian_when_kebab_has_meat() {
        val kebab = Kebab(listOf(HAM, CHOPPED_STEAK))

        assertThat(kebab.isVegetarian()).isFalse()
    }

    @Test
    fun should_not_be_vegetarian_when_kebab_has_meat_among_other_ingredients() {
        val kebab = Kebab(listOf(CHEDDAR, SALAD, TOMATO, HAM))

        assertThat(kebab.isVegetarian()).isFalse()
    }

    @Test
    fun should_be_pescetarian_when_kebab_has_fish_among_other_ingredients() {
        val kebab = Kebab(listOf(MOZZARELLA, SALAD, TOMATO, SALMON))

        assertThat(kebab.isPescetarian()).isTrue()
    }
}