package football.game

enum class Score {
    HOME_WON, AWAY_WON, DRAW;

    companion object {
        fun calculate(home: Team, away: Team): Score {
            return when {
                home.score > away.score -> HOME_WON
                home.score < away.score -> AWAY_WON
                else -> DRAW
            }
        }
    }
}