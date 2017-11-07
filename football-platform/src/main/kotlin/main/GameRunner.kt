package main

import football.Ball
import football.FieldContext
import football.game.Score
import football.game.Team
import football.player.Player
import helpers.hasBall
import main.ihm.State

class GameRunner(val home: Team, val away: Team, val score: Int = 3, val turns: Int = 200) {
    val states: MutableList<State> = mutableListOf()

    fun play(): Score {
        states.clear()
        home.resetPositions()
        away.resetPositions()
        home.score = 0
        away.score = 0

        var turn = turns
        var scoreReached = false

        while (turn > 0 && !scoreReached) {
            when {
                doPlayerTurn(home.player1) -> scoreReached = true
                doPlayerTurn(home.player2) -> scoreReached = true
                doPlayerTurn(home.player3) -> scoreReached = true
                doPlayerTurn(home.player4) -> scoreReached = true

                doPlayerTurn(away.player1) -> scoreReached = true
                doPlayerTurn(away.player2) -> scoreReached = true
                doPlayerTurn(away.player3) -> scoreReached = true
                doPlayerTurn(away.player4) -> scoreReached = true
            }

            turn--
        }

        val finalScore = Score.calculate(home, away)
//        println("${home.strategies} vs ${away.strategies} : $finalScore\n")

        return finalScore
    }

    private fun doPlayerTurn(player: Player?): Boolean {
        if (player != null) {
            player.position = player.moveTo()
            addState()

            if (hasBall(player)) {
                synchronized(Ball.instance) {
                    Ball.instance.position = player.shootTo()
                    addState()
                }

                return score() == score
            }
        }

        return false
    }

    private fun score(): Int {
        if (Ball.instance.position.x <= 0.0) {
            away.score++
            resetAllPositions()
        } else if (Ball.instance.position.x >= FieldContext.width) {
            home.score++
            resetAllPositions()
        }

        return Math.max(home.score, away.score)
    }

    private fun resetAllPositions() {
        Ball.instance.position = FieldContext.ballInitialPosition

        home.resetPositions()
        away.resetPositions()

        addState(false)
    }

    private fun addState(shouldAnimate: Boolean = true) {
        states.add(State(home.clone(), away.clone(), Ball.instance.clone(), shouldAnimate))
    }
}