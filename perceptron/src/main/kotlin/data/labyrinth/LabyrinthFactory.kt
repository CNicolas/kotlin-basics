package data.labyrinth

import data.labyrinth.board.Case
import data.labyrinth.board.Case.*
import data.labyrinth.board.Coordinates
import data.labyrinth.board.Labyrinth
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

            board[startLine][0] = START
            board[endCol][size - 1] = END

            val labyrinth = Labyrinth(board)
            labyrinth.start = Coordinates(startLine, 0)
            labyrinth.end = Coordinates(endCol, size - 1)

            return labyrinth
        }

        fun createTestingLabyrinthWithoutObstacles(): Labyrinth {
            val size = 5

            val board = initBoardBorderWalls(size)

            board[3][0] = START
            board[1][4] = END

            val labyrinth = Labyrinth(board)
            labyrinth.start = Coordinates(3, 0)
            labyrinth.end = Coordinates(1, 4)

            return labyrinth
        }

        fun createTestingLabyrinthWithObstacle(): Labyrinth {
            val size = 5

            val board = initBoardBorderWalls(size)

            board[1][0] = START
            board[1][4] = END
            board[1][2] = WALL
            board[2][2] = WALL

            val labyrinth = Labyrinth(board)
            labyrinth.start = Coordinates(1, 0)
            labyrinth.end = Coordinates(1, 4)

            return labyrinth
        }

        fun createBigTestingLabyrinthWithObstacle(): Labyrinth {
            val size = 10
            val start = Coordinates(2, 0)
            val end = Coordinates(9, 7)


            val board = initBoardBorderWalls(size)

            board[start.x][start.y] = START
            board[end.x][end.y] = END
            board[2][2] = WALL
            board[3][1] = WALL
            board[3][2] = WALL
            board[4][3] = WALL
            board[4][4] = WALL
            board[6][8] = WALL
            board[6][7] = WALL
            board[6][6] = WALL
            board[6][5] = WALL
            board[6][4] = WALL
            board[6][3] = WALL

            val labyrinth = Labyrinth(board)
            labyrinth.start = start
            labyrinth.end = end

            return labyrinth
        }

        private fun initBoardBorderWalls(size: Int): Array<Array<Case>> {

            val board = Array(size, { Array(size, { PATH }) })

            for (line in 0 until size) {
                for (col in 0 until size) {
                    when {
                        isFirstOrLast(line, size) -> board[line][col] = WALL
                        isFirstOrLast(col, size) -> board[line][col] = WALL
                        else -> board[line][col] = PATH
                    }
                }
            }

            return board
        }

        private fun isFirstOrLast(index: Int, size: Int) = (index == 0 || index == size - 1)
    }
}