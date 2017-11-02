package helpers

import football.game.Ball
import football.player.Player

fun distance(from: Coordinates, to: Coordinates): Double {
    return Math.sqrt(Math.pow(to.x - from.x, 2.0) + Math.pow(to.y - from.y, 2.0))
}

fun getMaxCoordinates(from: Coordinates, aim: Coordinates, maxDistance: Double): Coordinates {
    var toX = from.x
    var toY = from.y

    for (count in 0 until 1000) {
        val currentDistanceTowardsObjective = distance(from, Coordinates(toX, toY))

        val isArrived = Math.abs(maxDistance - currentDistanceTowardsObjective) < 2.0 || Coordinates(toX, toY) == aim
        if (isArrived) {
            return Coordinates(toX, toY)
        }

        if (toX < aim.x) toX += 1
        if (toX > aim.x) toX -= 1
        if (toY < aim.y) toY += 1
        if (toY > aim.y) toY -= 1
    }

    return Coordinates(toX, toY)
}

fun hasBall(player: Player): Boolean {
    val maxDistanceToTouch = 15
    val diffX = Math.abs(player.position.x - Ball.instance.position.x)
    val diffY = Math.abs(player.position.y - Ball.instance.position.y)

    return diffX < maxDistanceToTouch && diffY < maxDistanceToTouch
}