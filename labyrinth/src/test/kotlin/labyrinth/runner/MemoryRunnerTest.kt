package labyrinth.runner

import labyrinth.LabyrinthFactory
import org.assertj.core.api.Assertions
import org.testng.annotations.Test

class MemoryRunnerTest {
    @Test
    fun should_finish_using_Memory_Runner() {
        val labyrinth = LabyrinthFactory.createTestingLabyrinthWithoutObstacles()
        val labyrintheRunner = MemoryLabyrinthRunner(labyrinth)

        val steps = labyrintheRunner.run()

        Assertions.assertThat(steps).isPositive()
    }

    @Test
    fun should_finish_labyrinth_with_obstacles_using_Memory_Runner() {
        val labyrinth = LabyrinthFactory.createTestingLabyrinthWithObstacle()
        val labyrintheRunner = MemoryLabyrinthRunner(labyrinth)

        val steps = labyrintheRunner.run()

        Assertions.assertThat(steps).isPositive()
    }

    @Test
    fun should_finish_big_labyrinth_with_obstacles_using_Memory_Runner() {
        val labyrinth = LabyrinthFactory.createBigTestingLabyrinthWithObstacle()
        val labyrintheRunner = MemoryLabyrinthRunner(labyrinth)

        val steps = labyrintheRunner.run()

        Assertions.assertThat(steps).isPositive()
    }
}