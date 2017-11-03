package main

import football.Ball
import football.FieldContext
import football.game.Team
import football.player.Player
import helpers.hasBall
import main.ihm.State

class GameRunner(val team1: Team, val team2: Team, val score: Int = 3, val turns: Int = 200) {
    val states: MutableList<State> = mutableListOf()

    fun play() {
        states.clear()
        team1.resetPositions()
        team2.resetPositions()
        team1.score = 0
        team2.score = 0

        var turn = turns
        var scoreReached = false

        while (turn > 0 && !scoreReached) {
            when {
                doPlayerTurn(team1.player1) -> scoreReached = true
                doPlayerTurn(team1.player2) -> scoreReached = true
                doPlayerTurn(team1.player3) -> scoreReached = true
                doPlayerTurn(team1.player4) -> scoreReached = true

                doPlayerTurn(team2.player1) -> scoreReached = true
                doPlayerTurn(team2.player2) -> scoreReached = true
                doPlayerTurn(team2.player3) -> scoreReached = true
                doPlayerTurn(team2.player4) -> scoreReached = true
            }

            turn--
        }

        println("$team1 vs $team2")
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
            team2.score++
            resetAllPositions()
        } else if (Ball.instance.position.x >= FieldContext.width) {
            team1.score++
            resetAllPositions()
        }

        return Math.max(team1.score, team2.score)
    }

    private fun resetAllPositions() {
        Ball.instance.position = FieldContext.ballInitialPosition

        team1.resetPositions()
        team2.resetPositions()

        addState(false)
    }

    private fun addState(shouldAnimate: Boolean = true) {
        states.add(State(team1.clone(), team2.clone(), Ball.instance.clone(), shouldAnimate))
    }
}