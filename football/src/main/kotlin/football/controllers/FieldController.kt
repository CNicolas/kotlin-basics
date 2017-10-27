package football.controllers

import football.game.Coordinates
import football.game.GameContext
import football.helpers.distance
import javafx.animation.TranslateTransition
import javafx.fxml.FXML
import javafx.scene.input.MouseEvent
import javafx.scene.shape.Circle
import javafx.scene.shape.Line
import javafx.scene.shape.Rectangle
import javafx.util.Duration


class FieldController {
    private val pixelsByMillisecond = 100.0
    private val strengthInPixels = 50.0

    @FXML private var mediane: Line? = null
    @FXML private var centralRound: Circle? = null
    @FXML private var leftSurface: Rectangle? = null
    @FXML private var rightSurface: Rectangle? = null

    @FXML private var ball: Circle? = null

    @FXML
    var team1player1: Circle? = null
    @FXML
    var team1player2: Circle? = null
    @FXML
    var team2player1: Circle? = null
    @FXML
    var team2player2: Circle? = null

    @FXML
    fun handleClic(event: MouseEvent) {
        moveTo(team1player1!!, Coordinates(event.x, event.y))
    }

    fun initializePlayersPositionAndColors() {
        val game = GameContext.instance

        team1player1!!.fill = game.team1.color
        team1player1!!.translateX = game.team1.player1.currentPosition.x
        team1player1!!.translateY = game.team1.player1.currentPosition.y

        team1player2!!.fill = game.team1.color
        team1player2!!.translateX = game.team1.player2.currentPosition.x
        team1player2!!.translateY = game.team1.player2.currentPosition.y

        team2player1!!.fill = game.team2.color
        team2player1!!.translateX = game.team2.player1.currentPosition.x
        team2player1!!.translateY = game.team2.player1.currentPosition.y

        team2player2!!.fill = game.team2.color
        team2player2!!.translateX = game.team2.player2.currentPosition.x
        team2player2!!.translateY = game.team2.player2.currentPosition.y
    }

    fun moveTo(player: Circle, to: Coordinates) {
        val distanceToArrival = distance(Coordinates(player.translateX, player.translateY), to)
        val duration = (distanceToArrival * 1000) / pixelsByMillisecond

        val transition = TranslateTransition(Duration(duration), player)
        transition.toX = to.x
        transition.toY = to.y
        transition.play()

        transition.setOnFinished {
            player.translateX = to.x
            player.translateY = to.y

            if (isTouchingBall(player.translateX, player.translateY)) {
                pushBall(500.0, 150.0)
            }
        }
    }

    private fun isTouchingBall(x: Double, y: Double): Boolean {
        val maxDistanceToTouch = 10
        return Math.abs(x - ball!!.translateX) < maxDistanceToTouch && Math.abs(y - ball!!.translateY) < maxDistanceToTouch
    }

    private fun pushBall(toX: Double, toY: Double) {
        val duration = (strengthInPixels * 1000) / pixelsByMillisecond
        val (realX, realY) = moveBallTowards(toX, toY)

        val transition = TranslateTransition(Duration(duration), ball!!)
        transition.toX = realX
        transition.toY = realY
        transition.play()

        transition.setOnFinished {
            ball!!.translateX = realX
            ball!!.translateY = realY
            GameContext.instance.ballPosition = Coordinates(realX, realY)
        }
    }

    private fun moveBallTowards(aimX: Double, aimY: Double): Coordinates {
        var toX = ball!!.translateX
        var toY = ball!!.translateY

        for (count in 0 until 1000) {
            val currentDistanceTowardsObjective = distance(Coordinates(ball!!.translateX, ball!!.translateY), Coordinates(toX, toY))

            val isArrived = Math.abs(strengthInPixels - currentDistanceTowardsObjective) <= 5
            if (isArrived) {
                return Coordinates(toX, toY)
            }

            when {
                toX < aimX -> toX++
                toX > aimX -> toX--
                toY < aimY -> toY++
                toY > aimY -> toY--
                else -> return Coordinates(toX, toY)
            }
        }

        return Coordinates(toX, toY)
    }
}