package football.player.strategy.simple.attack.dumbRushers

import football.Ball
import football.player.Player
import football.player.ShootingStrength
import football.player.strategy.AttackStrategy
import helpers.Coordinates

abstract class DumbRusher(private val strength: ShootingStrength) : AttackStrategy() {
    override fun move(player: Player): Coordinates {
        val destination = Ball.instance.position

        return moveTowards(player.position, destination)
    }

    override fun shoot(player: Player): Coordinates {
        val destination = getOpponentGoalsCenter(player)

        return shootTowards(player.position, destination, strength)
    }
}