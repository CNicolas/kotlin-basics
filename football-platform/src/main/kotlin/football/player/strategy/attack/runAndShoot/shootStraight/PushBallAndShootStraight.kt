package football.player.strategy.attack.runAndShoot.shootStraight

import football.Ball
import football.FieldContext
import football.game.GameSide
import football.player.Player
import football.player.ShootingStrength
import football.player.SideInTeam
import football.player.strategy.AbstractPlayerStrategy
import helpers.Coordinates

class PushBallAndShootStraight(override val side: SideInTeam) : AbstractPlayerStrategy() {
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

    override fun setInitialX(gameSide: GameSide): Double {
        return when (gameSide) {
            GameSide.HOME -> FieldContext.fieldTotalWidth / 3
            else -> (2 * FieldContext.fieldTotalWidth) / 3
        }
    }

    override fun setInitialY(): Double {
        return when (side) {
            SideInTeam.UP -> FieldContext.fieldTotalHeight / 3
            SideInTeam.DOWN -> (2 * FieldContext.fieldTotalHeight) / 3
            else -> FieldContext.fieldHalfWidth
        }
    }
}