package football.helpers

import football.game.Coordinates
import java.util.*

enum class Side { LEFT, RIGHT }

fun distance(from: Coordinates, to: Coordinates): Double {
    return Math.sqrt(Math.pow(to.x - from.x, 2.0) + Math.pow(to.y - from.y, 2.0))
}

fun moveTowards(from: Coordinates, aim: Coordinates, maxDistance: Double): Coordinates {
    val r = Random()
    var toX = from.x
    var toY = from.y

    for (count in 0 until 1000) {
        val currentDistanceTowardsObjective = distance(from, Coordinates(toX, toY))

        val isArrived = Math.abs(maxDistance - currentDistanceTowardsObjective) <= 5
        if (isArrived) {
            return Coordinates(toX, toY)
        }

        when {
            toX < aim.x -> toX += r.nextInt(3)
            toX > aim.x -> toX -= r.nextInt(3)
            toY < aim.y -> toY += r.nextInt(3)
            toY > aim.y -> toY -= r.nextInt(3)
            else -> return Coordinates(toX, toY)
        }
    }

    return Coordinates(toX, toY)
}