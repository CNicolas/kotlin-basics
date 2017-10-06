package perceptron

import org.assertj.core.api.Assertions.assertThat
import org.testng.annotations.Test
import perceptron.trainers.DoubleTrainer
import perceptron.trainers.Trainer
import java.util.*

class PerceptronSimpleTest {

    private fun f(x: Double) = x

    @Test
    fun weights_should_be_different_from_0_when_given_doubles() {
        val perceptron = Perceptron()

        val r = Random(42L)
        val trainingData: Array<Trainer<Double>> = Array(2000) {
            val x = r.nextDouble() * 100 * (if (r.nextBoolean()) 1 else -1)
            val y = r.nextDouble() * 100 * (if (r.nextBoolean()) 1 else -1)
            val answer = if (y < f(x)) LearningClasse.BAD else LearningClasse.GOOD

            DoubleTrainer(arrayOf(x, y), answer)
        }

        val weights = perceptron.run(trainingData, 1000)

        assertThat(weights.sum()).isNotEqualTo(0.0)

        println(Arrays.toString(weights))

        assertThat(perceptron.decide(arrayOf(10.0, 20.0)).classe).isEqualTo(LearningClasse.GOOD)
        assertThat(perceptron.decide(arrayOf(10.0, 1.0)).classe).isEqualTo(LearningClasse.BAD)
    }
}