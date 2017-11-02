package football.game

import football.player.Team
import football.strategy.DoesNothing
import football.strategy.DumbRusherPushBall
import football.strategy.FixedGoal
import helpers.GameSide
import helpers.SideInTeam
import javafx.scene.paint.Color
import org.assertj.core.api.Assertions.assertThat
import org.testng.annotations.Test

class GameRunnerTest {
    @Test
    fun simpleTest() {
        val team1 = Team(Color.BLUE, GameSide.HOME, FixedGoal(), DumbRusherPushBall(SideInTeam.UP))
        val team2 = Team(Color.RED, GameSide.AWAY, DoesNothing(SideInTeam.UP), DoesNothing(SideInTeam.DOWN))


        val runner = GameRunner(team1, team2)
        runner.play()

        assertThat(team1.score).isEqualTo(3)
        assertThat(team2.score).isEqualTo(0)
    }
}