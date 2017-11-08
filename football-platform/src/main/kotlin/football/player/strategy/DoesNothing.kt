package football.player.strategy

import football.FieldContext
import football.game.GameSide
import football.player.Player
import football.player.SideInTeam
import helpers.Coordinates

class DoesNothing(override val side: SideInTeam) : AbstractPlayerStrategy() {
    init {
        val x = FieldContext.fieldTotalWidth / 4
        var y = FieldContext.fieldHalfHeight

        if (side == SideInTeam.UP) {
            y = FieldContext.fieldTotalHeight / 4
        } else if (side == SideInTeam.DOWN) {
            y = (3 * FieldContext.fieldTotalHeight) / 4
        }

        initialPosition = Coordinates(x, y)
    }

    override fun move(player: Player): Coordinates = player.position

    override fun shoot(player: Player): Coordinates = player.position

    override fun setInitialPosition(gameSide: GameSide): Coordinates {
        val x = setInitialX(gameSide)
        val y = setInitialY()

        initialPosition = Coordinates(x, y)

        return initialPosition
    }

    override fun setInitialX(gameSide: GameSide): Double {
        return when (gameSide) {
            GameSide.HOME -> FieldContext.fieldTotalWidth / 4
            else -> (3 * FieldContext.fieldTotalWidth) / 4
        }
    }

    override fun setInitialY(): Double {
        return when (side) {
            SideInTeam.UP -> FieldContext.fieldTotalHeight / 4
            SideInTeam.DOWN -> (3 * FieldContext.fieldTotalHeight) / 4
            else -> FieldContext.fieldHalfWidth
        }
    }
}