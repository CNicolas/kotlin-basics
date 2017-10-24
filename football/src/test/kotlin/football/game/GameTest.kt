package football.game

import javafx.scene.paint.Color
import org.assertj.core.api.Assertions.assertThat
import org.testng.annotations.Test

class GameTest {
    @Test
    fun should_initialize() {
        val team1 = Team(Color.BLUE, Player(150.0, 75.0), Player(150.0, 225.0))
        val team2 = Team(Color.RED, Player(350.0, 75.0), Player(350.0, 225.0))

        assertThat(Game(team1, team2)).isNotNull()
    }
}