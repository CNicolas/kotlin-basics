package data.labyrinthe

import org.assertj.core.api.Assertions.assertThat
import org.testng.annotations.Test

class LabyrintheTest {

    @Test
    fun should_initialize_correctly() {
        Labyrinthe(10)
    }

    @Test
    fun should_finish() {
        val labyrinthe = Labyrinthe(5)
        val labyrintheRunner = LabyrintheRunner(labyrinthe)

        val steps = labyrintheRunner.run()

        println(steps)

        assertThat(steps).isPositive()
    }
}