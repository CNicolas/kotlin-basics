package helloworld

import javafx.application.Application
import javafx.scene.Scene
import javafx.scene.control.Button
import javafx.scene.layout.StackPane
import javafx.stage.Stage

class HelloWorldApp : Application() {
    fun launchLaunch(args: Array<String>) {
        launch(*args)
    }

    override fun start(primaryStage: Stage?) {
        val btn = Button()
        btn.text = "Say 'Hello World'"
        btn.setOnAction { print("Hello World\n") }

        val rootPane = StackPane()
        rootPane.children.add(btn)

        primaryStage?.title = "Hello World !"
        primaryStage?.scene = Scene(rootPane, 300.0, 250.0)
        primaryStage?.show()
    }
}