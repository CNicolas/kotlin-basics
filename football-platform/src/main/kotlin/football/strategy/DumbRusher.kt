package football.strategy

import football.game.Ball
import football.player.Player
import helpers.Coordinates
import helpers.getOpponentGoalsCenter
import helpers.moveTowards

class DumbRusher : PlayerStrategy {
    override fun move(player: Player): Coordinates {
        val destination = Ball.instance.position
        return moveTowards(player.position, destination)
    }

    override fun shoot(player: Player): Coordinates = getOpponentGoalsCenter(player)
}