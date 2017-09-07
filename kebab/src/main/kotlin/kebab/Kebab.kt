package kebab

import kebab.Ingredient.FISH
import kebab.Ingredient.MEAT

class Kebab(val ingredients: List<Ingredient>) {
    fun isVegetarian(): Boolean = !(ingredients.contains(MEAT) || ingredients.contains(FISH))

    fun isPescetarian(): Boolean = !ingredients.contains(MEAT)
}