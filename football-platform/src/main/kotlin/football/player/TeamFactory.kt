package football.player

import football.strategy.DoesNothing
import football.strategy.DumbRusherRunWithBall
import football.strategy.FixedGoalKeeper
import helpers.GameSide
import helpers.SideInTeam.*
import javafx.scene.paint.Color

class TeamFactory {
    fun DoesNothingUP_DoesNothingDOWN(color: Color, side: GameSide): Team {
        return Team(color, side, listOf(DoesNothing(UP), DoesNothing(DOWN)))
    }

    fun FixedGoalKeeper_DumbRusherRunWithBallUP(color: Color, side: GameSide): Team {
        return Team(color, side, listOf(FixedGoalKeeper(), DumbRusherRunWithBall(UP)))
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
}