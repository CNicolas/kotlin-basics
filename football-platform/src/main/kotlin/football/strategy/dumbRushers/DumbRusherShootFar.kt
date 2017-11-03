package football.strategy.dumbRushers

import football.FieldContext
import football.game.Ball
import football.player.Player
import football.strategy.AbstractPlayerStrategy
import helpers.Coordinates
import helpers.GameSide
import helpers.ShootingStrength
import helpers.SideInTeam

class DumbRusherShootFar(override val side: SideInTeam) : AbstractPlayerStrategy() {
    override var initialPosition = Coordinates()

    override fun move(player: Player): Coordinates {
        val destination = Ball.instance.position

        return moveTowards(player.position, destination)
    }

    override fun shoot(player: Player): Coordinates {
        val destination = getOpponentGoalsCenter(player)

        return shootTowards(player.position, destination, ShootingStrength.CLEARANCE)
    }

    override fun setInitialPosition(gameSide: GameSide): Coordinates {
        val x = setInitialX(gameSide)
        val y = setInitialY()

        initialPosition = Coordinates(x, y)

        return initialPosition
    }

    private fun setInitialX(gameSide: GameSide): Double {
        return when (gameSide) {
            GameSide.HOME -> FieldContext.width / 3
            else -> (2 * FieldContext.width) / 3
        }
    }

    private fun setInitialY(): Double {
        return when (side) {
            SideInTeam.UP -> FieldContext.height / 3
            SideInTeam.DOWN -> (2 * FieldContext.height) / 3
            else -> FieldContext.width / 2
        }
    }
}