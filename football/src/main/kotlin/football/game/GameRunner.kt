package football.game

import football.game.strategies.PlayerStrategy
import football.helpers.moveTowards

class GameRunner(val score: Int = 1, val turns: Int = 100000) {
    fun play() {
        turns@ for (turn in 0 until turns) {
            when {
                doPlayerTurn(Game.instance.team1.player1) -> break@turns
                doPlayerTurn(Game.instance.team1.player2) -> break@turns
                doPlayerTurn(Game.instance.team2.player1) -> break@turns
                doPlayerTurn(Game.instance.team2.player2) -> break@turns
            }
        }
    }

    fun doPlayerTurn(player: PlayerStrategy): Boolean {
        val destination = player.move()

        player.currentPosition = destination
        if (isTouchingBall(player.currentPosition)) {
            pushBall(player.shoot())
            if (score() == score) {
                print("END = ${Game.instance}\n")
                return true
            } else {
                print("${player.name} : ${player.currentPosition}, ball : ${Game.instance.ballPosition}\n")
            }
        }

        return false
    }

    private fun pushBall(aim: Coordinates) {
        Game.instance.ballPosition = moveTowards(Game.instance.ballPosition, aim, Game.strengthInPixels)
    }

    private fun isTouchingBall(coordinates: Coordinates): Boolean {
        val maxDistanceToTouch = 10
        return Math.abs(coordinates.x - Game.instance.ballPosition.x) < maxDistanceToTouch
                && Math.abs(coordinates.y - Game.instance.ballPosition.y) < maxDistanceToTouch
    }


    private fun score(): Int {
        if (Game.instance.ballPosition.x == 0.0) {
            Game.instance.team2.score++
        } else if (Game.instance.ballPosition.x == Game.width) {
            Game.instance.team1.score++
        }

        return Math.max(Game.instance.team1.score, Game.instance.team2.score)
    }
}