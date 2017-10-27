package football.game.strategies

import football.game.Coordinates
import football.game.Player
import football.game.Team

interface PlayerStrategy {
    val team: Team
    var player: Player
    val name: String

    var currentPosition: Coordinates
    val initialPosition: Coordinates

    fun move(): Coordinates
    fun shoot(): Coordinates
}