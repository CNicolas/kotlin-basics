package football

import football.game.Game
import football.game.Player
import football.game.Team
import javafx.application.Application
import javafx.fxml.FXMLLoader
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.scene.layout.AnchorPane
import javafx.scene.paint.Color
import javafx.scene.paint.Color.FORESTGREEN
import javafx.scene.shape.Circle
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
        val root = FXMLLoader.load<Parent>(javaClass.classLoader.getResource("field.fxml"))

        if (root is AnchorPane) {
            setupTeamPlayer(root, game.team1, 1, 1)
            setupTeamPlayer(root, game.team1, 1, 2)
            setupTeamPlayer(root, game.team2, 2, 1)
            setupTeamPlayer(root, game.team2, 2, 2)
        }

        primaryStage?.title = "Football"
        primaryStage?.scene = Scene(root, 500.0, 300.0, FORESTGREEN)
        primaryStage?.show()
    }

    private fun setupTeamPlayer(root: AnchorPane, team: Team, teamNumber: Int, playerNumber: Int) {
        val playerCircle = root.children.find { it.id == "team${teamNumber}player$playerNumber" } as Circle
        playerCircle.fill = team.color
        playerCircle.translateX = if (playerNumber == 1) team.player1.x else team.player2.x
        playerCircle.translateY = if (playerNumber == 1) team.player1.y else team.player2.y
    }
}