package tennis

import tennis.Score.LOVE

class Player(val name: String) {
    var score: Score = LOVE

    fun winBall(): Unit {
        score = score.next()
    }
}