package football.player.strategy

import football.FieldContext
import football.game.GameSide

abstract class DefenderStrategy : AbstractPlayerStrategy() {
    override fun setInitialX(gameSide: GameSide): Double {
        val distanceFromCage = FieldContext.fieldTotalWidth / 5

        return when (gameSide) {
            GameSide.HOME -> distanceFromCage
            else -> FieldContext.fieldTotalWidth - distanceFromCage
        }
    }

    override fun setInitialY(): Double = FieldContext.fieldHalfHeight
}