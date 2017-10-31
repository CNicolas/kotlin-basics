package football.controllers

import football.game.Coordinates
import football.game.GameContext
import football.game.strategies.PlayerStrategy
import football.helpers.distance
import football.ihm.MovingTransitionWrapper
import javafx.fxml.FXML
import javafx.scene.shape.Circle
import javafx.scene.shape.Line
import javafx.scene.shape.Rectangle
import javafx.util.Duration
import java.util.*


class FieldController {
    private val team1PlayerCirclesMap = HashMap<Circle, PlayerStrategy>()
    private val team2PlayerCirclesMap = HashMap<Circle, PlayerStrategy>()

    @FXML private var mediane: Line? = null
    @FXML private var centralRound: Circle? = null
    @FXML private var leftSurface: Rectangle? = null
    @FXML private var rightSurface: Rectangle? = null

    @FXML private var ball: Circle? = null

    @FXML private var team1player1: Circle? = null
    @FXML private var team1player2: Circle? = null
    @FXML private var team2player1: Circle? = null
    @FXML private var team2player2: Circle? = null

    fun initializePlayersPositionAndColors() {
        val game = GameContext.instance

        with(team1PlayerCirclesMap) {
            put(team1player1!!, game.team1.player1)
            put(team1player2!!, game.team1.player2)
        }
        with(team2PlayerCirclesMap) {
            put(team2player1!!, game.team2.player1)
            put(team2player2!!, game.team2.player2)
        }

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

    fun updatePositions() {
        chainTransitions(
                listOf(
                        createPlayerTransition(team1PlayerCirclesMap[team1player1]!!, team1player1!!),
                        createPlayerTransition(team1PlayerCirclesMap[team1player2]!!, team1player2!!),
                        createPlayerTransition(team2PlayerCirclesMap[team2player1]!!, team2player1!!),
                        createPlayerTransition(team2PlayerCirclesMap[team2player2]!!, team2player2!!),
                        createBallTransition()
                )
        )
    }

    private fun createPlayerTransition(playerStrategy: PlayerStrategy, playerCircle: Circle): MovingTransitionWrapper {
        val distanceToArrival = distance(Coordinates(playerCircle.translateX, playerCircle.translateY), playerStrategy.currentPosition)
        val duration = (distanceToArrival * 1000) / GameContext.movingSpeed

        val transitionWrapper = MovingTransitionWrapper(Duration(duration), playerCircle)
        transitionWrapper.toX = playerStrategy.currentPosition.x
        transitionWrapper.toY = playerStrategy.currentPosition.y

        transitionWrapper.afterFinished = {
            playerCircle.translateX = playerStrategy.currentPosition.x
            playerCircle.translateY = playerStrategy.currentPosition.y
        }

        return transitionWrapper
    }

    private fun createBallTransition(): MovingTransitionWrapper {
        val duration = (GameContext.shootingDistance * 1000) / GameContext.movingSpeed

        val transitionWrapper = MovingTransitionWrapper(Duration(duration), ball!!)
        transitionWrapper.toX = GameContext.instance.ballPosition.x
        transitionWrapper.toY = GameContext.instance.ballPosition.y

        transitionWrapper.afterFinished = {
            ball!!.translateX = GameContext.instance.ballPosition.x
            ball!!.translateY = GameContext.instance.ballPosition.y

            println(GameContext.instance.ballPosition)
        }

        return transitionWrapper
    }

    private fun chainTransitions(transitions: List<MovingTransitionWrapper>) {
        for (i in 0 until transitions.size - 1) {
            transitions[i].transition.setOnFinished {
                transitions[i].afterFinished()
                transitions[i + 1].play()
            }

            transitions[i].play()
        }
    }

    private fun updatePlayerPosition(playerStrategy: PlayerStrategy, playerCircle: Circle) {
        val transition = createPlayerTransition(playerStrategy, playerCircle)
        synchronized(GameContext.instance) {
            transition.play()
        }
    }

    private fun updateBallPosition() {
        val transition = createBallTransition()
        synchronized(GameContext.instance) {
            transition.play()
        }
    }
}
