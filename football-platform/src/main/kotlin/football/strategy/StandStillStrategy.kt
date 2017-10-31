package football.strategy

import football.player.Player
import helpers.Coordinates
import helpers.getOpponentGoalsCenter

class StandStillStrategy : PlayerStrategy {
    override fun move(player: Player): Coordinates = player.position

    override fun shoot(player: Player): Coordinates = getOpponentGoalsCenter(player)
}