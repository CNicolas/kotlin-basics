package football.player.strategy.simple.defense

import football.Ball
import football.FieldContext
import football.game.GameSide
import football.player.Player
import football.player.ShootingStrength
import football.player.SideInTeam
import football.player.strategy.AbstractPlayerStrategy
import helpers.Coordinates

class FollowClearBall : AbstractPlayerStrategy() {
    override val side: SideInTeam = SideInTeam.CENTER

    override fun moveWithoutBall(player: Player): Coordinates {
        val destination = when {
            isBallInHalfField(player) -> Ball.instance.position
            else -> Coordinates(initialPosition.x, Ball.instance.position.y)
        }
        return moveTowards(player.position, destination)
    }

    override fun shoot(player: Player): Coordinates {
        return shootTowards(player.position, getOpponentGoalsCenter(player), ShootingStrength.CLEARANCE)
    }

    private fun isBallInHalfField(player: Player): Boolean = this.isInTeamHalfField(player.team.gameSide, Ball.instance.position)

    override fun setInitialX(gameSide: GameSide): Double {
        val distanceFromCage = FieldContext.fieldTotalWidth / 5
        return when (gameSide) {
            GameSide.HOME -> distanceFromCage
            else -> FieldContext.fieldTotalWidth - distanceFromCage
        }
    }

    override fun setInitialY(): Double = FieldContext.fieldHalfHeight
}