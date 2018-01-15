package football.player.strategy

import football.FieldContext
import football.game.GameSide
import football.player.SideInTeam

abstract class AttackStrategy : AbstractPlayerStrategy() {
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