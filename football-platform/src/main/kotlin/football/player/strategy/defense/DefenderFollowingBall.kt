package football.player.strategy.defense

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

    override fun setInitialPosition(gameSide: GameSide): Coordinates {
        val x = setInitialX(gameSide)
        val y = setInitialY()

        initialPosition = Coordinates(x, y)

        return initialPosition
    }

    override fun setInitialX(gameSide: GameSide): Double {
        return when (gameSide) {
            GameSide.HOME -> 100.0
            else -> FieldContext.width - 100.0
        }
    }

    override fun setInitialY(): Double = FieldContext.height / 2
}