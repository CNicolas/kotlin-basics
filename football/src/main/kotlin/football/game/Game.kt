package football.game

import football.controllers.FieldController
import football.game.strategies.PlayerStrategy
import football.helpers.distance
import football.helpers.moveTowards
import javafx.scene.shape.Circle
import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.launch
import java.util.*

class Game private constructor(val team1: Team, val team2: Team) {
    private val team1PlayerCirclesMap = HashMap<PlayerStrategy, Circle>()
    private val team2PlayerCirclesMap = HashMap<PlayerStrategy, Circle>()

    var ballPosition: Coordinates = Coordinates(width / 2, height / 2)
    var fieldController: FieldController? = null
        set(value) {
            field = value
            instance.team1PlayerCirclesMap.put(team1.player1, field!!.team1player1!!)
            instance.team1PlayerCirclesMap.put(team1.player2, field!!.team1player2!!)
            instance.team2PlayerCirclesMap.put(team2.player1, field!!.team2player1!!)
            instance.team2PlayerCirclesMap.put(team2.player2, field!!.team2player2!!)
        }

    private object Holder {
        var INSTANCE: Game? = null
    }

    companion object {
        val instance: Game by lazy { Holder.INSTANCE!! }

        val width: Double = 500.0
        val height: Double = 300.0
        val pixelsByMillisecond = 100.0
        val strengthInPixels = 50.0


        fun createGame(team1: Team, team2: Team): Game {
            Holder.INSTANCE = Game(team1, team2)

            return instance
        }
    }

    fun move(playerStrategy: PlayerStrategy, to: Coordinates) {
        val distanceToArrival = distance(playerStrategy.currentPosition, to)
        val duration = (distanceToArrival * 1000) / pixelsByMillisecond

        playerStrategy.currentPosition = to

        launch {
            delay(duration.toLong())
            if (isTouchingBall(playerStrategy.currentPosition)) {
                pushBall(Coordinates(500.0, 150.0))
                print("player : ${playerStrategy.currentPosition}, ball : $ballPosition")
            }
        }
    }

    private fun isTouchingBall(coordinates: Coordinates): Boolean {
        val maxDistanceToTouch = 10
        return Math.abs(coordinates.x - ballPosition.x) < maxDistanceToTouch
                && Math.abs(coordinates.y - ballPosition.y) < maxDistanceToTouch
    }

    private fun pushBall(aim: Coordinates) {
        val duration = (strengthInPixels * 1000) / pixelsByMillisecond
        val (realX, realY) = moveTowards(ballPosition, aim, strengthInPixels)

        launch {
            delay(duration.toLong())
            ballPosition = Coordinates(realX, realY)
        }
    }

    override fun toString(): String {
        return "Game(team1=$team1, team2=$team2)"
    }


}