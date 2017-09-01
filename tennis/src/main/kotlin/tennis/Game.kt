package tennis

class Game(val player1: Player, val player2: Player) {
    private val DEUCE: String = "deuce"

    fun getGameScore(): String {
        if (player1.score === Score.FORTY && player1.score === player2.score) return DEUCE
        if (player1.score === Score.ADVANTAGE) return "${player1.score.printableScore} ${player1.name}"
        if (player2.score === Score.ADVANTAGE) return "${player2.score.printableScore} ${player2.name}"
        return "${player1.score.printableScore}, ${player2.score.printableScore}"
    }
}