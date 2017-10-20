package football

import javafx.application.Application
import javafx.fxml.FXMLLoader
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.scene.paint.Color.FORESTGREEN
import javafx.stage.Stage

class FootballFXMLApp : Application() {

    fun launchLaunch(args: Array<String>) {
        launch(*args)
    }

    override fun start(primaryStage: Stage?) {
        val root = FXMLLoader.load<Parent>(javaClass.classLoader.getResource("field.fxml"))

        primaryStage?.title = "Football"
        primaryStage?.scene = Scene(root, 500.0, 300.0, FORESTGREEN)
        primaryStage?.show()
    }
}