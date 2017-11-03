package football.strategy

import football.FieldContext
import football.game.Ball
import football.player.Player
import helpers.Coordinates
import helpers.GameSide
import helpers.ShootingStrength
import helpers.SideInTeam

class PushBallAndShootStraight(override val side: SideInTeam) : AbstractPlayerStrategy() {
    override var initialPosition = Coordinates()

    override fun move(player: Player): Coordinates {
        val destination = Ball.instance.position

        return moveTowards(player.position, destination)
    }

    override fun shoot(player: Player): Coordinates {
        val destination = getOpponentGoalsCenter(player)
        val strength = when (isInOpponentSurface(player)) {
            true -> ShootingStrength.SHOOT
            false -> ShootingStrength.NORMAL
        }

        return shootTowards(player.position, destination, strength)
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