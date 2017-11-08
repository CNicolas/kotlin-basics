package helpers

fun calculateFunctionOfX(x: Double, coef: Double, originOrdinate: Double) = coef * x + originOrdinate

fun extractFunctionOfLine(from: Coordinates, to: Coordinates): (Double) -> Double {
    val coef = (from.y - to.y) / (from.x - to.x)
    val originOrdinate = from.y - (coef * from.x)

    return { x -> coef * x + originOrdinate }
}