package tennis

import org.assertj.core.api.Assertions.assertThat
import org.testng.annotations.Test

class GameTest {
    @Test
    fun should_be_love_love() {
        val player1: Player = Player("John")
        val player2: Player = Player("Jack")
        val game: Game = Game(player1, player2)

        assertThat(game.getGameScore()).isEqualTo("love, love")
    }

    @Test
    fun should_be_forty_thirty_for_John() {
        val player1: Player = Player("John")
        val player2: Player = Player("Jack")
        val game: Game = Game(player1, player2)

        player1.winBall()
        player1.winBall()
        player1.winBall()

        player2.winBall()
        player2.winBall()

        assertThat(game.getGameScore()).isEqualTo("forty, thirty")
    }

    @Test
    fun should_be_deuce() {
        val player1: Player = Player("John")
        val player2: Player = Player("Jack")
        val game: Game = Game(player1, player2)

        player1.winBall()
        player1.winBall()
        player1.winBall()

        player2.winBall()
        player2.winBall()
        player2.winBall()

        assertThat(game.getGameScore()).isEqualTo("deuce")
    }

    @Test
    fun should_be_advantage_John() {
        val player1: Player = Player("John")
        val player2: Player = Player("Jack")
        val game: Game = Game(player1, player2)

        player1.winBall()
        player1.winBall()
        player1.winBall()
        player1.winBall()

        player2.winBall()
        player2.winBall()
        player2.winBall()

        assertThat(game.getGameScore()).isEqualTo("advantage John")
    }
}