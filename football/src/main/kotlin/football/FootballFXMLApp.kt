package football

import football.controllers.FieldController
import football.game.Game
import football.game.Game.Companion.createGame
import football.game.Team
import football.game.strategies.DumbRusher
import football.game.strategies.StandStill
import football.helpers.Side
import javafx.application.Application
import javafx.fxml.FXMLLoader
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.scene.paint.Color
import javafx.scene.paint.Color.FORESTGREEN
import javafx.stage.Stage

class FootballFXMLApp : Application() {
    init {
        val team1 = Team(Color.BLUE, Side.LEFT)
        team1.player1 = DumbRusher(team1)
        team1.player2 = StandStill(team1)
        val team2 = Team(Color.RED, Side.RIGHT)
        team2.player1 = StandStill(team2)
        team2.player2 = StandStill(team2)
        //, StandStill(Side.RIGHT), StandStill(Side.RIGHT))
        createGame(team1, team2)
    }

    fun launchLaunch(args: Array<String>) {
        launch(*args)
    }

    override fun start(primaryStage: Stage?) {
        val fxmlLoader = FXMLLoader(javaClass.classLoader.getResource("field.fxml"))
        val root = fxmlLoader.load<Parent>()

        val controller = fxmlLoader.getController<FieldController>()
        controller.initializePlayersPositionAndColors()

        Game.instance.fieldController = controller

        primaryStage?.title = "Football"
        primaryStage?.scene = Scene(root, 500.0, 300.0, FORESTGREEN)
        primaryStage?.show()
    }
}