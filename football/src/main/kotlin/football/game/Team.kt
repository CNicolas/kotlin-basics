package football.game

import football.game.strategies.PlayerStrategy
import football.helpers.Side
import javafx.scene.paint.Color

class Team(val color: Color, val side: Side) {
    lateinit var player1: PlayerStrategy
    lateinit var player2: PlayerStrategy

    var score: Int = 0

    override fun toString(): String {
        return "Team $color : $score"
    }
}