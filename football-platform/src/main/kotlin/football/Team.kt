package football

import helpers.Coordinates
import helpers.Side
import javafx.scene.paint.Color

class Team(val color: Color, val side: Side) {
    val player1: Player = Player(this)
    val player2: Player = Player(this)

    init {
        when (side) {
            Side.LEFT -> {
                player1.position = Coordinates(150.0, 100.0)
                player2.position = Coordinates(150.0, 200.0)
            }
            Side.RIGHT -> {
                player1.position = Coordinates(350.0, 100.0)
                player2.position = Coordinates(350.0, 200.0)
            }
        }
    }
}