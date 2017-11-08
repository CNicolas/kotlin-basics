package football.player.strategy.attack.runAndShoot.shootOblique

import football.Ball
import football.FieldContext
import football.game.GameSide
import football.game.GameSide.AWAY
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
        val variation = FieldContext.cageSize / 2
        val destinationVariation = Random().nextBoolean()
        val opponentsGoalCenter = getOpponentGoalsCenter(player)

        val destinationY = when {
            side == UP -> opponentsGoalCenter.y + variation
            side == DOWN -> opponentsGoalCenter.y - variation
            side == CENTER && destinationVariation -> opponentsGoalCenter.y + variation
            side == CENTER && !destinationVariation -> opponentsGoalCenter.y - variation
            else -> opponentsGoalCenter.y
        }

        val destinationX = when (player.team.gameSide) {
            HOME -> opponentsGoalCenter.x + 50
            AWAY -> opponentsGoalCenter.x - 50
        }

        val strength = when (isInOpponentSurface(player)) {
            true -> ShootingStrength.SHOOT
            false -> ShootingStrength.RUN
        }

        val aim = Coordinates(destinationX, destinationY)
        return shootTowards(player.position, aim, strength)
    }

    override fun setInitialPosition(gameSide: GameSide): Coordinates {
        val x = setInitialX(gameSide)
        val y = setInitialY()

        initialPosition = Coordinates(x, y)

        return initialPosition
    }

    override fun setInitialX(gameSide: GameSide): Double {
        return when (gameSide) {
            HOME -> FieldContext.width / 3
            else -> (2 * FieldContext.width) / 3
        }
    }

    override fun setInitialY(): Double {
        return when (side) {
            UP -> FieldContext.height / 3
            DOWN -> (2 * FieldContext.height) / 3
            else -> FieldContext.width / 2
        }
    }
}