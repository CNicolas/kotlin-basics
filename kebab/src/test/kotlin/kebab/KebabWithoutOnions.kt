package kebab

import kebab.Ingredient.*
import org.assertj.core.api.Assertions.assertThat
import org.testng.annotations.Test

class KebabWithoutOnions {
    @Test
    fun should_be_MEAT_when_kebab_has_MEAT_ONION_and_without_onions() {
        val kebab = Kebab(listOf(MEAT, ONION))
        val expected = listOf(MEAT)

        kebab.withoutOnions()

        assertThat(kebab.getIngredients()).isEqualTo(expected)
    }

    @Test
    fun should_be_MEAT_SALAD_when_kebab_has_ONION_MEAT_ONION_ONION_SALAD_and_without_onions() {
        val kebab = Kebab(listOf(ONION, MEAT, ONION, ONION, SALAD))
        val expected = listOf(MEAT, SALAD)

        kebab.withoutOnions()

        assertThat(kebab.getIngredients()).isEqualTo(expected)
    }
}
