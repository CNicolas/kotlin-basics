package football.player.strategy.simple.midfield

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

    override fun moveWithoutBall(player: Player): Coordinates {
        val distanceOfTheBall = ShootingStrength.SHOOT.distance
        val toX = if (player.team.gameSide == GameSide.HOME) {
            Math.max(Ball.instance.position.x - distanceOfTheBall, FieldContext.surfaceHeight)
        } else {
            Math.min(Ball.instance.position.x + distanceOfTheBall, FieldContext.fieldTotalWidth)
        }

        val destination = getMaxCoordinates(Ball.instance.position, Coordinates(toX, Ball.instance.position.y), distanceOfTheBall)

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
            else -> FieldContext.fieldHalfWidth + distanceFromFieldCenter
        }
    }

    override fun setInitialY(): Double = FieldContext.fieldHalfHeight
}
