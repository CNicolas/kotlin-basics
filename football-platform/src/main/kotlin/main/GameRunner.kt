package main

import football.Ball
import football.FieldContext
import football.game.GameSide
import football.game.GameSide.AWAY
import football.game.GameSide.HOME
import football.game.Score
import football.game.Team
import football.player.Player
import helpers.Coordinates
import helpers.doesBallEnterCage
import helpers.hasBall
import helpers.isThereAPlayerOnBallsWay
import main.ihm.State
import java.util.*
import kotlin.collections.HashMap

class GameRunner(private val home: Team,
                 private val away: Team,
                 private val turns: Int = 200,
                 private val score: Int = 3) {
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
                val currentPlayerNumber = order[i]
                val otherPlayers = playersByNumber.filterKeys { it != currentPlayerNumber }
                        .filterValues { it !== null }
                        .values
                        .map { it!! }

                if (doPlayerTurn(playersByNumber[currentPlayerNumber], otherPlayers)) {
                    scoreReached = true
                    break
                }
            }

            turn--
        }

        return Score.calculate(home, away)
    }

    private fun doPlayerTurn(player: Player?, otherPlayers: List<Player>): Boolean {
        if (player != null) {
            player.position = player.moveTo()
            addState()

            if (hasBall(player)) {
                synchronized(Ball.instance) {
                    val futureBallPosition = player.shootTo()
                    val ballEnteredCage = doesBallEnterCage(futureBallPosition)
                    val playerOnBallsWay = isThereAPlayerOnBallsWay(futureBallPosition, otherPlayers)

                    if (playerOnBallsWay is Coordinates) {
                        Ball.instance.position = playerOnBallsWay
                    } else {
                        Ball.instance.position = futureBallPosition
                    }
                    addState()

                    if (ballEnteredCage is GameSide) {
                        when (ballEnteredCage) {
                            HOME -> away.score++
                            AWAY -> home.score++
                        }

                        resetAllPositions()
                    }
                }

                return Math.max(home.score, away.score) == score
            }
        }

        return false
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