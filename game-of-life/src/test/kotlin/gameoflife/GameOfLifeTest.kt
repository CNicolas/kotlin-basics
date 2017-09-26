package gameoflife

import org.assertj.core.api.Assertions.assertThat
import org.testng.annotations.Test

class GameOfLifeTest {
    @Test
    fun should_be_all_dead() {
        val startingGrid = Array(2) { Array(2) { Cell() } }
        startingGrid[0][0].beBorn()

        val expectedGrid = Array(2) { Array(2) { Cell() } }

        val game = GameOfLife(startingGrid)

        assertThat(game.stringRepresentation()).isEqualTo(game.stringRepresentation(startingGrid))
        game.turn()
        assertThat(game.stringRepresentation()).isEqualTo(game.stringRepresentation(expectedGrid))
    }

    @Test
    fun should_have_3_cells_vertically() {
        val startingGrid = Array(3) { Array(3) { Cell() } }
        startingGrid[1][0].beBorn()
        startingGrid[1][1].beBorn()
        startingGrid[1][2].beBorn()

        val expectedGrid = Array(3) { Array(3) { Cell() } }
        expectedGrid[0][1].beBorn()
        expectedGrid[1][1].beBorn()
        expectedGrid[2][1].beBorn()

        val game = GameOfLife(startingGrid)

        assertThat(game.stringRepresentation()).isEqualTo(game.stringRepresentation(startingGrid))
        game.turn()
        assertThat(game.stringRepresentation()).isEqualTo(game.stringRepresentation(expectedGrid))
    }

    @Test
    fun should_have_3_cells_horizontally() {
        val startingGrid = Array(3) { Array(3) { Cell() } }
        startingGrid[1][0].beBorn()
        startingGrid[1][1].beBorn()
        startingGrid[1][2].beBorn()

        val expectedMiddleGrid = Array(3) { Array(3) { Cell() } }
        expectedMiddleGrid[0][1].beBorn()
        expectedMiddleGrid[1][1].beBorn()
        expectedMiddleGrid[2][1].beBorn()

        val game = GameOfLife(startingGrid)

        assertThat(game.stringRepresentation()).isEqualTo(game.stringRepresentation(startingGrid))
        game.turn()
        assertThat(game.stringRepresentation()).isEqualTo(game.stringRepresentation(expectedMiddleGrid))
        game.turn()
        assertThat(game.stringRepresentation()).isEqualTo(game.stringRepresentation(startingGrid))
    }

    @Test
    fun should_die_at_the_end() {
        val startingGrid = Array(4) { Array(4) { Cell() } }
        startingGrid[0][2].beBorn()
        startingGrid[0][3].beBorn()
        startingGrid[1][0].beBorn()
        startingGrid[1][3].beBorn()
        startingGrid[2][1].beBorn()
        startingGrid[2][2].beBorn()
        startingGrid[3][1].beBorn()
        startingGrid[3][3].beBorn()

        val game = GameOfLife(startingGrid)

        println(game.stringRepresentation())
        game.turn()
        println(game.stringRepresentation())
        game.turn()
        println(game.stringRepresentation())
        game.turn()
        println(game.stringRepresentation())
        game.turn()
        println(game.stringRepresentation())
        game.turn()
        println(game.stringRepresentation())
        game.turn()
        println(game.stringRepresentation())
        game.turn()
        println(game.stringRepresentation())
        game.turn()
        println(game.stringRepresentation())
        game.turn()
        println(game.stringRepresentation())
        game.turn()

    }
}
