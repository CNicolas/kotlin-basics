package football.player.strategy.simple.defense

import football.Ball
import football.FieldContext
import football.game.GameSide
import football.player.Player
import football.player.ShootingStrength
import football.player.SideInTeam
import football.player.strategy.AbstractPlayerStrategy
import helpers.Coordinates

class DefenderFollowingBall : AbstractPlayerStrategy() {
    override val side: SideInTeam = SideInTeam.CENTER

    override fun move(player: Player): Coordinates {
        val destination = Coordinates(initialPosition.x, Ball.instance.position.y)

        return moveTowards(player.position, destination)
    }

    override fun shoot(player: Player): Coordinates {
        val destination = getOpponentGoalsCenter(player)

        return shootTowards(player.position, destination, ShootingStrength.CLEARANCE)
    }

    override fun setInitialX(gameSide: GameSide): Double {
        val distanceFromCage = FieldContext.fieldTotalWidth / 5
        return when (gameSide) {
            GameSide.HOME -> distanceFromCage
            else -> FieldContext.fieldTotalWidth - distanceFromCage
        }
    }

    override fun setInitialY(): Double = FieldContext.fieldHalfHeight
}