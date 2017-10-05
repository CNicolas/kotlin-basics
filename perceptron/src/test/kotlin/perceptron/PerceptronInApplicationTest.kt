package perceptron

import data.illness.Diagnostic
import org.assertj.core.api.Assertions
import org.testng.annotations.Test
import java.util.*

class PerceptronInApplicationTest {

    private fun illness(diagnostic: Diagnostic): Boolean {
        return when {
            diagnostic.soreThroat && !diagnostic.lungs -> true
            diagnostic.bronchi && diagnostic.lungs -> true
            diagnostic.eye && diagnostic.headAche -> true
            diagnostic.cold && !diagnostic.bronchi -> false
            else -> false
        }
    }

    @Test
    fun weights_should_be_different_from_0_when_given_data() {
        val perceptron = Perceptron()

        val r = Random()
        val trainingData: Array<Trainer<Boolean>> = Array(2000) {
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
            val isIll = r.nextBoolean()

            val answer = if (isIll != illness(diagnostic)) LearningClasse.BAD else LearningClasse.GOOD
            BooleanTrainer(diagnostic.toArray(), answer)
        }

        val weights = perceptron.run(trainingData, 1000)

        Assertions.assertThat(weights.sum()).isNotEqualTo(0.0)

        println(Arrays.toString(weights))
    }
}