package football.strategy.defense

import football.FieldContext
import football.player.Player
import football.strategy.AbstractPlayerStrategy
import helpers.Coordinates
import helpers.GameSide
import helpers.ShootingStrength
import helpers.SideInTeam

class FixedGoalKeeper : AbstractPlayerStrategy() {
    override val side: SideInTeam = SideInTeam.CENTER
    override var initialPosition = Coordinates()

    override fun move(player: Player): Coordinates = moveTowards(player.position, initialPosition)


    override fun shoot(player: Player): Coordinates {
        val destination = getOpponentGoalsCenter(player)

        return shootTowards(player.position, destination, ShootingStrength.CLEARANCE)
    }

    override fun setInitialPosition(gameSide: GameSide): Coordinates {
        val x = setInitialX(gameSide)
        val y = setInitialY()

        initialPosition = Coordinates(x, y)

        return initialPosition
    }

    override fun setInitialX(gameSide: GameSide): Double {
        return when (gameSide) {
            GameSide.HOME -> 30.0
            else -> FieldContext.width - 30.0
        }
    }

    override fun setInitialY(): Double = FieldContext.height / 2
}