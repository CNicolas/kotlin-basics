package football.ihm

import javafx.animation.TranslateTransition
import javafx.scene.Node
import javafx.util.Duration

class MovingTransitionWrapper(duration: Duration?, node: Node?) {
    val transition = TranslateTransition(duration, node)

    var toX
        get() = transition.toX
        set(value) {
            transition.toX = value
        }
    var toY
        get() = transition.toY
        set(value) {
            transition.toY = value
        }

    lateinit var afterFinished: () -> Unit

    fun play() = transition.play()
}