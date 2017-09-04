package tennis

class Game(val player1: Player, val player2: Player) {
    private val DEUCE: String = "deuce"

    fun getGameScore(): String {
        if (isDeuce())
            return DEUCE

        if (hasPlayer1Advantage())
            return "${player1.getScore().printableScore} ${player1.name}"

        if (hasPlayer2Advantage())
            return "${player1.getScore().printableScore} ${player2.name}"

        return "${player1.getScore().printableScore}, ${player2.getScore().printableScore}"
    }

    private fun isDeuce() = player1.getScore() === Score.FORTY && player2.getScore() === Score.FORTY

    private fun hasPlayer1Advantage() = player1.getScore() === Score.ADVANTAGE && player2.getScore() === Score.FORTY

    private fun hasPlayer2Advantage() = player1.getScore() === Score.FORTY && player2.getScore() === Score.ADVANTAGE
}