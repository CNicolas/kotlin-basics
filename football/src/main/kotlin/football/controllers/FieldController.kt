package football.controllers

import javafx.animation.TranslateTransition
import javafx.fxml.FXML
import javafx.scene.input.MouseEvent
import javafx.scene.shape.Circle
import javafx.scene.shape.Line
import javafx.scene.shape.Rectangle
import javafx.util.Duration


class FieldController {
    private val pixelsByMillisecond = 100.0

    @FXML private var mediane: Line? = null
    @FXML private var centralRound: Circle? = null
    @FXML private var leftSurface: Rectangle? = null
    @FXML private var rightSurface: Rectangle? = null

    @FXML private var ball: Circle? = null

    @FXML private var team1player1: Circle? = null
    @FXML private var team1player2: Circle? = null
    @FXML private var team2player1: Circle? = null
    @FXML private var team2player2: Circle? = null

    @FXML
    fun handleClic(event: MouseEvent) {
        val distanceToArrival = distance(team1player1!!.translateX, team1player1!!.translateY, event.x, event.y)
        val duration = (distanceToArrival * 1000) / pixelsByMillisecond

        val transition = TranslateTransition(Duration(duration), team1player1)
        transition.toX = event.x
        transition.toY = event.y
        transition.play()

        team1player1!!.translateX = event.x
        team1player1!!.translateY = event.y
    }

    private fun distance(fromX: Double, fromY: Double, toX: Double, toY: Double): Double {
        return Math.sqrt(Math.pow(toX - fromX, 2.0) + Math.pow(toY - fromY, 2.0))
    }
}