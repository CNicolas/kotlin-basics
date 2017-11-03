package football.game

import main.Tournament
import org.testng.annotations.Test

class TournamentTest {
    @Test
    fun should_initialize_tournament_of_5_games() {
        val tournament = Tournament()
        val teams = tournament.createTournament(5)

        teams.map { team: Team -> "${team.strategies}" }
                .forEach { println(it) }
    }
}