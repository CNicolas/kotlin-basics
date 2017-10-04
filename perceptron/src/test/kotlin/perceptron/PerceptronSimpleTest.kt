package perceptron

import org.assertj.core.api.Assertions.assertThat
import org.testng.annotations.Test
import java.util.*

class PerceptronSimpleTest {

    private fun f(x: Double) = x

    @Test
    fun weights_should_be_different_from_0() {
        val perceptron = Perceptron()

        val r = Random()
        val trainingData: Array<Trainer> = Array(2000) {
            val x = r.nextDouble() * 100 * (if (r.nextBoolean()) 1 else -1)
            val y = r.nextDouble() * 100 * (if (r.nextBoolean()) 1 else -1)
            val answer = if (y < f(x)) LearningClasse.BAD else LearningClasse.GOOD
            Trainer(doubleArrayOf(x, y), answer)
        }

        val weights = perceptron.run(trainingData, 1000)

        assertThat(weights).doesNotContain(0.0)
        assertThat(weights).doesNotHaveDuplicates()

        println(Arrays.toString(weights))

        assertThat(perceptron.decide(doubleArrayOf(10.0, 20.0)).classe).isEqualTo(LearningClasse.GOOD)
        assertThat(perceptron.decide(doubleArrayOf(10.0, 1.0)).classe).isEqualTo(LearningClasse.BAD)
    }
}