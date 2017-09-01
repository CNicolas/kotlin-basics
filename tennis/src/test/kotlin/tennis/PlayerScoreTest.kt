package tennis

import org.assertj.core.api.Assertions.assertThat
import org.testng.annotations.Test

class PlayerScoreTest {
    @Test
    fun should_return_love_when_player_is_created() {
        val player: Player = Player()
        assertThat(player.score).isEqualTo(Score.LOVE)
    }

    @Test
    fun should_return_fifteen_when_player_win_a_ball() {
        val player: Player = Player()
        player.winBall()
        assertThat(player.score).isEqualTo(Score.FIFTEEN)
    }

    @Test
    fun should_return_thirty_when_player_win_a_ball_twice() {
        val player: Player = Player()
        player.winBall()
        player.winBall()
        assertThat(player.score).isEqualTo(Score.THIRTY)
    }

    @Test
    fun should_return_forty_when_player_win_a_ball_three_times() {
        val player: Player = Player()
        player.winBall()
        player.winBall()
        player.winBall()
        assertThat(player.score).isEqualTo(Score.FORTY)
    }
}