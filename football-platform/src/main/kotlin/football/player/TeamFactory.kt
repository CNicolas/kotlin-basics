package football.player

import football.game.GameSide
import football.game.Team
import football.player.SideInTeam.*
import football.player.strategy.DoesNothing
import football.player.strategy.PlayerStrategy
import football.player.strategy.attack.dumbRushers.DumbRusherNormal
import football.player.strategy.attack.dumbRushers.DumbRusherRun
import football.player.strategy.attack.dumbRushers.DumbRusherShoot
import football.player.strategy.attack.runAndShoot.RunAndShootStraight
import football.player.strategy.defense.DefenderFollowingBall
import football.player.strategy.defense.FixedGoalKeeper
import javafx.scene.paint.Color

class TeamFactory {
    fun customTeam(color: Color, side: GameSide, strategies: List<PlayerStrategy>): Team = Team(color, side, strategies)

    fun DoesNothingUP_DoesNothingDOWN(color: Color, side: GameSide): Team {
        return Team(color, side, listOf(DoesNothing(UP), DoesNothing(DOWN)))
    }

    fun FixedGoalKeeper_DumbRusherRunWithBallUP(color: Color, side: GameSide): Team {
        return Team(color, side, listOf(FixedGoalKeeper(), DumbRusherRun(UP)))
    }

    fun FixedGoalKeeper_RunAndShootStraightUP(color: Color, side: GameSide): Team {
        return Team(color, side, listOf(FixedGoalKeeper(), RunAndShootStraight(UP)))
    }

    fun DumbRusherRunUP_DumbRusherRunDOWN(color: Color, side: GameSide): Team {
        return Team(color, side, listOf(DumbRusherRun(UP), DumbRusherRun(DOWN)))
    }

    fun FixedGoalKeeper_DumbRusherRunWithBallUP_DumbRusherRunWithBallDOWN(color: Color, side: GameSide): Team {
        return Team(color, side, listOf(FixedGoalKeeper(), DumbRusherRun(UP), DumbRusherRun(DOWN)))
    }

    fun FixedGoalKeeper_DumbRusherRunUP_DumbRusherNormalDOWN_DumbRusherShootCENTER(color: Color, side: GameSide): Team {
        return Team(color, side, listOf(FixedGoalKeeper(), DumbRusherRun(UP), DumbRusherNormal(DOWN), DumbRusherShoot(CENTER)))
    }

    fun Only_DefenderFollowingBall(color: Color, side: GameSide): Team {
        return Team(color, side, listOf(DefenderFollowingBall()))
    }
}