package data.labyrinthe

enum class Case(private val representation: String) {
    PATH(" "),
    WALL("@");

    override fun toString(): String {
        return this.representation
    }
}