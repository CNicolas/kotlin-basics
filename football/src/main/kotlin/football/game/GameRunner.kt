package football.game

import football.game.strategies.PlayerStrategy
import football.helpers.moveTowards

class GameRunner(val score: Int = 1, val turns: Int = 100000) {
    fun play() {
        turns@ for (turn in 0 until turns) {
            print("$turn\n")

            when {
                doPlayerTurn(GameContext.instance.team1.player1) -> break@turns
                doPlayerTurn(GameContext.instance.team1.player2) -> break@turns
                doPlayerTurn(GameContext.instance.team2.player1) -> break@turns
                doPlayerTurn(GameContext.instance.team2.player2) -> break@turns
            }

            GameContext.instance.fieldController!!.updatePositions()
        }
    }

    private fun doPlayerTurn(player: PlayerStrategy): Boolean {
        player.move()

        if (isTouchingBall(player.currentPosition)) {
            pushBall(player.shoot())
            return score() == score
        }

        return false
    }

    private fun pushBall(aim: Coordinates) {
        GameContext.instance.ballPosition = moveTowards(GameContext.instance.ballPosition, aim, GameContext.shootingDistance)
    }

    private fun isTouchingBall(coordinates: Coordinates): Boolean {
        val maxDistanceToTouch = 10
        return Math.abs(coordinates.x - GameContext.instance.ballPosition.x) < maxDistanceToTouch
                && Math.abs(coordinates.y - GameContext.instance.ballPosition.y) < maxDistanceToTouch
    }


    private fun score(): Int {
        if (GameContext.instance.ballPosition.x == 0.0) {
            GameContext.instance.team2.score++
        } else if (GameContext.instance.ballPosition.x == GameContext.width) {
            GameContext.instance.team1.score++
        }

        return Math.max(GameContext.instance.team1.score, GameContext.instance.team2.score)
    }
}