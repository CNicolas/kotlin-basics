package main.tournament

import football.game.GameSide
import football.game.Score
import football.game.Team
import football.player.SideInTeam
import football.player.SideInTeam.*
import football.player.strategy.DoesNothing
import football.player.strategy.PlayerStrategy
import football.player.strategy.attack.dumbRushers.DumbRusherNormal
import football.player.strategy.attack.dumbRushers.DumbRusherRun
import football.player.strategy.attack.dumbRushers.DumbRusherShoot
import football.player.strategy.attack.runAndShoot.PushBallAndShootStraight
import football.player.strategy.attack.runAndShoot.RunAndShootStraight
import football.player.strategy.defense.DefenderFollowingBall
import football.player.strategy.defense.FixedGoalKeeper
import javafx.scene.paint.Color
import main.GameRunner
import java.util.*

class Tournament {
    fun playTournament(teams: List<Team>): TournamentLeaderBoard {
        val leaderBoard = TournamentLeaderBoard(teams)

        for (homeIndex in 0 until teams.size) {
            for (awayIndex in 0 until teams.size) {
                if (homeIndex != awayIndex) {
                    teams[homeIndex].gameSide = GameSide.HOME
                    val home = teams[homeIndex].clone()
                    home.resetPositions()

                    teams[awayIndex].gameSide = GameSide.AWAY
                    val away = teams[awayIndex].clone()
                    away.resetPositions()

                    val runner = GameRunner(home, away)
                    val score = runner.play()

                    when (score) {
                        Score.HOME_WON -> leaderBoard.win(homeIndex)
                        Score.AWAY_WON -> leaderBoard.win(awayIndex)
                        else -> leaderBoard.draw(homeIndex, awayIndex)
                    }

                    leaderBoard.gamesPlayed++
                }
            }
        }

        return leaderBoard
    }

    fun createTournament(teamsCount: Int): List<Team> {
        val r = Random()

        val listOfTeams = mutableListOf<Team>()

        for (i in 0 until teamsCount) {
            val team = Team(Color.BLACK, createRandomTeam(r.nextInt(4)))

            if (!listOfTeams.any { areTeamsEqual(it, team) }) {
                listOfTeams.add(team)
            }
        }

        return listOfTeams
    }

    private fun createRandomTeam(numberOfPlayers: Int): List<PlayerStrategy> {
        val res = mutableListOf<PlayerStrategy>()

        for (i in 0..numberOfPlayers) {
            res.add(createRandomStrategy())
        }

        return res
    }

    private fun createRandomStrategy(): PlayerStrategy {
        val r = Random()

        val randomSideInTeam = SideInTeam.values()[r.nextInt(SideInTeam.values().size)]
        val strategyNumber = r.nextInt(20)

        return when (strategyNumber) {
            0 -> FixedGoalKeeper()
            1 -> DefenderFollowingBall()
            2 -> RunAndShootStraight(UP)
            3 -> RunAndShootStraight(CENTER)
            4 -> RunAndShootStraight(DOWN)
            5 -> PushBallAndShootStraight(UP)
            6 -> PushBallAndShootStraight(CENTER)
            7 -> PushBallAndShootStraight(DOWN)
            8 -> DumbRusherRun(UP)
            9 -> DumbRusherRun(CENTER)
            10 -> DumbRusherRun(DOWN)
            11 -> DumbRusherNormal(UP)
            12 -> DumbRusherNormal(CENTER)
            13 -> DumbRusherNormal(DOWN)
            14 -> DumbRusherShoot(UP)
            15 -> DumbRusherShoot(CENTER)
            16 -> DumbRusherShoot(DOWN)

            else -> DoesNothing(randomSideInTeam)
        }
    }

    private fun areTeamsEqual(teamHome: Team, teamAway: Team): Boolean {
        if (teamHome.strategies.size == teamAway.strategies.size) {
            val equalStrategies = (0 until teamHome.strategies.size).count {
                teamHome.strategies[it].javaClass.simpleName == teamAway.strategies[it].javaClass.simpleName
            }

            return equalStrategies == teamHome.strategies.size
        }

        return false
    }
}