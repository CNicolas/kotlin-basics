package football.player.strategy.simple.attack.runAndShoot.shootOblique

import football.Ball
import football.FieldContext
import football.player.Player
import football.player.ShootingStrength
import football.player.SideInTeam
import football.player.SideInTeam.*
import football.player.strategy.AttackStrategy
import helpers.Coordinates
import java.util.*

class RunZigZag(override val side: SideInTeam) : AttackStrategy() {
    private var direction = when (side) {
        DOWN -> true
        UP -> false
        CENTER -> Random().nextBoolean()
    }

    override fun move(player: Player): Coordinates {
        val destination = Ball.instance.position

        return moveTowards(player.position, destination)
    }

    override fun shoot(player: Player): Coordinates {
        val opponentsGoalCenter = getOpponentGoalsCenter(player)

        val destinationY = when (isInOpponentSurface(player)) {
            true -> when (direction) {
                true -> opponentsGoalCenter.y + (FieldContext.cageSize / 3)
                false -> opponentsGoalCenter.y - (FieldContext.cageSize / 3)
            }
            false -> when (direction) {
                true -> opponentsGoalCenter.y + FieldContext.cageSize
                false -> opponentsGoalCenter.y - FieldContext.cageSize
            }
        }
        direction = !direction

        val aim = Coordinates(opponentsGoalCenter.x, destinationY)
        return shootTowards(player.position, aim, ShootingStrength.NORMAL)
    }
}