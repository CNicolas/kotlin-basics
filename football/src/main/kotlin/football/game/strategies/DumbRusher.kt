package football.game.strategies

import football.game.Coordinates
import football.game.Game
import football.game.Player
import football.game.Team

class DumbRusher(team: Team, override val initialPosition: Coordinates = Coordinates(150.0, 75.0))
    : AbstractPlayerStrategy(team) {
    override lateinit var player: Player

    init {
        setPlayer("Dumb ${team.side}", initialPosition)
    }

    override fun move(): Coordinates {
        return Game.instance.ballPosition
    }

    override fun shoot(): Coordinates {
        return getOpponentGoalsCenter()
    }
}