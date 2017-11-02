package football.strategy

import football.player.Player
import helpers.Coordinates
import helpers.GameSide
import helpers.SideInTeam

interface PlayerStrategy {
    val side: SideInTeam
    var initialPosition: Coordinates

    fun move(player: Player): Coordinates
    fun shoot(player: Player): Coordinates

    fun setInitialPosition(gameSide: GameSide): Coordinates
}