package football.player

import football.strategy.PlayerStrategy
import helpers.Coordinates
import helpers.Side
import javafx.scene.paint.Color

class Team(val color: Color, val side: Side, private val strategy1: PlayerStrategy, private val strategy2: PlayerStrategy) {
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

        resetPositions()
    }

    fun resetPositions() {
        player1.setInitialPosition(player1InitialPosition)
        player2.setInitialPosition(player2InitialPosition)
    }

    fun clone(): Team {
        val team = Team(color, side, strategy1, strategy2)
        team.player1 = player1.clone()
        team.player2 = player2.clone()

        return team
    }

    override fun toString(): String {
        return "Team($side, $score, [$player1, $player2])"
    }
}