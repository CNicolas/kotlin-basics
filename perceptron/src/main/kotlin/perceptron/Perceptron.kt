package perceptron

class Perceptron {

    fun train(data: Array<InputValue>, iterations: Int): Array<Double> {
        val weights: Array<Double> = Array(data.size, { 0.0 })

        for (iteration in 1..iterations) {
            data.forEach { input ->
                val prediction = signOf(scalarProduct(weights, input.inputVector))

                if (prediction != input.classe.classe) {
                    if (input.classe == LearningClasse.GOOD) {
                        weights.mapIndexed { index, weight -> weight + input.inputVector[index] }
                    } else {
                        weights.mapIndexed { index, weight -> weight - input.inputVector[index] }
                    }
                }
            }
        }

        return weights
    }

    private fun signOf(scalarProduct: Double): Int = if (scalarProduct > 0.0) 1 else -1

    private fun scalarProduct(weights: Array<Double>, inputVector: Array<Double>): Double {
        return (0 until weights.size).sumByDouble { weights[it] * inputVector[it] }
    }
}