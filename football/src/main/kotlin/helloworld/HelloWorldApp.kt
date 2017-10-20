package helloworld

import javafx.application.Application
import javafx.geometry.Pos
import javafx.scene.Scene
import javafx.scene.control.Button
import javafx.scene.layout.GridPane
import javafx.stage.Stage

class HelloWorldApp : Application() {
    fun launchLaunch(args: Array<String>) {
        launch(*args)
    }

    override fun start(primaryStage: Stage?) {
        val btn = Button()
        btn.text = "Say 'Hello World'"
        btn.setOnAction { print("Hello World\n") }

        val rootPane = GridPane()
        rootPane.alignment = Pos.TOP_LEFT
        rootPane.add(btn, 0, 0, 2, 1)
        rootPane.add(Button("Another Button's text"), 3, 1, 3, 2)

        primaryStage?.title = "Hello World !"
        primaryStage?.scene = Scene(rootPane, 500.0, 300.0)
        primaryStage?.show()
    }
}