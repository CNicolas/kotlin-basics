package main

import football.Ball
import football.FieldContext
import football.game.GameSide
import football.game.GameSide.AWAY
import football.game.GameSide.HOME
import football.game.Score
import football.game.Team
import football.player.Player
import helpers.hasBall
import main.ihm.State
import java.util.*
import kotlin.collections.HashMap

class GameRunner(val home: Team, val away: Team, val turns: Int = 200, val score: Int = 3) {
    val states: MutableList<State> = mutableListOf()

    fun play(): Score {
        states.clear()
        home.resetPositions()
        away.resetPositions()
        home.score = 0
        away.score = 0

        var turn = turns
        var scoreReached = false
        val playersByNumber = makeMapBetweenPlayerAndNumber()
        val order = listOf(0, 1, 2, 3, 4, 5, 6, 7)

        while (turn > 0 && !scoreReached) {
            Collections.shuffle(order)

            for (i in order) {
                if (doPlayerTurn(playersByNumber[order[i]])) {
                    scoreReached = true
                    break
                }
            }

            turn--
        }

        return Score.calculate(home, away)
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
        if (hasSideScoredGoal(HOME)) {
            home.score++
            resetAllPositions()
        } else if (hasSideScoredGoal(AWAY)) {
            away.score++
            resetAllPositions()
        }

        return Math.max(home.score, away.score)
    }

    private fun hasSideScoredGoal(side: GameSide): Boolean {
        val isBetweenCageBound = Ball.instance.position.y > FieldContext.height / 2 - FieldContext.cageSize / 2
                && Ball.instance.position.y < FieldContext.height / 2 + FieldContext.cageSize / 2

        return when (side) {
            HOME -> isBetweenCageBound && Ball.instance.position.x >= FieldContext.width
            AWAY -> isBetweenCageBound && Ball.instance.position.x <= 0.0
        }
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

    private fun makeMapBetweenPlayerAndNumber(): Map<Int, Player?> {
        val playersByNumber = HashMap<Int, Player?>()

        playersByNumber.put(0, home.player1)
        playersByNumber.put(1, home.player2)
        playersByNumber.put(2, home.player3)
        playersByNumber.put(3, home.player4)

        playersByNumber.put(4, away.player1)
        playersByNumber.put(5, away.player2)
        playersByNumber.put(6, away.player3)
        playersByNumber.put(7, away.player4)

        return playersByNumber
    }
}