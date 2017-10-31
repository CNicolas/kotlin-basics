package football

import javafx.scene.paint.Color
import javafx.scene.shape.Circle
import javafx.scene.shape.Line
import javafx.scene.shape.Rectangle

class FieldContext {
    companion object {
        val width: Double = 500.0
        val height: Double = 300.0

        val movingSpeed = 100.0
        val shootingDistance = 50.0
        val moveDistanceByTurn = 75.0

        val grassColor = Color.FORESTGREEN
        val linesColor = Color.WHITE

        val mediane = Line(width / 2, 0.0, width / 2, height)
        val centerRing = Circle(width / 2, height / 2, 50.0, Color.TRANSPARENT)
        val leftSurface = Rectangle(-1.0, 100.0, 50.0, 100.0)
        val rightSurface = Rectangle(width - 49.0, 100.0, 50.0, 100.0)

        init {
            mediane.stroke = linesColor

            centerRing.stroke = linesColor

            leftSurface.stroke = linesColor
            leftSurface.strokeWidth = 1.0
            leftSurface.fill = Color.TRANSPARENT

            rightSurface.stroke = linesColor
            rightSurface.strokeWidth = 1.0
            rightSurface.fill = Color.TRANSPARENT

            println("initialize fieldContext")
        }
    }
}