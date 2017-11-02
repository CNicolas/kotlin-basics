package football.game

import football.player.Team
import football.strategy.DoesNothing
import football.strategy.DumbRusherPushBall
import football.strategy.DumbRusherRunWithBall
import football.strategy.FixedGoal
import helpers.GameSide
import helpers.SideInTeam
import javafx.scene.paint.Color
import org.assertj.core.api.Assertions.assertThat
import org.testng.annotations.Test

class GameRunnerTest {
    @Test
    fun simple_test() {
        val team1 = Team(Color.BLUE, GameSide.HOME, listOf(FixedGoal(), DumbRusherPushBall(SideInTeam.UP)))
        val team2 = Team(Color.RED, GameSide.AWAY, listOf(DoesNothing(SideInTeam.UP), DoesNothing(SideInTeam.DOWN)))

        val runner = GameRunner(team1, team2)
        runner.play()

        assertThat(team1.score).isEqualTo(3)
        assertThat(team2.score).isEqualTo(0)
    }

    @Test
    fun full_DumbRusherRunWithBall_on_each_side() {
        val team1 = Team(Color.BLUE, GameSide.HOME, listOf(DumbRusherRunWithBall(SideInTeam.UP), DumbRusherRunWithBall(SideInTeam.DOWN)))
        val team2 = Team(Color.RED, GameSide.AWAY, listOf(DumbRusherRunWithBall(SideInTeam.UP), DumbRusherRunWithBall(SideInTeam.DOWN)))

        val runner = GameRunner(team1, team2)
        runner.play()

        assertThat(team1.score).isEqualTo(0)
        assertThat(team2.score).isEqualTo(0)
    }
}