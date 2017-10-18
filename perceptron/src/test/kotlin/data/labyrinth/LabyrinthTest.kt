package data.labyrinth

import data.labyrinth.runner.DummyLabyrinthRunner
import data.labyrinth.runner.MemoryLabyrinthRunner
import data.labyrinth.runner.RandomLabyrinthRunner
import org.assertj.core.api.Assertions.assertThat
import org.testng.annotations.Test

class LabyrinthTest {

    @Test
    fun should_initialize_correctly() {
        LabyrinthFactory.createRandomEmptyLabyrinthOfSize(10)
    }

    @Test
    fun should_finish_using_Dummy_Runner() {
        val labyrinth = LabyrinthFactory.createRandomEmptyLabyrinthOfSize(5)
        val labyrintheRunner = DummyLabyrinthRunner(labyrinth)

        val steps = labyrintheRunner.run()

        println(steps)

        assertThat(steps).isPositive()
    }

    @Test
    fun should_finish_using_Random_Runner() {
        val labyrinth = LabyrinthFactory.createRandomEmptyLabyrinthOfSize(5)
        val labyrintheRunner = RandomLabyrinthRunner(labyrinth)

        val steps = labyrintheRunner.run(true)

        println(steps)

        assertThat(steps).isPositive()
    }

    @Test
    fun should_finish_using_Memory_Runner() {
        val labyrinth = LabyrinthFactory.createTestingLabyrinthWithoutObstacles()
        val labyrintheRunner = MemoryLabyrinthRunner(labyrinth)

        val steps = labyrintheRunner.run(true)

        println(steps)

        assertThat(steps).isPositive()
    }

    @Test
    fun should_finish_labyrinth_with_obstacles_using_Random_Runner() {
        val labyrinth = LabyrinthFactory.createTestingLabyrinthWithObstacle()
        val labyrintheRunner = RandomLabyrinthRunner(labyrinth)

        val steps = labyrintheRunner.run()

        println(steps)

        assertThat(steps).isPositive()
    }

    @Test
    fun should_finish_labyrinth_with_obstacles_using_Memory_Runner() {
        val labyrinth = LabyrinthFactory.createTestingLabyrinthWithObstacle()
        val labyrintheRunner = MemoryLabyrinthRunner(labyrinth)

        val steps = labyrintheRunner.run(true)

        println(steps)

        assertThat(steps).isPositive()
    }

    @Test
    fun should_finish_big_labyrinth_with_obstacles_using_Memory_Runner() {
        val labyrinth = LabyrinthFactory.createBigTestingLabyrinthWithObstacle()
        val labyrintheRunner = MemoryLabyrinthRunner(labyrinth)

        val steps = labyrintheRunner.run(false)

        println(steps)

        assertThat(steps).isPositive()
    }
}