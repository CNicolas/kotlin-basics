package football.game.strategies

import football.game.Coordinates
import football.game.Game
import football.game.Player
import football.game.Team
import football.helpers.Side

abstract class AbstractPlayerStrategy(override val team: Team) : PlayerStrategy {
    override var currentPosition: Coordinates
        get() = player.coordinates
        set(value) {
            player.coordinates = value
        }
    override val name: String
        get() = player.name

    protected fun setPlayer(name: String, coordinates: Coordinates) {
        player = when {
            team.side == Side.LEFT -> Player(name, coordinates)
            else -> Player(name, Coordinates(Game.width - coordinates.x, coordinates.y))
        }
    }

    protected fun getOpponentGoalsCenter(): Coordinates {
        return when (team.side) {
            Side.LEFT -> getGoalCenter(Side.RIGHT)
            else -> getGoalCenter(Side.LEFT)
        }
    }

    private fun getGoalCenter(side: Side): Coordinates {
        return when (side) {
            Side.LEFT -> Coordinates(0.0, Game.height / 2)
            else -> Coordinates(Game.width, Game.height / 2)
        }
    }
}