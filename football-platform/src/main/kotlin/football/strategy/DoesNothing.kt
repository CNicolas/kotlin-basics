package football.strategy

import football.FieldContext
import football.player.Player
import helpers.Coordinates
import helpers.GameSide
import helpers.SideInTeam

class DoesNothing(override val side: SideInTeam) : AbstractPlayerStrategy() {
    override var initialPosition = Coordinates()

    init {
        val x = FieldContext.width / 4
        var y = FieldContext.height / 2

        if (side == SideInTeam.UP) {
            y = FieldContext.height / 4
        } else if (side == SideInTeam.DOWN) {
            y = (3 * FieldContext.height) / 4
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

    private fun setInitialX(gameSide: GameSide): Double {
        return when (gameSide) {
            GameSide.HOME -> FieldContext.width / 4
            else -> (3 * FieldContext.width) / 4
        }
    }

    private fun setInitialY(): Double {
        return when (side) {
            SideInTeam.UP -> FieldContext.height / 4
            SideInTeam.DOWN -> (3 * FieldContext.height) / 4
            else -> FieldContext.width / 2
        }
    }
}