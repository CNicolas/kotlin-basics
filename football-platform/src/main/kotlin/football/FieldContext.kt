package football

import helpers.Coordinates
import javafx.scene.paint.Color
import javafx.scene.shape.Circle
import javafx.scene.shape.Line
import javafx.scene.shape.Rectangle

class FieldContext {
    companion object {
        val fieldTotalWidth: Double = 500.0
        val fieldHalfWidth: Double = fieldTotalWidth / 2

        val fieldTotalHeight: Double = 300.0
        val fieldHalfHeight: Double = fieldTotalHeight / 2

        val surfaceHeight: Double = 150.0
        val surfaceWidth: Double = surfaceHeight / 2

        val cageHeight: Double = 50.0
        val cageWidth: Double = cageHeight / 2

        val movingSpeed = 200.0
        val shootingDistance = 50.0
        val moveDistanceByTurn = 50.0
        val maxDistanceToTouch = 5

        val grassColor = Color.FORESTGREEN!!
        private val linesColor = Color.WHITE!!

        val mediane = Line(fieldHalfWidth,
                0.0,
                fieldHalfWidth,
                fieldTotalHeight)

        val centerRing = Circle(fieldHalfWidth,
                fieldHalfHeight,
                fieldTotalHeight / 6,
                Color.TRANSPARENT)

        val leftSurface = Rectangle(-1.0,
                fieldHalfHeight - surfaceWidth,
                surfaceWidth + 15,
                surfaceHeight)
        val rightSurface = Rectangle(fieldTotalWidth - (surfaceWidth - 1 + 15),
                fieldHalfHeight - surfaceWidth,
                surfaceWidth + 15,
                surfaceHeight)

        val leftCage = Rectangle(-(cageWidth / 2),
                fieldHalfHeight - cageWidth,
                cageWidth,
                cageHeight)
        val rightCage = Rectangle(fieldTotalWidth - (cageWidth - cageWidth / 2),
                fieldHalfHeight - cageWidth,
                cageWidth,
                cageHeight)

        val ballInitialPosition = Coordinates(fieldHalfWidth, fieldHalfHeight)

        init {
            mediane.stroke = linesColor

            centerRing.stroke = linesColor

            leftSurface.stroke = linesColor
            leftSurface.strokeWidth = 1.0
            leftSurface.fill = Color.TRANSPARENT

            rightSurface.stroke = linesColor
            rightSurface.strokeWidth = 1.0
            rightSurface.fill = Color.TRANSPARENT

            leftCage.stroke = linesColor
            leftCage.strokeWidth = 2.0
            leftCage.fill = Color.TRANSPARENT

            rightCage.stroke = linesColor
            rightCage.strokeWidth = 2.0
            rightCage.fill = Color.TRANSPARENT
        }
    }
}