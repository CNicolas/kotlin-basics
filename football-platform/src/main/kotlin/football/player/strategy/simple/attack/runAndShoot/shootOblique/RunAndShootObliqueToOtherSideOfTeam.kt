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

class RunAndShootObliqueToOtherSideOfTeam(override val side: SideInTeam) : AttackStrategy() {
    override fun move(player: Player): Coordinates {
        val destination = Ball.instance.position

        return moveTowards(player.position, destination)
    }

    override fun shoot(player: Player): Coordinates {
        val variation = FieldContext.cageHalfSize
        val destinationVariation = Random().nextBoolean()
        val opponentsGoalCenter = getOpponentGoalsCenter(player)

        val destinationY = when {
            side == UP -> opponentsGoalCenter.y + variation
            side == DOWN -> opponentsGoalCenter.y - variation
            side == CENTER && destinationVariation -> opponentsGoalCenter.y + variation
            side == CENTER && !destinationVariation -> opponentsGoalCenter.y - variation
            else -> opponentsGoalCenter.y
        }

        val strength = when (isInOpponentSurface(player)) {
            true -> ShootingStrength.SHOOT
            false -> ShootingStrength.RUN
        }

        val aim = Coordinates(opponentsGoalCenter.x, destinationY)
        return shootTowards(player.position, aim, strength)
    }
}