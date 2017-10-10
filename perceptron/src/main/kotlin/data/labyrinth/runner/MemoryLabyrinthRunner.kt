package data.labyrinth.runner

import data.labyrinth.board.Coordinates
import data.labyrinth.board.Labyrinth
import data.labyrinth.runner.state.LabyrinthRunnerState

class MemoryLabyrinthRunner(labyrinth: Labyrinth) : LabyrinthRunner(labyrinth) {
    var choices: MutableList<Int> = mutableListOf()

    override fun moveOnceTowards(coordinates: Coordinates) {
        val currentState = states.last()

        when {
            playerPos.x > coordinates.x && currentState.upAccessible -> moveUpAndSaveChoice()
            playerPos.x < coordinates.x && currentState.downAccessible -> moveDownAndSaveChoice()
            else -> when {
                playerPos.y > coordinates.y && currentState.leftAccessible -> moveLeftAndSaveChoice()
                playerPos.y < coordinates.y && currentState.rightAccessible -> moveRightAndSaveChoice()
                else -> makeAChoice(currentState)
            }
        }
    }

    private fun makeAChoice(currentState: LabyrinthRunnerState) {
        val lastChoice = choices.last()
        if (lastChoice == 0) {

        } else if (lastChoice == 1) {

        } else if (lastChoice == 2) {

        } else {

        }

        when {
            currentState.downAccessible -> moveDownAndSaveChoice()
            currentState.rightAccessible -> moveRightAndSaveChoice()
            currentState.upAccessible -> moveUpAndSaveChoice()
            currentState.leftAccessible -> moveLeftAndSaveChoice()
            else -> throw IllegalStateException("I'm stuck here : $playerPos")
        }
    }

    private fun moveUpAndSaveChoice() {
        moveUp()
        choices.add(0)
    }

    private fun moveRightAndSaveChoice() {
        moveRight()
        choices.add(1)
    }

    private fun moveDownAndSaveChoice() {
        moveDown()
        choices.add(2)
    }

    private fun moveLeftAndSaveChoice() {
        moveLeft()
        choices.add(3)
    }
}