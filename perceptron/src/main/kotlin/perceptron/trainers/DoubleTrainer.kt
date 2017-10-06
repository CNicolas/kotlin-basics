package perceptron.trainers

import perceptron.LearningClasse
import java.util.*

data class DoubleTrainer(override val inputs: Array<Double>, override val classe: LearningClasse) : Trainer<Double> {
    override fun toDouble(index: Int): Double = inputs[index]

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as DoubleTrainer

        if (!Arrays.equals(inputs, other.inputs)) return false
        if (classe != other.classe) return false

        return true
    }

    override fun hashCode(): Int {
        var result = Arrays.hashCode(inputs)
        result = 31 * result + classe.hashCode()
        return result
    }

}