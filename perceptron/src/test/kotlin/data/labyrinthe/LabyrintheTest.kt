package data.labyrinthe

import org.assertj.core.api.Assertions.assertThat
import org.testng.annotations.Test

class LabyrintheTest {

    @Test
    fun should_initialize_correctly() {
        val labyrinthe = Labyrinthe(10)

        println(labyrinthe)
    }

    @Test
    fun should_finish() {
        val labyrinthe = Labyrinthe(5)
        println(labyrinthe)

        val player = Player("John")
        val labyrintheRunner = LabyrintheRunner(labyrinthe, player)

        assertThat(labyrintheRunner.run()).isPositive()
    }
}