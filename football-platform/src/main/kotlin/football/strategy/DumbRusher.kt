package football.strategy

import football.FieldContext
import football.game.Ball
import football.player.Player
import helpers.Coordinates
import helpers.ShootingStrength
import helpers.distanceTowards
import helpers.getOpponentGoalsCenter

class DumbRusher : PlayerStrategy {
    override fun move(player: Player): Coordinates {
        val destination = Ball.instance.position

        return distanceTowards(player.position, destination)
    }

    override fun shoot(player: Player): Coordinates {
        val destination = getOpponentGoalsCenter(player)
        val shootingDistance = FieldContext.shootingDistance * ShootingStrength.RUN.strengthPercentage

        return distanceTowards(player.position, destination, shootingDistance)
    }
}