package football.player

import football.strategy.PlayerStrategy
import helpers.GameSide
import javafx.scene.paint.Color

class Team(val color: Color, val gameSide: GameSide, private val strategy1: PlayerStrategy, private val strategy2: PlayerStrategy) {
    var player1: Player = Player(this, strategy1)
    var player2: Player = Player(this, strategy2)

    var score = 0

    init {
        strategy1.setInitialPosition(gameSide)
        strategy2.setInitialPosition(gameSide)

        resetPositions()
    }

    fun resetPositions() {
        player1.setInitialPosition(strategy1.initialPosition)
        player2.setInitialPosition(strategy2.initialPosition)
    }

    fun clone(): Team {
        val team = Team(color, gameSide, strategy1, strategy2)
        team.player1 = player1.clone()
        team.player2 = player2.clone()

        return team
    }

    override fun toString(): String {
        return "Team($gameSide, $score, [$player1, $player2])"
    }
}