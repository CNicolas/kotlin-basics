package football

import football.controllers.FieldController
import football.game.Game
import football.game.Player
import football.game.Team
import javafx.application.Application
import javafx.fxml.FXMLLoader
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.scene.paint.Color
import javafx.scene.paint.Color.FORESTGREEN
import javafx.stage.Stage

class FootballFXMLApp : Application() {
    private val game: Game

    init {
        val team1 = Team(Color.BLUE, Player(150.0, 75.0), Player(150.0, 225.0))
        val team2 = Team(Color.RED, Player(350.0, 75.0), Player(350.0, 225.0))

        game = Game(team1, team2)
    }

    fun launchLaunch(args: Array<String>) {
        launch(*args)
    }

    override fun start(primaryStage: Stage?) {
        val fxmlLoader = FXMLLoader(javaClass.classLoader.getResource("field.fxml"))
        val root = fxmlLoader.load<Parent>()

        val controller = fxmlLoader.getController<FieldController>()
        controller.game = game
        controller.initializePlayersPositionAndColors()

        primaryStage?.title = "Football"
        primaryStage?.scene = Scene(root, 500.0, 300.0, FORESTGREEN)
        primaryStage?.show()
    }
}