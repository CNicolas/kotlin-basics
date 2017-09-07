package kebab

enum class Ingredient(val ingredientType: IngredientType) {
    SALAD(IngredientType.VEGETABLE),
    TOMATO(IngredientType.VEGETABLE),
    ONION(IngredientType.VEGETABLE),
    POTATOE(IngredientType.VEGETABLE),
    CHEDDAR(IngredientType.CHEESE),
    GOUDA(IngredientType.CHEESE),
    GRUYERE(IngredientType.CHEESE),
    MOZZARELLA(IngredientType.CHEESE),
    HAM(IngredientType.MEAT),
    CHICKEN(IngredientType.MEAT),
    CHOPPED_STEAK(IngredientType.MEAT),
    SALMON(IngredientType.FISH),
    POLLOCK(IngredientType.FISH),
    EGG(IngredientType.EGGS),
    MUSHROOM(IngredientType.MUSHROOMS)
}