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
        val startingGrid = Array(5) { Array(5) { Cell() } }
        startingGrid[2][1].beBorn()
        startingGrid[2][2].beBorn()
        startingGrid[2][3].beBorn()

        val expectedGrid = Array(5) { Array(5) { Cell() } }
        expectedGrid[1][2].beBorn()
        expectedGrid[2][2].beBorn()
        expectedGrid[3][2].beBorn()

        val game = GameOfLife(startingGrid)

        assertThat(game.stringRepresentation()).isEqualTo(game.stringRepresentation(startingGrid))
        assertThat(game.stringRepresentation(game.turn())).isEqualTo(game.stringRepresentation(expectedGrid))
    }
}