package football.player.strategy.attack.runAndShoot.shootOblique

import football.Ball
import football.FieldContext
import football.game.GameSide
import football.game.GameSide.HOME
import football.player.Player
import football.player.ShootingStrength
import football.player.SideInTeam
import football.player.SideInTeam.*
import football.player.strategy.AbstractPlayerStrategy
import helpers.Coordinates
import java.util.*

class RunAndShootObliqueToOtherSideOfTeam(override val side: SideInTeam) : AbstractPlayerStrategy() {
    override fun move(player: Player): Coordinates {
        val destination = Ball.instance.position

        return moveTowards(player.position, destination)
    }

    override fun shoot(player: Player): Coordinates {
        val variation = FieldContext.cageHalfSize
        val destinationVariation = Random().nextBoolean()
        val opponentsGoalCenter = getOpponentGoalsCenter(player)

        val destinationY = when {
            side == UP -> opponentsGoalCenter.y + variation
            side == DOWN -> opponentsGoalCenter.y - variation
            side == CENTER && destinationVariation -> opponentsGoalCenter.y + variation
            side == CENTER && !destinationVariation -> opponentsGoalCenter.y - variation
            else -> opponentsGoalCenter.y
        }

        val strength = when (isInOpponentSurface(player)) {
            true -> ShootingStrength.SHOOT
            false -> ShootingStrength.RUN
        }

        val aim = Coordinates(opponentsGoalCenter.x, destinationY)
        return shootTowards(player.position, aim, strength)
    }

    override fun setInitialX(gameSide: GameSide): Double {
        return when (gameSide) {
            HOME -> FieldContext.fieldTotalWidth / 3
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