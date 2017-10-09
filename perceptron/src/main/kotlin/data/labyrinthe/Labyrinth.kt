package data.labyrinthe

import java.util.*

class Labyrinth(val size: Int) {
    private lateinit var board: Array<Array<Case>>
    var start: Coordinates
    var end: Coordinates

    init {
        initializeBoard()

        val r = Random()

        start = createOpening(r.nextInt(size - 2) + 1, 0)
        end = createOpening(r.nextInt(size - 2) + 1, size - 1)

        board[start.x][start.y] = Case.START
        board[end.x][end.y] = Case.END
    }

    constructor(size: Int, startX: Int, startY: Int, endX: Int, endY: Int) : this(size) {
        initializeBoard()

        start = createOpening(startX, startY)
        end = createOpening(endX, endY)

        board[start.x][start.y] = Case.START
        board[end.x][end.y] = Case.END
    }

    constructor(board: Array<Array<Case>>) : this(board.size) {
        if (board.size == board[0].size) {
            this.board = board
        }
    }

    fun getCase(coordinates: Coordinates) = board[coordinates.x][coordinates.y]

    fun setPlayerLocation(coordinates: Coordinates) {
        board[coordinates.x][coordinates.y] = Case.PLAYER
    }

    fun isWall(coordinates: Coordinates) = getCase(coordinates) == Case.WALL

    fun isInLabyrinth(coordinates: Coordinates) = coordinates.x in 1..(size - 1) && coordinates.y in 1..(size - 1)

    private fun initializeBoard() {
        if (size < 3) {
            throw IllegalArgumentException("Size must be at least 3")
        }

        val creatingBoard = Array(size, { Array(size, { Case.PATH }) })

        for (line in 0 until size) {
            for (col in 0 until size) {
                if (isFirstOrLast(line)) {
                    creatingBoard[line][col] = Case.WALL
                } else if (isFirstOrLast(col)) {
                    creatingBoard[line][col] = Case.WALL
                }
            }
        }

        board = creatingBoard
    }

    private fun createOpening(x: Int, y: Int): Coordinates {
        if (isOnEdge(x, y)) {
            board[x][y] = Case.PATH
        } else {
            throw IllegalArgumentException("Bad coordinates for opening : $x $y")
        }

        return Coordinates(x, y)
    }

    private fun isFirstOrLast(index: Int) = (index == 0 || index == size - 1)

    private fun isOnEdge(x: Int, y: Int) = isFirstOrLast(x) || isFirstOrLast(y)

    override fun toString(): String {
        var res = ""

        board.forEach { line ->
            line.forEach { case -> res += case.toString() }
            res += "\n"
        }

        return res
    }


}