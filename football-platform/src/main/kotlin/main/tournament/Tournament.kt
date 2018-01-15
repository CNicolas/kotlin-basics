package main.tournament

import football.game.FinalScoreStatus.AWAY_WON
import football.game.FinalScoreStatus.HOME_WON
import football.game.GameSide
import football.game.Team
import football.player.SideInTeam
import football.player.SideInTeam.*
import football.player.strategy.PlayerStrategy
import football.player.strategy.combinated.attack.RunStraightAndCrossShot
import football.player.strategy.combinated.attack.ZigZagAndCrossShot
import football.player.strategy.simple.DoesNothing
import football.player.strategy.simple.attack.dumbRushers.DumbRusherNormal
import football.player.strategy.simple.attack.dumbRushers.DumbRusherRun
import football.player.strategy.simple.attack.dumbRushers.DumbRusherShoot
import football.player.strategy.simple.attack.runAndShoot.cross.CrossShot
import football.player.strategy.simple.attack.runAndShoot.cross.RunZigZag
import football.player.strategy.simple.attack.runAndShoot.shootStraight.PushBallAndShootStraight
import football.player.strategy.simple.attack.runAndShoot.shootStraight.RunAndShootStraight
import football.player.strategy.simple.defense.DefenderFollowingBall
import football.player.strategy.simple.defense.FixedGoalKeeper
import football.player.strategy.simple.midfield.StayAtShootDistanceOfTheBall
import javafx.scene.paint.Color
import main.GameRunner
import java.util.*

class Tournament {
    fun playTournament(teams: List<Team>): LeaderBoard {
        val leaderBoard = LeaderBoard(teams)

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

                    when (score.status) {
                        HOME_WON -> {
                            leaderBoard.win(homeIndex)
                            leaderBoard.lose(awayIndex)
                        }
                        AWAY_WON -> {
                            leaderBoard.win(awayIndex)
                            leaderBoard.lose(homeIndex)
                        }
                        else -> {
                            leaderBoard.draw(homeIndex, awayIndex)
                        }
                    }
                    leaderBoard.addGoals(homeIndex, score.homeGoals, awayIndex, score.awayGoals)

                    leaderBoard.oneMoreGamePlayed()
                }
            }
        }

        leaderBoard.orderDescendingByScore()

        return leaderBoard
    }

    fun createTournament(teamsCount: Int = Int.MAX_VALUE): List<Team> {
        val r = Random()
        val effectiveTeamsCount = Math.min(teamsCount, 20 * 4)

        val listOfTeams = mutableListOf<Team>()
        while (listOfTeams.size < effectiveTeamsCount) {
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
        val strategyNumber = r.nextInt(30)

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
            17 -> CrossShot(UP)
            18 -> CrossShot(CENTER)
            19 -> CrossShot(DOWN)
            20 -> RunZigZag(UP)
            21 -> RunZigZag(CENTER)
            22 -> RunZigZag(DOWN)
            23 -> StayAtShootDistanceOfTheBall()
            24 -> RunStraightAndCrossShot(UP)
            25 -> RunStraightAndCrossShot(CENTER)
            26 -> RunStraightAndCrossShot(DOWN)
            27 -> ZigZagAndCrossShot(UP)
            28 -> ZigZagAndCrossShot(CENTER)
            29 -> ZigZagAndCrossShot(DOWN)

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