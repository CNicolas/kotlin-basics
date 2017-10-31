package football.player

import football.strategy.PlayerStrategy
import helpers.Coordinates
import javafx.scene.shape.Circle

class Player(val team: Team, val strategy: PlayerStrategy) {
    val circle: Circle = Circle(0.0, 0.0, 7.0, team.color)
    var position: Coordinates = Coordinates()
        set(value) {
            field = value
            circle.translateX = field.x
            circle.translateY = field.y
        }


    fun moveTo(): Coordinates = strategy.move(this)

    fun shootTo(): Coordinates = strategy.shoot(this)

    override fun toString(): String {
        return "Player(team=$team, position=$position)"
    }
}