package football.player.strategy.attack.runAndShoot.shootOblique

import football.Ball
import football.FieldContext
import football.game.GameSide
import football.player.Player
import football.player.ShootingStrength
import football.player.SideInTeam
import football.player.SideInTeam.*
import football.player.strategy.AbstractPlayerStrategy
import helpers.Coordinates
import java.util.*

class RunZigZag(override val side: SideInTeam) : AbstractPlayerStrategy() {
    private var direction = when (side) {
        DOWN -> true
        UP -> false
        CENTER -> Random().nextBoolean()
    }

    override fun move(player: Player): Coordinates {
        val destination = Ball.instance.position

        return moveTowards(player.position, destination)
    }

    override fun shoot(player: Player): Coordinates {
        val opponentsGoalCenter = getOpponentGoalsCenter(player)

        val destinationY = when (isInOpponentSurface(player)) {
            true -> when (direction) {
                true -> opponentsGoalCenter.y + (FieldContext.cageSize / 3)
                false -> opponentsGoalCenter.y - (FieldContext.cageSize / 3)
            }
            false -> when (direction) {
                true -> opponentsGoalCenter.y + FieldContext.cageSize
                false -> opponentsGoalCenter.y - FieldContext.cageSize
            }
        }
        direction = !direction

        val aim = Coordinates(opponentsGoalCenter.x, destinationY)
        return shootTowards(player.position, aim, ShootingStrength.NORMAL)
    }

    override fun setInitialX(gameSide: GameSide): Double {
        return when (gameSide) {
            GameSide.HOME -> FieldContext.fieldTotalWidth / 3
            else -> (2 * FieldContext.fieldTotalWidth) / 3
        }
    }

    override fun setInitialY(): Double {
        return when (side) {
            UP -> FieldContext.fieldTotalHeight / 3
            DOWN -> (2 * FieldContext.fieldTotalHeight) / 3
            else -> FieldContext.fieldHalfWidth
        }
    }
}