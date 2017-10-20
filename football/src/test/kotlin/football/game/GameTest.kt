package football.game

import javafx.scene.paint.Color
import org.assertj.core.api.Assertions.assertThat
import org.testng.annotations.Test

class GameTest {
    @Test
    fun should_initialize() {
        val team1 = Team(Color.BLUE, Player())
        val team2 = Team(Color.BLUE, Player())

        assertThat(Game(team1, team2)).isNotNull()
    }
}