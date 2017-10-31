package football.strategy

import football.player.Player
import helpers.Coordinates

interface PlayerStrategy {
    fun move(player: Player): Coordinates
    fun shoot(player: Player): Coordinates
}