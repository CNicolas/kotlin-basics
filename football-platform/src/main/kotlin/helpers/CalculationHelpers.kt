package helpers

fun distance(from: Coordinates, to: Coordinates): Double {
    return Math.sqrt(Math.pow(to.x - from.x, 2.0) + Math.pow(to.y - from.y, 2.0))
}

fun extractFunctionOfLine(from: Coordinates, to: Coordinates): (Double) -> Double {
    val coef = (from.y - to.y) / (from.x - to.x)
    val originOrdinate = from.y - (coef * from.x)

    return { x -> coef * x + originOrdinate }
}