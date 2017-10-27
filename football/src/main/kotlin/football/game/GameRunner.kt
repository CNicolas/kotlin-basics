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
            pushBall(Coordinates(500.0, 150.0))
            if (score() == score) {
                print("END = ${Game.instance}\n")
                return true
            } else {
                print("${player.name} : ${player.currentPosition}, ball : ${Game.instance.ballPosition}\n")
            }
        }

        return false
    }

    //
//    private fun move(playerStrategy: PlayerStrategy, to: Coordinates) {
//        val distanceToArrival = distance(playerStrategy.currentPosition, to)
//        val duration = (distanceToArrival * 1000) / Game.pixelsByMillisecond
//
//        playerStrategy.currentPosition = to
//
//        launch {
//            delay(duration.toLong())
//            if (isTouchingBall(playerStrategy.currentPosition)) {
//                pushBall(Coordinates(500.0, 150.0))
//                print("player : ${playerStrategy.currentPosition}, ball : ${Game.instance.ballPosition}")
//            }
//        }
//    }

    private fun pushBall(aim: Coordinates) {
        val duration = (Game.strengthInPixels * 1000) / Game.pixelsByMillisecond
        val (realX, realY) = moveTowards(Game.instance.ballPosition, aim, Game.strengthInPixels)

        Game.instance.ballPosition = moveTowards(Game.instance.ballPosition, aim, Game.strengthInPixels)

//        launch {
//            delay(duration.toLong())
//            Game.instance.ballPosition = Coordinates(realX, realY)
//        }
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