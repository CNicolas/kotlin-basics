package tennis

class Player(val name: String) {
    var score: Int = Score.LOVE.ordinal

    fun winBall() {
        score = Score.values()[score].next().ordinal
    }

    fun getScore(): Score = Score.values()[score]
}
