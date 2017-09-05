package tennis

import tennis.Score.*

class Game(val player1: Player, val player2: Player) {
    fun player1WinBall(): String {
        if (!isGameWon()) {
            if (hasAdvantage(player2))
                loseFromAdvantageToDeuce(player2)
            else
                player1.winBall()

            if (isGameBallFor(player1)) {
                player1.winBall()
            }
        }

        return getGameScore()
    }

    fun player2WinBall(): String {
        if (!isGameWon()) {
            if (hasAdvantage(player1))
                loseFromAdvantageToDeuce(player1)
            else
                player2.winBall()

            if (isGameBallFor(player2)) {
                player2.winBall()
            }
        }

        return getGameScore()
    }

    fun getGameScore(): String {
        if (hasWon(player1))
            return "${player1.name} has won"

        if (hasWon(player2))
            return "${player2.name} has won"

        if (isDeuce())
            return "deuce"

        if (hasAdvantage(player1))
            return "${player1.getScore().printableScore} ${player1.name}"

        if (hasAdvantage(player2))
            return "${player1.getScore().printableScore} ${player2.name}"

        return "${player1.getScore().printableScore}, ${player2.getScore().printableScore}"
    }

    private fun loseFromAdvantageToDeuce(player: Player) {
        player.score = FORTY.ordinal
    }

    private fun isGameWon() = hasWon(player1) || hasWon(player2)

    private fun hasWon(player: Player): Boolean = player.getScore() === WON

    private fun isGameBallFor(player: Player): Boolean {
        if (player == player1)
            return player1.getScore() === ADVANTAGE && isUnderDeuce(player2)
        else
            return player2.getScore() === ADVANTAGE && isUnderDeuce(player1)
    }

    private fun isUnderDeuce(player: Player): Boolean = player.getScore().ordinal < FORTY.ordinal

    private fun isDeuce() = player1.getScore() === FORTY && player2.getScore() === FORTY

    private fun hasAdvantage(player: Player): Boolean = player.getScore() === ADVANTAGE
}
