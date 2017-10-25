package football.game

class Game private constructor(val team1: Team, val team2: Team) {

    private object Holder {
        var INSTANCE: Game? = null
    }

    companion object {
        val instance: Game by lazy { Holder.INSTANCE!! }

        val width: Double = 500.0
        val height: Double = 300.0
        var ballPosition: Coordinates = Coordinates(width / 2, height / 2)

        fun createGame(team1: Team, team2: Team): Game {
            Holder.INSTANCE = Game(team1, team2)

            return instance
        }
    }
}