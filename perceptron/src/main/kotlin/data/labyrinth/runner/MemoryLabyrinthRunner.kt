package data.labyrinth.runner

import data.labyrinth.board.Coordinates
import data.labyrinth.board.Labyrinth
import data.labyrinth.runner.state.Direction
import data.labyrinth.runner.state.Direction.*
import data.labyrinth.runner.state.DirectionState
import data.labyrinth.runner.state.LabyrinthRunnerState
import java.util.*

class MemoryLabyrinthRunner(labyrinth: Labyrinth) : LabyrinthRunner(labyrinth) {
    var choices: MutableList<Direction> = mutableListOf()
    private var started = false

    override fun moveOnceTowards(coordinates: Coordinates) {
        val currentState = states.last()

        if (!started) {
            when {
                currentState.upAccessible -> moveUpAndSaveChoice()
                currentState.downAccessible -> moveDownAndSaveChoice()
                currentState.leftAccessible -> moveLeftAndSaveChoice()
                currentState.rightAccessible -> moveRightAndSaveChoice()
            }
            started = true
        } else {
            when {
                coordinates.x == playerPos.x - 1 && coordinates.y == playerPos.y -> moveUpAndSaveChoice()
                coordinates.x == playerPos.x && coordinates.y == playerPos.y + 1 -> moveRightAndSaveChoice()
                coordinates.x == playerPos.x + 1 && coordinates.y == playerPos.y -> moveDownAndSaveChoice()
                coordinates.x == playerPos.x && coordinates.y == playerPos.y - 1 -> moveLeftAndSaveChoice()
                else -> makeAChoice(currentState)
            }
        }
    }

    private fun makeAChoice(currentState: LabyrinthRunnerState) {
        val lastChoice = choices.last()

        when (lastChoice) {
            UP -> makeAChoiceFromDown(currentState)
            RIGHT -> makeAChoiceFromLeft(currentState)
            DOWN -> makeAChoiceFromUp(currentState)
            else -> makeAChoiceFromRight(currentState)
        }
    }

    private fun makeAChoiceFromUp(currentState: LabyrinthRunnerState) {
        val r = Random()

        val intelligentDirections = currentState.directions
                .filter { it.isAccessible && it.direction != Direction.UP }

        when {
            intelligentDirections.size == 1 -> when {
                intelligentDirections[0].direction == Direction.RIGHT -> moveRightAndSaveChoice()
                intelligentDirections[0].direction == DOWN -> moveDownAndSaveChoice()
                intelligentDirections[0].direction == Direction.LEFT -> moveLeftAndSaveChoice()
            }
            intelligentDirections.size > 1 -> {
                val chosen = intelligentDirections[r.nextInt(intelligentDirections.size)]

                when {
                    chosen.direction == Direction.RIGHT -> moveRightAndSaveChoice()
                    chosen.direction == DOWN -> moveDownAndSaveChoice()
                    chosen.direction == Direction.LEFT -> moveLeftAndSaveChoice()
                }
            }
            else -> throw IllegalStateException("My choices were bad (here $playerPos) : $choices")
        }
    }

    private fun makeAChoiceFromRight(currentState: LabyrinthRunnerState) {
        val r = Random()

        val intelligentDirections = currentState.directions
                .filter { it.isAccessible && it.direction != Direction.RIGHT }

        when {
            intelligentDirections.size == 1 -> when {
                intelligentDirections[0].direction == Direction.UP -> moveUpAndSaveChoice()
                intelligentDirections[0].direction == DOWN -> moveDownAndSaveChoice()
                intelligentDirections[0].direction == Direction.LEFT -> moveLeftAndSaveChoice()
            }
            intelligentDirections.size > 1 -> {
                val chosen = intelligentDirections[r.nextInt(intelligentDirections.size)]

                when {
                    chosen.direction == Direction.UP -> moveUpAndSaveChoice()
                    chosen.direction == DOWN -> moveDownAndSaveChoice()
                    chosen.direction == Direction.LEFT -> moveLeftAndSaveChoice()
                }
            }
            else -> throw IllegalStateException("My choices were bad (here $playerPos) : $choices")
        }
    }

    private fun makeAChoiceFromDown(currentState: LabyrinthRunnerState) {
        val r = Random()

        val intelligentDirections: List<DirectionState> = currentState.directions
                .filter { it.isAccessible && it.direction != DOWN }

        when {
            intelligentDirections.size == 1 -> when {
                intelligentDirections[0].direction == Direction.UP -> moveUpAndSaveChoice()
                intelligentDirections[0].direction == Direction.RIGHT -> moveRightAndSaveChoice()
                intelligentDirections[0].direction == Direction.LEFT -> moveLeftAndSaveChoice()
            }
            intelligentDirections.size > 1 -> {
                val chosen = intelligentDirections[r.nextInt(intelligentDirections.size)]

                when {
                    chosen.direction == Direction.UP -> moveUpAndSaveChoice()
                    chosen.direction == Direction.RIGHT -> moveRightAndSaveChoice()
                    chosen.direction == Direction.LEFT -> moveLeftAndSaveChoice()
                }
            }
            else -> throw IllegalStateException("My choices were bad (here $playerPos) : $choices")
        }
    }

    private fun makeAChoiceFromLeft(currentState: LabyrinthRunnerState) {
        val r = Random()

        val intelligentDirections = currentState.directions
                .filter { it.isAccessible && it.direction != Direction.LEFT }

        when {
            intelligentDirections.size == 1 -> when {
                intelligentDirections[0].direction == Direction.UP -> moveUpAndSaveChoice()
                intelligentDirections[0].direction == Direction.RIGHT -> moveRightAndSaveChoice()
                intelligentDirections[0].direction == DOWN -> moveDownAndSaveChoice()
            }
            intelligentDirections.size > 1 -> {
                val chosen = intelligentDirections[r.nextInt(intelligentDirections.size)]

                when {
                    chosen.direction == Direction.UP -> moveUpAndSaveChoice()
                    chosen.direction == Direction.RIGHT -> moveRightAndSaveChoice()
                    chosen.direction == DOWN -> moveDownAndSaveChoice()
                }
            }
            else -> throw IllegalStateException("My choices were bad (here $playerPos) : $choices")
        }
    }

    private fun moveUpAndSaveChoice() {
        moveUp()
        choices.add(UP)
    }

    private fun moveRightAndSaveChoice() {
        moveRight()
        choices.add(RIGHT)
    }

    private fun moveDownAndSaveChoice() {
        moveDown()
        choices.add(DOWN)
    }

    private fun moveLeftAndSaveChoice() {
        moveLeft()
        choices.add(LEFT)
    }
}