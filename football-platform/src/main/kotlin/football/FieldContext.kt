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

        val surfaceSize: Double = 150.0
        val surfaceHalfSize: Double = surfaceSize / 2

        val cageSize: Double = 50.0
        val cageHalfSize: Double = cageSize / 2

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
                fieldHalfHeight - surfaceHalfSize,
                surfaceHalfSize + 15,
                surfaceSize)
        val rightSurface = Rectangle(fieldTotalWidth - (surfaceHalfSize - 1 + 15),
                fieldHalfHeight - surfaceHalfSize,
                surfaceHalfSize + 15,
                surfaceSize)

        val leftCage = Rectangle(-(cageHalfSize / 2),
                fieldHalfHeight - cageHalfSize,
                cageHalfSize,
                cageSize)
        val rightCage = Rectangle(fieldTotalWidth - (cageHalfSize - cageHalfSize / 2),
                fieldHalfHeight - cageHalfSize,
                cageHalfSize,
                cageSize)

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