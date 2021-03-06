package perceptron.trainers

import perceptron.LearningClasse

interface Trainer<T> {
    val inputs: Array<T>
    val classe: LearningClasse

    fun toDouble(index: Int): Double
}