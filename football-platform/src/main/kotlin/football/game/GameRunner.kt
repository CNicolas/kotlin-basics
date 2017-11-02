package football.game

import football.FieldContext
import football.player.Player
import football.player.Team
import helpers.hasBall

class GameRunner(val team1: Team, val team2: Team, val score: Int = 3, val turns: Int = 100000) {
    val states: MutableList<State> = mutableListOf()

    fun play() {
        var turn = turns
        var scoreReached = false

        while (turn > 0 && !scoreReached) {
            when {
                doPlayerTurn(team1.player1) -> scoreReached = true
                doPlayerTurn(team1.player2) -> scoreReached = true
                doPlayerTurn(team2.player1) -> scoreReached = true
                doPlayerTurn(team2.player2) -> scoreReached = true
            }

            turn--
        }

        println("$team1 vs $team2")
    }

    private fun doPlayerTurn(player: Player): Boolean {
        player.position = player.moveTo()
        addState()

        if (hasBall(player)) {
            synchronized(Ball.instance) {
                Ball.instance.position = player.shootTo()
                addState()
            }

            return score() == score
        }

        return false
    }

    private fun score(): Int {
        if (Ball.instance.position.x == 0.0) {
            team2.score++
            resetAllPositions()
        } else if (Ball.instance.position.x == FieldContext.width) {
            team1.score++
            resetAllPositions()
        }

        return Math.max(team1.score, team2.score)
    }

    private fun resetAllPositions() {
        Ball.instance.position = FieldContext.ballInitialPosition

        team1.resetPositions()
        team2.resetPositions()
    }

    private fun addState() {
        states.add(State(team1.clone(), team2.clone(), Ball.instance.clone()))
    }
}