package football

import javafx.application.Application
import javafx.scene.Scene
import javafx.scene.layout.BorderPane
import javafx.scene.paint.Color.*
import javafx.scene.shape.Circle
import javafx.scene.shape.Line
import javafx.scene.shape.Rectangle
import javafx.stage.Stage


class FootballApp : Application() {
    private val grassColor = FORESTGREEN
    private val linesColor = WHITE

    fun launchLaunch(args: Array<String>) {
        launch(*args)
    }

    override fun start(primaryStage: Stage?) {
        val rootPane = BorderPane()

        val mediane = Line(250.0, 0.0, 250.0, 300.0)
        mediane.stroke = linesColor

        val centerRing = Circle(250.0, 150.0, 50.0, TRANSPARENT)
        centerRing.stroke = linesColor

        val leftSurface = Rectangle(50.0, 100.0, TRANSPARENT)
        leftSurface.stroke = linesColor
        leftSurface.strokeWidth = 1.0
        leftSurface.x = -1.0
        leftSurface.y = 100.0

        val rightSurface = Rectangle(50.0, 100.0, TRANSPARENT)
        rightSurface.stroke = linesColor
        rightSurface.strokeWidth = 1.0
        rightSurface.x = 451.0
        rightSurface.y = 100.0

        rootPane.children.add(mediane)
        rootPane.children.add(centerRing)
        rootPane.children.add(leftSurface)
        rootPane.children.add(rightSurface)

        primaryStage?.title = "Football"
        primaryStage?.scene = Scene(rootPane, 500.0, 300.0, grassColor)
        primaryStage?.show()
    }
}