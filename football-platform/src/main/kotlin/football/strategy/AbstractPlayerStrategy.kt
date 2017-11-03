package football.strategy

import football.FieldContext.Companion.height
import football.FieldContext.Companion.moveDistanceByTurn
import football.FieldContext.Companion.shootingDistance
import football.FieldContext.Companion.surfaceSize
import football.FieldContext.Companion.width
import football.player.Player
import helpers.Coordinates
import helpers.GameSide
import helpers.ShootingStrength
import helpers.getMaxCoordinates

abstract class AbstractPlayerStrategy : PlayerStrategy {
    protected fun moveTowards(from: Coordinates, aim: Coordinates): Coordinates {
        return getMaxCoordinates(from, aim, moveDistanceByTurn)
    }

    protected fun shootTowards(from: Coordinates, aim: Coordinates, strength: ShootingStrength): Coordinates {
        val trueX = when {
            aim.x < from.x -> aim.x - from.x
            aim.x > from.x -> aim.x + from.x
            else -> aim.x
        }

        val trueY = when {
            aim.y < from.y -> aim.y - from.y
            aim.y > from.y -> aim.y + from.y
            else -> aim.y
        }

        val trueAim = Coordinates(trueX, trueY)

        return getMaxCoordinates(from, trueAim, shootingDistance * strength.strengthPercentage)
    }

    protected fun getOpponentGoalsCenter(player: Player): Coordinates {
        return when (player.team.gameSide) {
            GameSide.HOME -> getGoalCenter(GameSide.AWAY)
            else -> getGoalCenter(GameSide.HOME)
        }
    }

    protected fun getGoalCenter(gameSide: GameSide): Coordinates {
        return when (gameSide) {
            GameSide.HOME -> Coordinates(0.0, height / 2)
            else -> Coordinates(width, height / 2)
        }
    }

    protected fun isInOpponentSurface(player: Player): Boolean {
        val isInSurfaceByX = when (player.team.gameSide) {
            GameSide.HOME -> player.position.x > width - surfaceSize
            else -> player.position.x < surfaceSize
        }

        val isInSurfaceByY = player.position.y > surfaceSize && player.position.y < height - surfaceSize

        return isInSurfaceByX && isInSurfaceByY
    }
}