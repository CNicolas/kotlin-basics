package labyrinth.board

class Coordinates(val x: Int, val y: Int) {
    fun up() = Coordinates(x - 1, y)
    fun right() = Coordinates(x, y + 1)
    fun down() = Coordinates(x + 1, y)
    fun left() = Coordinates(x, y - 1)

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Coordinates

        if (x != other.x) return false
        if (y != other.y) return false

        return true
    }

    override fun hashCode(): Int {
        var result = x
        result = 31 * result + y
        return result
    }

    override fun toString(): String {
        return "[$x, $y]"
    }
}