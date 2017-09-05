package harrypotter

class Book(val name: HarryPotterBook) {
    val price: Int = 8
}

enum class HarryPotterBook {
    THE_PHILOSOPHERS_STONE,
    THE_CHAMBER_OF_SECRETS,
    THE_PRISONER_OF_AZKABAN,
    THE_GOBELET_OF_FIRE,
    THE_ORDER_OF_THE_PHENIX
}
