package football.game

import football.game.strategies.DumbRusher
import football.game.strategies.StandStill
import football.helpers.Side
import javafx.scene.paint.Color
import org.testng.annotations.BeforeTest
import org.testng.annotations.Test

class GameRunnerTest {

    @BeforeTest
    fun setup() {
        val team1 = Team(Color.BLUE, Side.LEFT)
        team1.player1 = DumbRusher(team1, Coordinates(0.0, 150.0))
        team1.player2 = StandStill(team1)
        val team2 = Team(Color.RED, Side.RIGHT)
        team2.player1 = StandStill(team2, Coordinates(0.0, 0.0))
        team2.player2 = DumbRusher(team2, Coordinates(0.0, 0.0))

        GameContext.createGame(team1, team2)
    }

    @Test
    fun should_start_and_finish() {
        val runner = GameRunner(turns = 10)
        runner.play()
    }
}