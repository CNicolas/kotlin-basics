package perceptron

class Perceptron {
    lateinit var weights: DoubleArray

    fun <T> run(data: Array<Trainer<T>>, iterations: Int): DoubleArray {
        val weights = DoubleArray(data[0].inputs.size, { 0.0 })

        for (iteration in 1..iterations) {
            data.forEach { train(weights, it) }
        }

        this.weights = weights

        return weights
    }

//    fun <T> decide(point: Array<T>): Trainer<T> {
//        val prediction = calculatePrediction(weights, point)
//
//        return Trainer(point, prediction)
//    }

    private fun <T> train(weights: DoubleArray, input: Trainer<T>) {
        val prediction = calculatePrediction(weights, input)

        if (prediction != input.classe) {
            if (input.classe == LearningClasse.GOOD) {
                for (i in 0 until weights.size)
                    weights[i] += input.toDouble(i)
            } else {
                for (i in 0 until weights.size)
                    weights[i] -= input.toDouble(i)
            }
        }
    }

    private fun <T> calculatePrediction(weights: DoubleArray, trainer: Trainer<T>): LearningClasse {
        val scalarProduct = scalarProduct(weights, trainer)

        return activate(scalarProduct)
    }

    private fun activate(scalarProduct: Double): LearningClasse =
            if (scalarProduct > 0.0) LearningClasse.GOOD else LearningClasse.BAD

    private fun <T> scalarProduct(weights: DoubleArray, trainer: Trainer<T>): Double {
        return (0 until weights.size).sumByDouble { weights[it] * trainer.toDouble(it) }
    }
}