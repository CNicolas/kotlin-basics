package kebab

import kebab.Ingredient.*
import org.assertj.core.api.Assertions.assertThat
import org.testng.annotations.Test

class KebabDoubleCheese {
    @Test
    fun should_be_CHEESE_CHEESE_when_kebab_has_CHEESE_and_double_cheese() {
        val kebab = Kebab(listOf(GOUDA))
        val expected = listOf(GOUDA, GOUDA)

        kebab.doubleCheese()

        assertThat(kebab.getIngredients()).isEqualTo(expected)
    }

    @Test
    fun should_be_MEAT_CHEESE_CHEESE_when_kebab_has_MEAT_CHEESE_and_double_cheese() {
        val kebab = Kebab(listOf(HAM, GRUYERE))
        val expected = listOf(HAM, GRUYERE, GRUYERE)

        kebab.doubleCheese()

        assertThat(kebab.getIngredients()).isEqualTo(expected)
    }

    @Test
    fun should_be_CHEESE_CHEESE_SALAD_when_kebab_has_CHEESE_SALAD_and_double_cheese() {
        val kebab = Kebab(listOf(MOZZARELLA, SALAD))
        val expected = listOf(MOZZARELLA, MOZZARELLA, SALAD)

        kebab.doubleCheese()

        assertThat(kebab.getIngredients()).isEqualTo(expected)
    }

    @Test
    fun should_be_M_C_C_S_C_C_T_when_kebab_has_MEAT_CHEESE_SALAD_CHEESE_TOMATO_and_double_cheese() {
        val kebab = Kebab(listOf(CHICKEN, CHEDDAR, SALAD, GOUDA, TOMATO))
        val expected = listOf(CHICKEN, CHEDDAR, CHEDDAR, SALAD, GOUDA, GOUDA, TOMATO)

        kebab.doubleCheese()

        assertThat(kebab.getIngredients()).isEqualTo(expected)
    }
}