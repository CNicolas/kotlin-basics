package football.strategy

import football.game.Ball
import football.player.Player
import helpers.Coordinates
import helpers.ShootingStrength

class DumbRusher : AbstractPlayerStrategy() {
    override fun move(player: Player): Coordinates {
        val destination = Ball.instance.position

        return moveTowards(player.position, destination)
    }

    override fun shoot(player: Player): Coordinates {
        val destination = getOpponentGoalsCenter(player)

        return shootTowards(player.position, destination, ShootingStrength.RUN)
    }
}