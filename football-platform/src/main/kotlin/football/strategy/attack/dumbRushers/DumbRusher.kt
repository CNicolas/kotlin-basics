package football.strategy.attack.dumbRushers

import football.FieldContext
import football.game.Ball
import football.player.Player
import football.strategy.AbstractPlayerStrategy
import helpers.Coordinates
import helpers.GameSide
import helpers.ShootingStrength
import helpers.SideInTeam

abstract class DumbRusher(private val strength: ShootingStrength) : AbstractPlayerStrategy() {
    override fun move(player: Player): Coordinates {
        val destination = Ball.instance.position

        return moveTowards(player.position, destination)
    }

    override fun shoot(player: Player): Coordinates {
        val destination = getOpponentGoalsCenter(player)

        return shootTowards(player.position, destination, strength)
    }

    override fun setInitialPosition(gameSide: GameSide): Coordinates {
        val x = setInitialX(gameSide)
        val y = setInitialY()

        initialPosition = Coordinates(x, y)

        return initialPosition
    }

    override fun setInitialX(gameSide: GameSide): Double {
        return when (gameSide) {
            GameSide.HOME -> FieldContext.width / 3
            else -> (2 * FieldContext.width) / 3
        }
    }

    override fun setInitialY(): Double {
        return when (side) {
            SideInTeam.UP -> FieldContext.height / 3
            SideInTeam.DOWN -> (2 * FieldContext.height) / 3
            else -> FieldContext.width / 2
        }
    }
}