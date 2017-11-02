package football.game

import football.FieldContext
import football.player.Player
import helpers.Coordinates
import helpers.distance
import javafx.animation.TranslateTransition
import javafx.util.Duration

class TransitionsManager {
    fun play(states: List<State>) {
        val transitions = mutableListOf<TranslateTransition>()

        println("${states.size} states")

        for (i in 0 until states.size - 1) {
            val state = states[i]
            val nextState = states[i + 1]

            if (state.team1.player1.position != nextState.team1.player1.position) {
                transitions.add(movePlayer(state.team1.player1, nextState.team1.player1.position))
            }
            if (state.team1.player2.position != nextState.team1.player2.position) {
                transitions.add(movePlayer(state.team1.player2, nextState.team1.player2.position))
            }
            if (state.team2.player1.position != nextState.team2.player1.position) {
                transitions.add(movePlayer(state.team2.player1, nextState.team2.player1.position))
            }
            if (state.team2.player2.position != nextState.team2.player2.position) {
                transitions.add(movePlayer(state.team2.player2, nextState.team2.player2.position))
            }
            if (state.ball.position != nextState.ball.position) {
                transitions.add(moveBall(state.ball, nextState.ball.position))
            }
        }

        playChainOfTransitions(transitions)
    }

    private fun playChainOfTransitions(transitions: List<TranslateTransition>) {
        println("${transitions.size} transitions")

        for (i in 0 until transitions.size - 1) {
            transitions[i].setOnFinished {
                transitions[i + 1].play()
            }
        }

        transitions[0].play()
    }

    private fun movePlayer(player: Player, to: Coordinates): TranslateTransition {
        val distanceToArrival = distance(player.position, to)
        val duration = (distanceToArrival * 1000) / FieldContext.movingSpeed

        val transition = TranslateTransition(Duration(duration), player.circle)
        transition.toX = to.x
        transition.toY = to.y

        return transition
    }

    private fun moveBall(ball: Ball, to: Coordinates): TranslateTransition {
        val distanceToArrival = distance(ball.position, to)
        val duration = (distanceToArrival * 1000) / FieldContext.movingSpeed

        val transition = TranslateTransition(Duration(duration), ball.circle)
        transition.toX = to.x
        transition.toY = to.y

        return transition
    }
}