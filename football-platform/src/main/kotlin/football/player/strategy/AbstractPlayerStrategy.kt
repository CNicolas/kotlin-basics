package football.player.strategy

import football.FieldContext.Companion.height
import football.FieldContext.Companion.moveDistanceByTurn
import football.FieldContext.Companion.shootingDistance
import football.FieldContext.Companion.surfaceSize
import football.FieldContext.Companion.width
import football.game.GameSide
import football.game.GameSide.AWAY
import football.game.GameSide.HOME
import football.player.Player
import football.player.ShootingStrength
import helpers.Coordinates
import helpers.calculateFunctionOfX
import helpers.getMaxCoordinates

abstract class AbstractPlayerStrategy : PlayerStrategy {
    override var initialPosition: Coordinates = Coordinates()

    protected fun moveTowards(from: Coordinates, aim: Coordinates): Coordinates {
        return getMaxCoordinates(from, aim, moveDistanceByTurn)
    }

    protected fun shootTowards(from: Coordinates, aim: Coordinates, strength: ShootingStrength): Coordinates {
        val coef = (from.y - aim.y) / (from.x - aim.x)
        val originOrdinate = from.y - (coef * from.x)
        val variation = 100

        val trueX = when {
            aim.x < from.x -> aim.x - variation
            aim.x > from.x -> aim.x + variation
            else -> aim.x
        }

        val trueY = calculateFunctionOfX(trueX, coef, originOrdinate)

        return getMaxCoordinates(from, Coordinates(trueX, trueY), shootingDistance * strength.strengthPercentage)
    }

    protected fun getOpponentGoalsCenter(player: Player): Coordinates {
        return when (player.team.gameSide) {
            HOME -> getGoalCenter(AWAY)
            else -> getGoalCenter(HOME)
        }
    }

    protected fun getGoalCenter(gameSide: GameSide): Coordinates {
        return when (gameSide) {
            HOME -> Coordinates(0.0, height / 2)
            else -> Coordinates(width, height / 2)
        }
    }

    protected fun isInOpponentSurface(player: Player): Boolean {
        val isInSurfaceByX = when (player.team.gameSide) {
            HOME -> player.position.x > width - surfaceSize
            else -> player.position.x < surfaceSize
        }

        val isInSurfaceByY = player.position.y > surfaceSize && player.position.y < height - surfaceSize

        return isInSurfaceByX && isInSurfaceByY
    }

    abstract fun setInitialX(gameSide: GameSide): Double
    abstract fun setInitialY(): Double

    override fun toString(): String = "${javaClass.simpleName} $side"
}