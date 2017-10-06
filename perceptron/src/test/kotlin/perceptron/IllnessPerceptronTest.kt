package perceptron

import data.illness.Diagnostic
import org.assertj.core.api.Assertions.assertThat
import org.testng.annotations.Test
import perceptron.trainers.BooleanTrainer
import perceptron.trainers.Trainer
import java.util.*

class IllnessPerceptronTest {

    private fun illness(diagnostic: Diagnostic): Double {
        return when {
            diagnostic.soreThroat && diagnostic.lungs -> 10.0
            diagnostic.soreThroat && diagnostic.bronchi -> 5.0
            diagnostic.bronchi && diagnostic.lungs -> 10.0
            diagnostic.eye && diagnostic.headAche -> 5.0
            diagnostic.cold && !diagnostic.bronchi -> 2.0
            diagnostic.back && diagnostic.neck && !diagnostic.waist -> -10.0
            else -> -20.0
        }
    }

    @Test
    fun weights_should_be_different_from_0_when_given_booleans() {
        val perceptron = Perceptron()

        val r = Random(40L)
        val trainingData: Array<Trainer<Boolean>> = Array(5000) {
            val diagnostic = Diagnostic()
            diagnostic.soreThroat = r.nextBoolean()
            diagnostic.cold = r.nextBoolean()
            diagnostic.lungs = r.nextBoolean()
            diagnostic.bronchi = r.nextBoolean()
            diagnostic.back = r.nextBoolean()
            diagnostic.waist = r.nextBoolean()
            diagnostic.neck = r.nextBoolean()
            diagnostic.eye = r.nextBoolean()
            diagnostic.headAche = r.nextBoolean()

            val isIll = r.nextDouble() * 50 * (if (r.nextBoolean()) 1 else -1)

            val answer = if (isIll < illness(diagnostic)) LearningClasse.BAD else LearningClasse.GOOD
            BooleanTrainer(diagnostic.toArray(), answer)
        }

        val weights = perceptron.run(trainingData, 10000)

        assertThat(weights.sum()).isNotEqualTo(0.0)

        println(Arrays.toString(weights))

        val notIll = Diagnostic()
        val notIll2 = Diagnostic()
        notIll2.cold = true
        notIll2.back = true
        val ill = Diagnostic()
        ill.soreThroat = true
        ill.lungs = true

        assertThat(perceptron.decide(ill.toArray()).classe).isEqualTo(LearningClasse.BAD)
        assertThat(perceptron.decide(notIll.toArray()).classe).isEqualTo(LearningClasse.GOOD)
        assertThat(perceptron.decide(notIll2.toArray()).classe).isEqualTo(LearningClasse.GOOD)
    }
}