package helpers

import football.Ball
import football.FieldContext
import football.player.Player
import java.util.*

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

fun hasBall(player: Player): Boolean {
    val maxDistanceToTouch = 5
    val diffX = Math.abs(player.position.x - Ball.instance.position.x)
    val diffY = Math.abs(player.position.y - Ball.instance.position.y)

    return diffX < maxDistanceToTouch && diffY < maxDistanceToTouch
}

fun getOpponentGoalsCenter(player: Player): Coordinates {
    return when (player.team.side) {
        Side.LEFT -> getGoalCenter(Side.RIGHT)
        else -> getGoalCenter(Side.LEFT)
    }
}

fun getGoalCenter(side: Side): Coordinates {
    return when (side) {
        Side.LEFT -> Coordinates(0.0, FieldContext.height / 2)
        else -> Coordinates(FieldContext.width, FieldContext.height / 2)
    }
}