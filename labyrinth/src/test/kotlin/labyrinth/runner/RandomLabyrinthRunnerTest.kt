package labyrinth.runner

import labyrinth.LabyrinthFactory
import org.assertj.core.api.Assertions.assertThat
import org.testng.annotations.Test

class RandomLabyrinthRunnerTest {
    @Test
    fun should_finish_using_Random_Runner() {
        val labyrinth = LabyrinthFactory.createRandomEmptyLabyrinthOfSize(5)
        val labyrintheRunner = RandomLabyrinthRunner(labyrinth)

        val steps = labyrintheRunner.run()

        assertThat(steps).isPositive()
    }

    @Test
    fun should_finish_labyrinth_with_obstacles_using_Random_Runner() {
        val labyrinth = LabyrinthFactory.createTestingLabyrinthWithObstacle()
        val labyrintheRunner = RandomLabyrinthRunner(labyrinth)

        val steps = labyrintheRunner.run()

        assertThat(steps).isPositive()
    }
}