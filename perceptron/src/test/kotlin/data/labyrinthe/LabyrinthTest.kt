package data.labyrinthe

import data.labyrinthe.runner.BasicLabyrintheRunner
import data.labyrinthe.runner.DummyLabyrinthRunner
import org.assertj.core.api.Assertions.assertThat
import org.testng.annotations.Test

class LabyrinthTest {

    @Test
    fun should_initialize_correctly() {
        Labyrinth(10)
    }

    @Test
    fun should_finish() {
        val labyrinthe = Labyrinth(5)
        val labyrintheRunner = DummyLabyrinthRunner(labyrinthe)

        val steps = labyrintheRunner.run()

        println(steps)

        assertThat(steps).isPositive()
    }

    @Test
    fun should_finish_given_board_to_labyrinthe() {
        val size = 5
        val board = Array(size, { Array(size, { Case.PATH }) })

        for (line in 0 until size) {
            for (col in 0 until size) {
                if (isFirstOrLast(size, line)) {
                    board[line][col] = Case.WALL
                } else if (isFirstOrLast(size, col)) {
                    board[line][col] = Case.WALL
                }
            }
        }

        board[1][0] = Case.START
        board[1][4] = Case.END
        board[1][2] = Case.WALL
        board[2][2] = Case.WALL

        val labyrinthe = Labyrinth(board)
        val labyrintheRunner = BasicLabyrintheRunner(labyrinthe)

        assertThat(labyrintheRunner.run()).isPositive()
    }

    private fun isFirstOrLast(size: Int, index: Int) = (index == 0 || index == size - 1)
}