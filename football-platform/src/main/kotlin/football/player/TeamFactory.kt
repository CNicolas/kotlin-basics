package football.player

import football.game.GameSide
import football.game.Team
import football.player.SideInTeam.*
import football.player.strategy.simple.DoesNothing
import football.player.strategy.simple.attack.dumbRushers.DumbRusherNormal
import football.player.strategy.simple.attack.dumbRushers.DumbRusherRun
import football.player.strategy.simple.attack.dumbRushers.DumbRusherShoot
import football.player.strategy.simple.attack.runAndShoot.shootStraight.RunAndShootStraight
import football.player.strategy.simple.defense.DefenderFollowingBall
import football.player.strategy.simple.defense.FixedGoalKeeper
import javafx.scene.paint.Color

class TeamFactory {
    fun DoesNothingUP_DoesNothingDOWN(color: Color, side: GameSide): Team {
        val team = Team(color, listOf(DoesNothing(UP), DoesNothing(DOWN)))
        team.gameSide = side

        return team
    }

    fun FixedGoalKeeper_DumbRusherRunWithBallUP(color: Color, side: GameSide): Team {
        val team = Team(color, listOf(FixedGoalKeeper(), DumbRusherRun(UP)))
        team.gameSide = side

        return team
    }

    fun FixedGoalKeeper_RunAndShootStraightUP(color: Color, side: GameSide): Team {
        val team = Team(color, listOf(FixedGoalKeeper(), RunAndShootStraight(UP)))
        team.gameSide = side

        return team
    }

    fun DumbRusherRunUP_DumbRusherRunDOWN(color: Color, side: GameSide): Team {
        val team = Team(color, listOf(DumbRusherRun(UP), DumbRusherRun(DOWN)))
        team.gameSide = side

        return team
    }

    fun FixedGoalKeeper_DumbRusherRunWithBallUP_DumbRusherRunWithBallDOWN(color: Color, side: GameSide): Team {
        val team = Team(color, listOf(FixedGoalKeeper(), DumbRusherRun(UP), DumbRusherRun(DOWN)))
        team.gameSide = side

        return team
    }

    fun FixedGoalKeeper_DumbRusherRunUP_DumbRusherNormalDOWN_DumbRusherShootCENTER(color: Color, side: GameSide): Team {
        val team = Team(color, listOf(FixedGoalKeeper(), DumbRusherRun(UP), DumbRusherNormal(DOWN), DumbRusherShoot(CENTER)))
        team.gameSide = side

        return team
    }

    fun Only_DefenderFollowingBall(color: Color, side: GameSide): Team {
        val team = Team(color, listOf(DefenderFollowingBall()))
        team.gameSide = side

        return team
    }
}