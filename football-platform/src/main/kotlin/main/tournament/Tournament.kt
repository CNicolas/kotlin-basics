package main.tournament

import football.game.FinalScoreStatus.AWAY_WON
import football.game.FinalScoreStatus.HOME_WON
import football.game.GameSide
import football.game.Team
import football.player.SideInTeam
import football.player.SideInTeam.*
import football.player.strategy.PlayerStrategy
import football.player.strategy.combined.attack.RunStraightAndCrossShot
import football.player.strategy.combined.attack.ZigZagAndCrossShot
import football.player.strategy.combined.midfield.RecoverAndShoot
import football.player.strategy.simple.DoesNothing
import football.player.strategy.simple.attack.camper.FollowBallHorizontally
import football.player.strategy.simple.attack.dumbRushers.DumbRusherNormal
import football.player.strategy.simple.attack.dumbRushers.DumbRusherRun
import football.player.strategy.simple.attack.dumbRushers.DumbRusherShoot
import football.player.strategy.simple.attack.runAndShoot.cross.CrossShot
import football.player.strategy.simple.attack.runAndShoot.cross.Overtake
import football.player.strategy.simple.attack.runAndShoot.cross.RunZigZag
import football.player.strategy.simple.attack.runAndShoot.shootStraight.PushBallAndShootStraight
import football.player.strategy.simple.attack.runAndShoot.shootStraight.RunAndShootStraight
import football.player.strategy.simple.defense.DefenderFollowingBall
import football.player.strategy.simple.defense.FixedGoalKeeper
import football.player.strategy.simple.defense.FollowClearBall
import football.player.strategy.simple.midfield.StayAtShootDistanceOfTheBall
import javafx.scene.paint.Color
import main.GameRunner
import java.util.*

class Tournament {
    private val strategiesCount = 35
    private val randomSideStrategiesCount = 16

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

            println("$homeIndex on ${teams.size}")
        }

        leaderBoard.orderDescendingByScore()

        return leaderBoard
    }

    fun createTournament(teamsCount: Int = Int.MAX_VALUE): List<Team> {
        val effectiveTeamsCount = Math.min(teamsCount, strategiesCount * 4)

        val listOfTeams = mutableListOf<Team>()
        while (listOfTeams.size < effectiveTeamsCount) {
            val team = Team(Color.BLACK, createRandomTeam(4))

            if (!listOfTeams.any { areTeamsEqual(it, team) }) {
                listOfTeams.add(team)
            }
        }

        return listOfTeams
    }

    fun createListOfTeams(): List<Team> {
        val listOfTeams = mutableListOf<Team>()

        // For One player
//        (0 until strategiesCount).mapTo(listOfTeams) { Team(Color.BLACK, listOf(createStrategyByNumber(it))) }

        // For 2 players
//        for (strategy1 in 0 until strategiesCount) {
//            (strategy1 until strategiesCount)
//                    .map { strategy2 -> listOf(createStrategyByNumber(strategy1), createStrategyByNumber(strategy2)) }
//                    .mapTo(listOfTeams) { Team(Color.BLACK, it) }
//        }

        // For 3 players
//        for (strategy1 in 0 until strategiesCount) {
//            for (strategy2 in strategy1 until strategiesCount) {
//                (strategy2 until strategiesCount)
//                        .map { strategy3 ->
//                            listOf(createStrategyByNumber(strategy1),
//                                    createStrategyByNumber(strategy2),
//                                    createStrategyByNumber(strategy3))
//                        }
//                        .mapTo(listOfTeams) { Team(Color.BLACK, it) }
//            }
//        }

        // For 4 players
        for (strategy1 in 0 until randomSideStrategiesCount) {
            for (strategy2 in strategy1 until randomSideStrategiesCount) {
                for (strategy3 in strategy2 until randomSideStrategiesCount) {
                    (strategy3 until randomSideStrategiesCount)
                            .map { strategy4 ->
                                listOf(createStrategyByNumber(strategy1),
                                        createStrategyByNumber(strategy2),
                                        createStrategyByNumber(strategy3),
                                        createStrategyByNumber(strategy4))
                            }
                            .mapTo(listOfTeams) { Team(Color.BLACK, it) }
                }
            }
        }

        return listOfTeams
    }

    private fun createRandomTeam(numberOfPlayers: Int): List<PlayerStrategy> {
        val res = mutableListOf<PlayerStrategy>()

        for (i in 0 until numberOfPlayers) {
            res.add(createRandomStrategy())
        }

        return res
    }

    private fun createRandomStrategy(): PlayerStrategy {
        val strategyNumber = Random().nextInt(strategiesCount)

        return createStrategyByNumberAndRandomSide(strategyNumber)
    }

    private fun createStrategyByNumber(strategyNumber: Int): PlayerStrategy {
        val randomSideInTeam = SideInTeam.values()[Random().nextInt(SideInTeam.values().size)]

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
            30 -> Overtake(UP)
            31 -> Overtake(DOWN)
            32 -> FollowBallHorizontally()
            33 -> FollowClearBall()
            34 -> RecoverAndShoot()

            else -> {
                DoesNothing(randomSideInTeam)
            }
        }
    }

    private fun createStrategyByNumberAndRandomSide(strategyNumber: Int): PlayerStrategy {
        val randomSideInTeam = SideInTeam.values()[Random().nextInt(SideInTeam.values().size)]

        return when (strategyNumber) {
            0 -> FixedGoalKeeper()
            1 -> DefenderFollowingBall()
            2 -> RunAndShootStraight(randomSideInTeam)
            3 -> PushBallAndShootStraight(randomSideInTeam)
            4 -> DumbRusherRun(randomSideInTeam)
            5 -> DumbRusherNormal(randomSideInTeam)
            6 -> DumbRusherShoot(randomSideInTeam)
            7 -> CrossShot(randomSideInTeam)
            8 -> RunZigZag(randomSideInTeam)
            9 -> StayAtShootDistanceOfTheBall()
            10 -> RunStraightAndCrossShot(randomSideInTeam)
            11 -> ZigZagAndCrossShot(randomSideInTeam)
            12 -> Overtake(randomSideInTeam)
            13 -> FollowBallHorizontally()
            14 -> FollowClearBall()
            15 -> RecoverAndShoot()

            else -> {
                DoesNothing(randomSideInTeam)
            }
        }
    }

    private fun areTeamsEqual(teamHome: Team, teamAway: Team): Boolean {
        if (teamHome.strategies.size == teamAway.strategies.size) {
            val equalStrategies = (0 until teamHome.strategies.size).count {
                teamHome.strategies[it].toString() == teamAway.strategies[it].toString()
            }

            return equalStrategies == teamHome.strategies.size
        }

        return false
    }
}