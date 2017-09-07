package kebab

import kebab.Ingredient.*
import org.assertj.core.api.Assertions.assertThat
import org.testng.annotations.Test

class KebabDoubleCheese {
    @Test
    fun should_be_CHEESE_CHEESE_when_kebab_has_CHEESE_and_double_cheese() {
        val kebab = Kebab(listOf(CHEESE))
        val expected = listOf(CHEESE, CHEESE)

        kebab.doubleCheese()

        assertThat(kebab.getIngredients()).isEqualTo(expected)
    }

    @Test
    fun should_be_MEAT_CHEESE_CHEESE_when_kebab_has_MEAT_CHEESE_and_double_cheese() {
        val kebab = Kebab(listOf(MEAT, CHEESE))
        val expected = listOf(MEAT, CHEESE, CHEESE)

        kebab.doubleCheese()

        assertThat(kebab.getIngredients()).isEqualTo(expected)
    }

    @Test
    fun should_be_CHEESE_CHEESE_SALAD_when_kebab_has_CHEESE_SALAD_and_double_cheese() {
        val kebab = Kebab(listOf(CHEESE, SALAD))
        val expected = listOf(CHEESE, CHEESE, SALAD)

        kebab.doubleCheese()

        assertThat(kebab.getIngredients()).isEqualTo(expected)
    }

    @Test
    fun should_be_M_C_C_S_C_C_T_when_kebab_has_MEAT_CHEESE_SALAD_CHEESE_TOMATO_and_double_cheese() {
        val kebab = Kebab(listOf(MEAT, CHEESE, SALAD, CHEESE, TOMATO))
        val expected = listOf(MEAT, CHEESE, CHEESE, SALAD, CHEESE, CHEESE, TOMATO)

        kebab.doubleCheese()

        assertThat(kebab.getIngredients()).isEqualTo(expected)
    }
}