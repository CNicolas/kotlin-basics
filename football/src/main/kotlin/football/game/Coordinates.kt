package football.game

data class Coordinates(val x: Double, val y: Double) {
    override fun toString(): String {
        return "($x, $y)"
    }
}