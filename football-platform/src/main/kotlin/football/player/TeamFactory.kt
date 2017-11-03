package football.player

import football.strategy.DoesNothing
import football.strategy.PlayerStrategy
import football.strategy.attack.dumbRushers.DumbRusherRunWithBall
import football.strategy.attack.runAndShoot.RunAndShootStraight
import football.strategy.defense.DefenderFollowingBall
import football.strategy.defense.FixedGoalKeeper
import helpers.GameSide
import helpers.SideInTeam.*
import javafx.scene.paint.Color

class TeamFactory {
    fun customTeam(color: Color, side: GameSide, strategies: List<PlayerStrategy>): Team = Team(color, side, strategies)

    fun DoesNothingUP_DoesNothingDOWN(color: Color, side: GameSide): Team {
        return Team(color, side, listOf(DoesNothing(UP), DoesNothing(DOWN)))
    }

    fun FixedGoalKeeper_DumbRusherRunWithBallUP(color: Color, side: GameSide): Team {
        return Team(color, side, listOf(FixedGoalKeeper(), DumbRusherRunWithBall(UP)))
    }

    fun FixedGoalKeeper_RunAndShootStraightUP(color: Color, side: GameSide): Team {
        return Team(color, side, listOf(FixedGoalKeeper(), RunAndShootStraight(UP)))
    }

    fun DumbRusherRunWithBallUP_DumbRusherRunWithBallDOWN(color: Color, side: GameSide): Team {
        return Team(color, side, listOf(DumbRusherRunWithBall(UP), DumbRusherRunWithBall(DOWN)))
    }

    fun FixedGoalKeeper_DumbRusherRunWithBallUP_DumbRusherRunWithBallDOWN(color: Color, side: GameSide): Team {
        return Team(color, side, listOf(FixedGoalKeeper(), DumbRusherRunWithBall(UP), DumbRusherRunWithBall(DOWN)))
    }

    fun FixedGoalKeeper_DumbRusherRunWithBallUP_DumbRusherRunWithBallDOWN_DumbRusherRunWithBallCENTER(color: Color, side: GameSide): Team {
        return Team(color, side, listOf(FixedGoalKeeper(), DumbRusherRunWithBall(UP), DumbRusherRunWithBall(DOWN), DumbRusherRunWithBall(CENTER)))
    }

    fun Only_DefenderFollowingBall(color: Color, side: GameSide): Team {
        return Team(color, side, listOf(DefenderFollowingBall()))
    }
}