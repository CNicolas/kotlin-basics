package football.game

import football.player.SideInTeam
import football.player.strategy.DoesNothing
import football.player.strategy.attack.dumbRushers.DumbRusherRun
import javafx.scene.paint.Color
import main.tournament.Tournament
import org.testng.annotations.Test

class TournamentTest {
    @Test
    fun should_initialize_tournament_of_5_games() {
        val tournament = Tournament()
        val teams = tournament.createTournament(5)

        teams.map { team: Team -> "${team.strategies}" }
                .forEach { println(it) }
    }

    @Test
    fun play_with_2_teams_only_one_should_win() {
        val loser = Team(Color.BLUE, listOf(DoesNothing(SideInTeam.UP)))
        val winner = Team(Color.RED, listOf(DumbRusherRun(SideInTeam.CENTER)))

        val tournament = Tournament()
        val results = tournament.playTournament(listOf(loser, winner))
        println("Games played : ${results.first}")
        results.second.map { entry -> "${entry.key.strategies} : ${entry.value}" }
                .forEach { println(it) }
    }

    @Test
    fun should_play_tournament_of_5_games() {
        val tournament = Tournament()
        val teams = tournament.createTournament(5)

        val results = tournament.playTournament(teams)
        println("Games played : ${results.first}")
        results.second.map { entry -> "${entry.key.strategies} : ${entry.value}" }
                .forEach { println(it) }
    }

    @Test
    fun should_play_tournament_of_20_games_and_print_best_team() {
        val tournament = Tournament()
        val teams = tournament.createTournament(20)

        val results = tournament.playTournament(teams)
        println("Games played : ${results.first}")
//        results.second.map { entry -> "${entry.key.strategies} : ${entry.value}" }
//                .forEach { println(it) }

    }
}