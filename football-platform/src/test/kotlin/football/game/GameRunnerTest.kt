package football.game

import football.player.TeamFactory
import javafx.scene.paint.Color
import main.GameRunner
import org.assertj.core.api.Assertions.assertThat
import org.testng.annotations.Test

class GameRunnerTest {
    private val factory: TeamFactory = TeamFactory()

    @Test
    fun should_play_twice_exactly_the_same_game() {
        val team1 = factory.FixedGoalKeeper_DumbRusherRunWithBallUP(Color.BLUE, GameSide.HOME)
        val team2 = factory.DoesNothingUP_DoesNothingDOWN(Color.RED, GameSide.AWAY)

        val runner = GameRunner(team1, team2)
        runner.play()

        assertThat(team1.score).isEqualTo(3)
        assertThat(team2.score).isEqualTo(0)

        runner.play()

        assertThat(team1.score).isEqualTo(3)
        assertThat(team2.score).isEqualTo(0)
    }

    @Test
    fun full_DumbRusherRunWithBall_on_each_side() {
        val team1 = factory.DumbRusherRunUP_DumbRusherRunDOWN(Color.BLUE, GameSide.HOME)
        val team2 = factory.DumbRusherRunUP_DumbRusherRunDOWN(Color.RED, GameSide.AWAY)

        val runner = GameRunner(team1, team2)
        runner.play()

        assertThat(team1.score).isEqualTo(0)
        assertThat(team2.score).isEqualTo(0)
    }
}