package perceptron

import perceptron.trainers.BooleanTrainer
import perceptron.trainers.DoubleTrainer
import perceptron.trainers.Trainer

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

    fun decide(dataVector: Array<Double>): DoubleTrainer {
        val prediction = calculatePrediction(weights, DoubleTrainer(dataVector, LearningClasse.BAD))

        return DoubleTrainer(dataVector, prediction)
    }

    fun decide(dataVector: Array<Boolean>): BooleanTrainer {
        val prediction = calculatePrediction(weights, BooleanTrainer(dataVector, LearningClasse.BAD))

        return BooleanTrainer(dataVector, prediction)
    }

    private fun <T> train(weights: DoubleArray, input: Trainer<T>) {
        val prediction = calculatePrediction(weights, input)

        // Apply correction
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