package football.player.strategy

import football.FieldContext
import football.FieldContext.Companion.fieldHalfHeight
import football.FieldContext.Companion.fieldTotalWidth
import football.FieldContext.Companion.moveDistanceByTurn
import football.FieldContext.Companion.shootingDistance
import football.game.GameSide
import football.game.GameSide.AWAY
import football.game.GameSide.HOME
import football.player.Player
import football.player.ShootingStrength
import helpers.Coordinates
import helpers.extractFunctionOfLine
import helpers.getMaxCoordinates

abstract class AbstractPlayerStrategy : PlayerStrategy {
    override var initialPosition: Coordinates = Coordinates()

    protected fun moveTowards(from: Coordinates, aim: Coordinates): Coordinates {
        return getMaxCoordinates(from, aim, moveDistanceByTurn)
    }

    protected fun shootTowards(from: Coordinates, aim: Coordinates, strength: ShootingStrength): Coordinates {
        val linearFunction = extractFunctionOfLine(from, aim)
        val variation = 10

        val trueX = when {
            aim.x < from.x -> aim.x - variation
            aim.x > from.x -> aim.x + variation
            else -> aim.x
        }

        val trueY = linearFunction(trueX)

        return getMaxCoordinates(from, Coordinates(trueX, trueY), shootingDistance * strength.strengthMultiplier)
    }

    protected fun getOpponentGoalsCenter(player: Player): Coordinates {
        return when (player.team.gameSide) {
            HOME -> getGoalCenter(AWAY)
            else -> getGoalCenter(HOME)
        }
    }

    protected fun getGoalCenter(gameSide: GameSide): Coordinates {
        return when (gameSide) {
            HOME -> Coordinates(0.0, fieldHalfHeight)
            else -> Coordinates(fieldTotalWidth, fieldHalfHeight)
        }
    }

    protected fun isInOpponentSurface(player: Player): Boolean {
        val isInSurfaceByX = when (player.team.gameSide) {
            HOME -> player.position.x > FieldContext.rightSurface.x
            AWAY -> player.position.x < FieldContext.leftSurface.width
        }

        val isInSurfaceByY = player.position.y > FieldContext.rightSurface.y
                && player.position.y < FieldContext.rightSurface.height

        return isInSurfaceByX && isInSurfaceByY
    }

    abstract fun setInitialX(gameSide: GameSide): Double
    abstract fun setInitialY(): Double

    override fun toString(): String = "${javaClass.simpleName} $side"
}