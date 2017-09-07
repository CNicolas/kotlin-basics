package kebab

import kebab.Ingredient.*

class Kebab(private var ingredients: List<Ingredient>) {
    fun isVegetarian(): Boolean = !(ingredients.contains(MEAT) || ingredients.contains(FISH))

    fun isPescetarian(): Boolean = !ingredients.contains(MEAT)

    fun doubleCheese() {
        val ingredientsWithDoubleCheese: MutableList<Ingredient> = ingredients.toMutableList()
        var indexDoubleCheesed = 0
        for ((index, value) in ingredients.withIndex()) {
            indexDoubleCheesed++
            if (value === CHEESE) {
                ingredientsWithDoubleCheese.add(indexDoubleCheesed, value)
                indexDoubleCheesed++
            }
        }

        ingredients = ingredientsWithDoubleCheese
    }

    fun withoutOnions() {
        val ingredientsWithouOnions = ingredients.toMutableList()
        ingredientsWithouOnions.removeAll(listOf(ONION))

        ingredients = ingredientsWithouOnions
    }

    fun getIngredients() = ingredients

    fun getIngredientsAsString(): String = ingredients.toString()

    override fun toString(): String {
        return "Kebab(ingredients=$ingredients)"
    }
}
