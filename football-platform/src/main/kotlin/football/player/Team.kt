package football.player

import football.strategy.PlayerStrategy
import helpers.Coordinates
import helpers.Side
import javafx.scene.paint.Color

class Team(val color: Color, val side: Side, strategy1: PlayerStrategy, strategy2: PlayerStrategy) {
    var player1: Player = Player(this, strategy1)
    var player2: Player = Player(this, strategy2)

    var score = 0

    private var player1InitialPosition: Coordinates
    private var player2InitialPosition: Coordinates

    init {
        when (side) {
            Side.LEFT -> {
                player1InitialPosition = Coordinates(150.0, 100.0)
                player2InitialPosition = Coordinates(150.0, 200.0)
            }
            Side.RIGHT -> {
                player1InitialPosition = Coordinates(350.0, 100.0)
                player2InitialPosition = Coordinates(350.0, 200.0)
            }
        }

        player1.position = player1InitialPosition
        player2.position = player2InitialPosition
    }

    fun resetPositions() {
        player1.position = player1InitialPosition
        player2.position = player2InitialPosition
    }

    override fun toString(): String {
        return "Team($side, score = $score)"
    }


}