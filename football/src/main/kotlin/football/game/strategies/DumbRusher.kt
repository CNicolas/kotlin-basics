package football.game.strategies

import football.game.Coordinates
import football.game.GameContext
import football.game.Player
import football.game.Team
import football.helpers.moveTowards

class DumbRusher(team: Team, override val initialPosition: Coordinates = Coordinates(150.0, 75.0))
    : AbstractPlayerStrategy(team) {
    override lateinit var player: Player

    init {
        setPlayer("Dumb ${team.side}", initialPosition)
    }

    override fun move() {
        val destination = GameContext.instance.ballPosition
        currentPosition = moveTowards(currentPosition, destination, GameContext.moveDistanceByTurn)
    }

    override fun shoot(): Coordinates {
        return getOpponentGoalsCenter()
    }
}