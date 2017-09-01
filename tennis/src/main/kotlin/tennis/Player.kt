package tennis

import tennis.Score.LOVE

class Player {
    var score: Score = LOVE

    fun winBall(): Unit {
        score = score.next()
    }
}