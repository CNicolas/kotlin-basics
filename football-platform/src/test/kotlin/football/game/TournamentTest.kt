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
        val leaderBoard = tournament.playTournament(listOf(loser, winner))
        println("Games played : ${leaderBoard.gamesPlayed}")
        leaderBoard.leaderBoard.map { leaderBoardElement -> "${leaderBoardElement.team.strategies} : ${leaderBoardElement.score}" }
                .forEach { println(it) }
    }

    @Test
    fun should_play_tournament_of_5_teams() {
        val tournament = Tournament()
        val teams = tournament.createTournament(5)

        val leaderBoard = tournament.playTournament(teams)
        leaderBoard.leaderBoard.map { leaderBoardElement -> "${leaderBoardElement.team.strategies} : ${leaderBoardElement.score}" }
                .forEach { println(it) }

        println()
        println("Games played : ${leaderBoard.gamesPlayed}, with ${leaderBoard.leaderBoard.size} teams")
        println("${leaderBoard.getWinner().team.strategies}")
    }

    @Test
    fun should_play_tournament_with_every_possible_team() {
        val tournament = Tournament()
        val teams = tournament.createTournament()

        val leaderBoard = tournament.playTournament(teams)
        leaderBoard.leaderBoard.map { leaderBoardElement -> "${leaderBoardElement.team.strategies} : ${leaderBoardElement.score}" }
                .forEach { println(it) }

        println()
        println("Games played : ${leaderBoard.gamesPlayed}, with ${leaderBoard.leaderBoard.size} teams")
        println("${leaderBoard.getWinner().team.strategies}")
    }
}