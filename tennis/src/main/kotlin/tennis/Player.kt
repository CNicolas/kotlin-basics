package tennis

class Player(val name: String) {
    var score: Int = 0

    fun winBall(): Unit {
        score++
    }

    fun getScore(): Score = Score.values()[score]
}
