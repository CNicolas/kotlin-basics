package perceptron

class Perceptron {
    lateinit var weights: DoubleArray

    fun run(data: Array<Trainer>, iterations: Int): DoubleArray {
        val weights = DoubleArray(data[0].inputs.size, { 0.0 })

        for (iteration in 1..iterations) {
            data.forEach { train(weights, it) }
        }

        this.weights = weights

        return weights
    }

    fun decide(point: DoubleArray): Trainer {
        val prediction = calculatePrediction(weights, point)

        return Trainer(point, prediction)
    }

    private fun train(weights: DoubleArray, input: Trainer) {
        val prediction = calculatePrediction(weights, input.inputs)

        if (prediction != input.classe) {
            if (input.classe == LearningClasse.GOOD) {
                for (i in 0 until weights.size)
                    weights[i] += input.inputs[i]
            } else {
                for (i in 0 until weights.size)
                    weights[i] -= input.inputs[i]
            }
        }
    }

    private fun calculatePrediction(weights: DoubleArray, dataVector: DoubleArray): LearningClasse {
        val scalarProduct = scalarProduct(weights, dataVector)

        return activate(scalarProduct)
    }

    private fun activate(scalarProduct: Double): LearningClasse =
            if (scalarProduct > 0.0) LearningClasse.GOOD else LearningClasse.BAD

    private fun scalarProduct(weights: DoubleArray, inputVector: DoubleArray): Double {
        return (0 until weights.size).sumByDouble { weights[it] * inputVector[it] }
    }
}