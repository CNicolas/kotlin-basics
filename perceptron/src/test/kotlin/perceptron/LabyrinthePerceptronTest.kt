package perceptron

import data.labyrinthe.Labyrinthe
import org.testng.annotations.Test

class LabyrinthePerceptronTest {

    @Test
    fun should_initialize_correctly() {
        val labyrinthe = Labyrinthe(10)

        println(labyrinthe)
    }
}