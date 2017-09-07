package kebab

import kebab.Ingredient.ONION

class Kebab(private var ingredients: List<Ingredient>) {
    fun isVegetarian(): Boolean = !(hasMeat() || hasFish())

    fun isPescetarian(): Boolean = !hasMeat()

    fun doubleCheese() {
        val ingredientsWithDoubleCheese: MutableList<Ingredient> = ingredients.toMutableList()
        var indexDoubleCheesed = 0
        for (value in ingredients) {
            indexDoubleCheesed++
            if (value.ingredientType === IngredientType.CHEESE) {
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

    private fun hasMeat(): Boolean = ingredients.map { it.ingredientType }.contains(IngredientType.MEAT)
    private fun hasFish(): Boolean = ingredients.map { it.ingredientType }.contains(IngredientType.FISH)
}
