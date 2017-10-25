package football.game

import javafx.scene.paint.Color

class Team(val color: Color, val player1: Player, var player2: Player) {
    var score = 0

    override fun toString(): String {
        return "Team $color : $score"
    }


}