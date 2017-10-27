package football.game.strategies

import football.game.Coordinates
import football.game.Player
import football.game.Team

class StandStill(team: Team, override val initialPosition: Coordinates = Coordinates(50.0, 150.0)) : AbstractPlayerStrategy(team) {
    override lateinit var player: Player

    init {
        setPlayer("Still ${team.side}", initialPosition)
    }

    override fun move(): Coordinates {
        return initialPosition
    }

    override fun shoot(): Coordinates {
        return getOpponentGoalsCenter()
    }
}