package football.game

import football.player.Team
import org.testng.annotations.Test

class TournamentTest {
    @Test
    fun should_initialize_tournament() {
        val tournament = Tournament()
        val games = tournament.createTournament(5)

        games.map { game: Pair<Team, Team> -> "${game.first.strategies} vs ${game.second.strategies}" }
                .forEach { println(it) }
    }
}