package main

import football.game.Game
import football.game.GameSide
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
import java.util.*

class Tournament {
    fun createTournament(gamesCount: Int): List<Game> {
        val r = Random()

        val listOfGames = mutableListOf<Game>()

        for (i in 0 until gamesCount) {
            val home = Team(Color.BLUE, GameSide.HOME, createRandomTeam(r.nextInt(4)))
            val away = Team(Color.RED, GameSide.AWAY, createRandomTeam(r.nextInt(4)))
            listOfGames.add(Game(home, away))
        }

        return listOfGames
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
}