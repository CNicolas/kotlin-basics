package perceptron.trainers

import perceptron.LearningClasse
import java.util.*

data class BooleanTrainer(override val inputs: Array<Boolean>, override val classe: LearningClasse) : Trainer<Boolean> {
    override fun toDouble(index: Int): Double = if (inputs[index]) 1.0 else 0.0

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as BooleanTrainer

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