package data.labyrinthe

import java.util.*

class Labyrinthe(val size: Int) {
    private lateinit var board: Array<Array<Case>>

    init {
        initializeBoard()

        val r = Random()

        createOpening(r.nextInt(size - 2) + 1, 0)
        createOpening(r.nextInt(size - 2) + 1, size - 1)
    }

    constructor(size: Int, startX: Int, startY: Int, endX: Int, endY: Int) : this(size) {
        initializeBoard()

        createOpening(startX, startY)
        createOpening(endX, endY)
    }

    fun getCaseByCoordinates(x: Int, y: Int) = board[x][y]

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

    private fun createOpening(x: Int, y: Int) {
        if (isOnEdge(x, y)) {
            board[x][y] = Case.PATH
        } else {
            throw IllegalArgumentException("Bad coordinates for opening : $x $y")
        }
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