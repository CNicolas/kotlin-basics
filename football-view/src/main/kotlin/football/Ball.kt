package football

import helpers.Coordinates
import javafx.scene.paint.Color
import javafx.scene.shape.Circle

class Ball private constructor() {
    val circle: Circle = Circle(0.0, 0.0, 7.0, Color.YELLOW)
    var position: Coordinates = Coordinates(FieldContext.width / 2, FieldContext.height / 2)
        set(value) {
            field = value
            circle.translateX = field.x
            circle.translateY = field.y
        }

    private object Holder {
        val INSTANCE = Ball()
    }

    companion object {
        val instance: Ball by lazy { Holder.INSTANCE }
    }
}