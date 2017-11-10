package football.player.strategy.midfield

import football.Ball
import football.FieldContext
import football.game.GameSide
import football.player.Player
import football.player.ShootingStrength
import football.player.SideInTeam
import football.player.strategy.AbstractPlayerStrategy
import helpers.Coordinates
import helpers.getMaxCoordinates

class StayAtShootDistanceOfTheBall : AbstractPlayerStrategy() {
    override val side: SideInTeam = SideInTeam.CENTER

    override fun move(player: Player): Coordinates {
        val destination =
                getMaxCoordinates(Ball.instance.position, player.position, ShootingStrength.SHOOT.distance)

        return moveTowards(player.position, destination)
    }

    override fun shoot(player: Player): Coordinates {
        val destination = getOpponentGoalsCenter(player)

        return shootTowards(player.position, destination, ShootingStrength.CLEARANCE)
    }

    override fun setInitialX(gameSide: GameSide): Double {
        val distanceFromFieldCenter = FieldContext.fieldTotalWidth / 5
        return when (gameSide) {
            GameSide.HOME -> FieldContext.fieldHalfWidth - distanceFromFieldCenter
            else -> FieldContext.fieldTotalWidth + distanceFromFieldCenter
        }
    }

    override fun setInitialY(): Double = FieldContext.fieldHalfHeight
}
