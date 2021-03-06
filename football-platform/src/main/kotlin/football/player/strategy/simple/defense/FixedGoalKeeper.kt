package football.player.strategy.simple.defense

import football.FieldContext
import football.game.GameSide
import football.player.Player
import football.player.ShootingStrength
import football.player.SideInTeam
import football.player.strategy.DefenderStrategy
import helpers.Coordinates

class FixedGoalKeeper : DefenderStrategy() {
    override val side: SideInTeam = SideInTeam.CENTER

    override fun moveWithoutBall(player: Player): Coordinates = moveTowards(player.position, initialPosition)

    override fun shoot(player: Player): Coordinates {
        val destination = getOpponentGoalsCenter(player)

        return shootTowards(player.position, destination, ShootingStrength.CLEARANCE)
    }

    override fun setInitialX(gameSide: GameSide): Double {
        val distanceFromCage = FieldContext.surfaceWidth / 3

        return when (gameSide) {
            GameSide.HOME -> distanceFromCage
            else -> FieldContext.fieldTotalWidth - distanceFromCage
        }
    }
}