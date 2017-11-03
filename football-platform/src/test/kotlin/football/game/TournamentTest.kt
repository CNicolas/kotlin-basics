package football.game

import main.Tournament
import org.testng.annotations.Test

class TournamentTest {
    @Test
    fun should_initialize_tournament_of_5_games() {
        val tournament = Tournament()
        val games = tournament.createTournament(5)

        games.map { game: Game -> "${game.home.strategies} vs ${game.away.strategies}" }
                .forEach { println(it) }
    }
}