package football

import helpers.Coordinates
import javafx.scene.paint.Color
import javafx.scene.shape.Circle

class Ball private constructor() {
    val circle: Circle = Circle(0.0, 0.0, 5.0, Color.YELLOW)
    var position: Coordinates = Coordinates()
        set(value) {
            field = value
            circle.translateX = field.x
            circle.translateY = field.y
        }

    init {
        position = Coordinates(football.FieldContext.width / 2, FieldContext.height / 2)
    }

    private object Holder {
        val INSTANCE = Ball()
    }

    companion object {
        val instance: Ball by lazy { Holder.INSTANCE }
    }
}