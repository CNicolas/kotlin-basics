package data.labyrinth

import java.util.*

class LabyrinthFactory {
    companion object {
        fun createRandomEmptyLabyrinthOfSize(size: Int): Labyrinth {
            if (size < 3) {
                throw IllegalArgumentException("Size must be at least 3")
            }

            val board = initBoardBorderWalls(size)

            val r = Random()
            val startLine = r.nextInt(size - 2) + 1
            val endCol = r.nextInt(size - 2) + 1

            board[startLine][0] = Case.START
            board[endCol][size - 1] = Case.END

            val labyrinth = Labyrinth(board)
            labyrinth.start = Coordinates(startLine, 0)
            labyrinth.end = Coordinates(endCol, size - 1)

            return labyrinth
        }

        fun createTestingLabyrinthWithObstacle(): Labyrinth {
            val size = 5

            val board = initBoardBorderWalls(size)

            board[1][0] = Case.START
            board[1][4] = Case.END
            board[1][2] = Case.WALL
            board[2][2] = Case.WALL

            val labyrinth = Labyrinth(board)
            labyrinth.start = Coordinates(1, 0)
            labyrinth.end = Coordinates(1, 4)

            return labyrinth
        }

        private fun initBoardBorderWalls(size: Int): Array<Array<Case>> {

            val board = Array(size, { Array(size, { Case.PATH }) })

            for (line in 0 until size) {
                for (col in 0 until size) {
                    when {
                        isFirstOrLast(line, size) -> board[line][col] = Case.WALL
                        isFirstOrLast(col, size) -> board[line][col] = Case.WALL
                        else -> board[line][col] = Case.PATH
                    }
                }
            }

            return board
        }

        private fun isFirstOrLast(index: Int, size: Int) = (index == 0 || index == size - 1)
    }
}