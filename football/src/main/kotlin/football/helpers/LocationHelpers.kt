package football.helpers

fun distance(fromX: Double, fromY: Double, toX: Double, toY: Double): Double {
    return Math.sqrt(Math.pow(toX - fromX, 2.0) + Math.pow(toY - fromY, 2.0))
}