package labyrinth.runner

import labyrinth.LabyrinthFactory
import org.assertj.core.api.Assertions.assertThat
import org.testng.annotations.Test

class DummyLabyrinthRunnerTest {
    @Test
    fun should_finish_using_Dummy_Runner() {
        val labyrinth = LabyrinthFactory.createRandomEmptyLabyrinthOfSize(5)
        val labyrintheRunner = DummyLabyrinthRunner(labyrinth)

        val steps = labyrintheRunner.run()

        assertThat(steps).isPositive()
    }
}