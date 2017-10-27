package football.game

import football.controllers.FieldController

class GameContext private constructor(val team1: Team, val team2: Team) {
    var ballPosition: Coordinates = Coordinates(width / 2, height / 2)
    var fieldController: FieldController? = null

    private object Holder {
        var INSTANCE: GameContext? = null
    }

    companion object {
        val instance: GameContext by lazy { Holder.INSTANCE!! }

        val width: Double = 500.0
        val height: Double = 300.0
        val pixelsByMillisecond = 100.0
        val shootingDistance = 50.0
        val moveDistanceByTurn = 75.0


        fun createGame(team1: Team, team2: Team): GameContext {
            Holder.INSTANCE = GameContext(team1, team2)

            return instance
        }
    }

    override fun toString(): String {
        return "GameContext(team1=$team1, team2=$team2)"
    }
}