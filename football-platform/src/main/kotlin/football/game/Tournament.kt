package football.game

import football.player.Team
import football.strategy.DoesNothing
import football.strategy.PlayerStrategy
import football.strategy.attack.dumbRushers.DumbRusherNormal
import football.strategy.attack.dumbRushers.DumbRusherRun
import football.strategy.attack.dumbRushers.DumbRusherShoot
import football.strategy.attack.runAndShoot.PushBallAndShootStraight
import football.strategy.attack.runAndShoot.RunAndShootStraight
import football.strategy.defense.DefenderFollowingBall
import football.strategy.defense.FixedGoalKeeper
import helpers.GameSide
import helpers.SideInTeam
import helpers.SideInTeam.*
import javafx.scene.paint.Color
import java.util.*

class Tournament {
    fun createTournament(gamesCount: Int): List<Pair<Team, Team>> {
        val r = Random()

        val listOfGames = mutableListOf<Pair<Team, Team>>()

        for (i in 0 until gamesCount) {
            val home = Team(Color.BLUE, GameSide.HOME, createRandomTeam(r.nextInt(4)))
            val away = Team(Color.RED, GameSide.AWAY, createRandomTeam(r.nextInt(4)))
            listOfGames.add(Pair(home, away))
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