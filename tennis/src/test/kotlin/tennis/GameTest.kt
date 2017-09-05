package tennis

import org.assertj.core.api.Assertions.assertThat
import org.testng.annotations.Test

class GameTest {
    @Test
    fun should_be_love_love() {
        val player1 = Player("John")
        val player2 = Player("Jack")
        val game = Game(player1, player2)

        assertThat(game.getGameScore()).isEqualTo("love, love")
    }

    @Test
    fun should_be_forty_thirty_for_John() {
        val player1 = Player("John")
        val player2 = Player("Jack")
        val game = Game(player1, player2)

        game.player1WinBall()
        game.player1WinBall()
        game.player1WinBall()

        game.player2WinBall()
        game.player2WinBall()

        assertThat(game.getGameScore()).isEqualTo("forty, thirty")
    }

    @Test
    fun should_be_deuce() {
        val player1 = Player("John")
        val player2 = Player("Jack")
        val game = Game(player1, player2)

        game.player1WinBall()
        game.player1WinBall()
        game.player1WinBall()

        game.player2WinBall()
        game.player2WinBall()
        game.player2WinBall()

        assertThat(game.getGameScore()).isEqualTo("deuce")
    }

    @Test
    fun John_should_have_advantage() {
        val player1 = Player("John")
        val player2 = Player("Jack")
        val game = Game(player1, player2)

        game.player1WinBall()
        game.player1WinBall()
        game.player1WinBall()

        game.player2WinBall()
        game.player2WinBall()
        game.player2WinBall()

        game.player1WinBall()

        assertThat(game.getGameScore()).isEqualTo("advantage John")
    }

    @Test
    fun John_should_have_won() {
        val player1 = Player("John")
        val player2 = Player("Jack")
        val game = Game(player1, player2)

        game.player1WinBall()
        game.player1WinBall()
        game.player1WinBall()
        game.player1WinBall()

        assertThat(game.getGameScore()).isEqualTo("John has won")
    }

    @Test
    fun John_should_win_after_winning_two_balls_when_Jack_had_advantage() {
        val player1 = Player("John")
        val player2 = Player("Jack")
        val game = Game(player1, player2)

        game.player1WinBall()
        game.player1WinBall()
        game.player1WinBall()

        game.player2WinBall()
        game.player2WinBall()
        game.player2WinBall()

        game.player1WinBall()
        game.player1WinBall()

        assertThat(game.getGameScore()).isEqualTo("John has won")
    }

    @Test
    fun should_be_deuce_after_advantage() {
        val player1 = Player("John")
        val player2 = Player("Jack")
        val game = Game(player1, player2)

        game.player1WinBall()
        game.player1WinBall()
        game.player1WinBall()

        game.player2WinBall()
        game.player2WinBall()
        game.player2WinBall()

        game.player1WinBall()
        assertThat(game.getGameScore()).isEqualTo("advantage John")

        game.player2WinBall()
        assertThat(game.getGameScore()).isEqualTo("deuce")
    }
}