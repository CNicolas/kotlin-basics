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
        rootPane.alignment = Pos.CENTER
        rootPane.add(btn, 0, 1)

        primaryStage?.title = "Hello World !"
        primaryStage?.scene = Scene(rootPane, 300.0, 250.0)
        primaryStage?.show()
    }
}