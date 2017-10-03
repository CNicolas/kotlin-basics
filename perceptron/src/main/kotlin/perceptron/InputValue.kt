package perceptron

import java.util.*


data class InputValue(val inputVector: Array<Double>, val classe: LearningClasse) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as InputValue

        if (!Arrays.equals(inputVector, other.inputVector)) return false
        if (classe != other.classe) return false

        return true
    }

    override fun hashCode(): Int {
        var result = Arrays.hashCode(inputVector)
        result = 31 * result + classe.hashCode()
        return result
    }

}