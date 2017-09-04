package tennis

class Game(val player1: Player, val player2: Player) {
    var isGameWon: Boolean = false

    fun player1WinBall(): String {
        if (!isGameWon) {
            if (hasPlayer2Advantage())
                player2.score = Score.FORTY.ordinal

            player1.winBall()
            if (player1.getScore() === Score.ADVANTAGE && player2.getScore().ordinal < Score.FORTY.ordinal) {
                player1.winBall()
                isGameWon = true
            }
        }

        return getGameScore()
    }

    fun player2WinBall(): String {
        if (!isGameWon) {
            if (hasPlayer1Advantage())
                player1.score = Score.FORTY.ordinal

            player2.winBall()
            if (player2.getScore() === Score.ADVANTAGE && player1.getScore().ordinal < Score.FORTY.ordinal) {
                player2.winBall()
                isGameWon = true
            }
        }

        return getGameScore()
    }

    fun getGameScore(): String {
        if (hasPlayer1Won())
            return "${player1.name} has won"

        if (hasPlayer2Won())
            return "${player2.name} has won"

        if (isDeuce())
            return "deuce"

        if (hasPlayer1Advantage())
            return "${player1.getScore().printableScore} ${player1.name}"

        if (hasPlayer2Advantage())
            return "${player1.getScore().printableScore} ${player2.name}"

        return "${player1.getScore().printableScore}, ${player2.getScore().printableScore}"
    }

    private fun hasPlayer2Won() = player2.getScore() === Score.WON

    private fun hasPlayer1Won() = player1.getScore() === Score.WON

    private fun isDeuce() = player1.getScore() === Score.FORTY && player2.getScore() === Score.FORTY

    private fun hasPlayer1Advantage() = player1.getScore() === Score.ADVANTAGE && player2.getScore() === Score.FORTY

    private fun hasPlayer2Advantage() = player1.getScore() === Score.FORTY && player2.getScore() === Score.ADVANTAGE
}