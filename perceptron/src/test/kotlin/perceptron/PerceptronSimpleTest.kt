package perceptron

import org.assertj.core.api.Assertions
import org.testng.annotations.Test
import java.util.*

class PerceptronSimpleTest {

    private fun h(a: Double, b: Double, c: Double) = a + b - c

    @Test
    fun weights_should_be_different_from_0_when_given_doubles() {
        val perceptron = Perceptron()

        val r = Random()
        val trainingData: Array<Trainer<Double>> = Array(2000) {
            val a = r.nextDouble() * 100 * (if (r.nextBoolean()) 1 else -1)
            val b = r.nextDouble() * 100 * (if (r.nextBoolean()) 1 else -1)
            val c = r.nextDouble() * 100 * (if (r.nextBoolean()) 1 else -1)
            val d = r.nextDouble() * 100 * (if (r.nextBoolean()) 1 else -1)
            val answer = if (d < h(a, b, c)) LearningClasse.BAD else LearningClasse.GOOD
            DoubleTrainer(arrayOf(a, b, c, d), answer)
        }

        val weights = perceptron.run(trainingData, 1000)

        Assertions.assertThat(weights.sum()).isNotEqualTo(0.0)

        println(Arrays.toString(weights))
    }
}