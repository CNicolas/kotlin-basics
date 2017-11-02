package football.strategy

import football.player.Player
import helpers.Coordinates

class DoesNothing : PlayerStrategy {
    override fun move(player: Player): Coordinates = player.position

    override fun shoot(player: Player): Coordinates = player.position
}