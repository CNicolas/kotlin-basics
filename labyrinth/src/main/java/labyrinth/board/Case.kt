package labyrinth.board

enum class Case(private val representation: String) {
    PATH(" "),
    WALL("@"),
    PLAYER("¤"),
    START("S"),
    END("E");

    override fun toString(): String {
        return this.representation
    }
}