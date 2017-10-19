package labyrinth.board

class Labyrinth(private val board: Array<Array<Case>>) {
    val size = board.size
    lateinit var start: Coordinates
    lateinit var end: Coordinates

    fun setPlayerLocation(coordinates: Coordinates) {
        board[coordinates.x][coordinates.y] = Case.PLAYER
    }

    fun getCase(coordinates: Coordinates) = board[coordinates.x][coordinates.y]

    fun isWall(coordinates: Coordinates) = getCase(coordinates) == Case.WALL

    fun isInLabyrinth(coordinates: Coordinates) = coordinates.x in 1..(size - 1) && coordinates.y in 1..(size - 1)

    override fun toString(): String {
        var res = ""

        board.forEach { line ->
            line.forEach { case -> res += case.toString() }
            res += "\n"
        }

        return res
    }
}