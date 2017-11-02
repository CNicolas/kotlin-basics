package football.strategy

import football.FieldContext
import football.player.Player
import helpers.Coordinates
import helpers.GameSide
import helpers.ShootingStrength
import helpers.getMaxCoordinates

abstract class AbstractPlayerStrategy : PlayerStrategy {
    protected fun moveTowards(from: Coordinates, aim: Coordinates): Coordinates {
        return getMaxCoordinates(from, aim, FieldContext.moveDistanceByTurn)
    }

    protected fun shootTowards(from: Coordinates, aim: Coordinates, strength: ShootingStrength): Coordinates {
        return getMaxCoordinates(from, aim, FieldContext.shootingDistance * strength.strengthPercentage)
    }

    protected fun getOpponentGoalsCenter(player: Player): Coordinates {
        return when (player.team.gameSide) {
            GameSide.HOME -> getGoalCenter(GameSide.AWAY)
            else -> getGoalCenter(GameSide.HOME)
        }
    }

    protected fun getGoalCenter(gameSide: GameSide): Coordinates {
        return when (gameSide) {
            GameSide.HOME -> Coordinates(0.0, FieldContext.height / 2)
            else -> Coordinates(FieldContext.width, FieldContext.height / 2)
        }
    }
}