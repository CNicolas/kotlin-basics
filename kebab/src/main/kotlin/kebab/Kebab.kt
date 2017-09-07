package kebab

class Kebab(val ingredients: List<Ingredient>) {
    fun isVegetarian(): Boolean = !ingredients.contains(Ingredient.MEAT)
}