package labyrinth.runner

import labyrinth.board.Coordinates
import labyrinth.board.Labyrinth
import labyrinth.runner.state.Direction
import labyrinth.runner.state.Direction.*
import labyrinth.runner.state.DirectionState
import labyrinth.runner.state.LabyrinthRunnerState
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
                else -> makeAChoice(currentState, coordinates)
            }
        }
    }

    private fun makeAChoice(currentState: LabyrinthRunnerState, coordinates: Coordinates) {
        val lastChoice = choices.last()

        when (lastChoice) {
            UP -> makeAChoiceFromDown(currentState, coordinates)
            RIGHT -> makeAChoiceFromLeft(currentState, coordinates)
            DOWN -> makeAChoiceFromUp(currentState, coordinates)
            else -> makeAChoiceFromRight(currentState, coordinates)
        }
    }

    private fun makeAChoiceFromUp(currentState: LabyrinthRunnerState, coordinates: Coordinates) {
        val intelligentDirections = currentState.directions
                .filter { it.isAccessible && it.direction != UP }

        when {
            intelligentDirections.size == 1 -> when {
                intelligentDirections[0].direction == RIGHT -> moveRightAndSaveChoice()
                intelligentDirections[0].direction == DOWN -> moveDownAndSaveChoice()
                intelligentDirections[0].direction == LEFT -> moveLeftAndSaveChoice()
            }
            intelligentDirections.size > 1 -> {
                val chosen = chooseBetweenAccessibleDirections(intelligentDirections, coordinates)

                when (chosen) {
                    RIGHT -> moveRightAndSaveChoice()
                    DOWN -> moveDownAndSaveChoice()
                    LEFT -> moveLeftAndSaveChoice()
                    UP -> throw IllegalStateException("I won't go back (here $playerPos) : $choices")
                }
            }
            else -> throw IllegalStateException("My choices were bad (here $playerPos) : $choices")
        }
    }

    private fun makeAChoiceFromRight(currentState: LabyrinthRunnerState, coordinates: Coordinates) {
        val intelligentDirections = currentState.directions
                .filter { it.isAccessible && it.direction != RIGHT }

        when {
            intelligentDirections.size == 1 -> when {
                intelligentDirections[0].direction == UP -> moveUpAndSaveChoice()
                intelligentDirections[0].direction == DOWN -> moveDownAndSaveChoice()
                intelligentDirections[0].direction == LEFT -> moveLeftAndSaveChoice()
            }
            intelligentDirections.size > 1 -> {
                val chosen = chooseBetweenAccessibleDirections(intelligentDirections, coordinates)

                when (chosen) {
                    UP -> moveUpAndSaveChoice()
                    DOWN -> moveDownAndSaveChoice()
                    LEFT -> moveLeftAndSaveChoice()
                    RIGHT -> throw IllegalStateException("I won't go back (here $playerPos) : $choices")
                }
            }
            else -> throw IllegalStateException("My choices were bad (here $playerPos) : $choices")
        }
    }

    private fun makeAChoiceFromDown(currentState: LabyrinthRunnerState, coordinates: Coordinates) {
        val intelligentDirections = currentState.directions
                .filter { it.isAccessible && it.direction != DOWN }

        when {
            intelligentDirections.size == 1 -> when {
                intelligentDirections[0].direction == UP -> moveUpAndSaveChoice()
                intelligentDirections[0].direction == RIGHT -> moveRightAndSaveChoice()
                intelligentDirections[0].direction == LEFT -> moveLeftAndSaveChoice()
            }
            intelligentDirections.size > 1 -> {
                val chosen = chooseBetweenAccessibleDirections(intelligentDirections, coordinates)

                when (chosen) {
                    UP -> moveUpAndSaveChoice()
                    RIGHT -> moveRightAndSaveChoice()
                    LEFT -> moveLeftAndSaveChoice()
                    DOWN -> throw IllegalStateException("I won't go back (here $playerPos) : $choices")
                }
            }
            else -> throw IllegalStateException("My choices were bad (here $playerPos) : $choices")
        }
    }

    private fun makeAChoiceFromLeft(currentState: LabyrinthRunnerState, coordinates: Coordinates) {
        val intelligentDirections = currentState.directions
                .filter { it.isAccessible && it.direction != LEFT }

        when {
            intelligentDirections.size == 1 -> when {
                intelligentDirections[0].direction == UP -> moveUpAndSaveChoice()
                intelligentDirections[0].direction == RIGHT -> moveRightAndSaveChoice()
                intelligentDirections[0].direction == DOWN -> moveDownAndSaveChoice()
            }
            intelligentDirections.size > 1 -> {
                val chosen = chooseBetweenAccessibleDirections(intelligentDirections, coordinates)

                when (chosen) {
                    UP -> moveUpAndSaveChoice()
                    RIGHT -> moveRightAndSaveChoice()
                    DOWN -> moveDownAndSaveChoice()
                    LEFT -> throw IllegalStateException("I won't go back (here $playerPos) : $choices")
                }
            }
            else -> throw IllegalStateException("My choices were bad (here $playerPos) : $choices")
        }
    }

    private fun chooseBetweenAccessibleDirections(directionStates: List<DirectionState>, coordinates: Coordinates): Direction {
        val directions = directionStates.map { it.direction }

        return when {
            playerPos.x > coordinates.x && directions.contains(UP) -> UP
            playerPos.x < coordinates.x && directions.contains(DOWN) -> DOWN
            else -> when {
                playerPos.y > coordinates.y && directions.contains(LEFT) -> LEFT
                playerPos.y < coordinates.y && directions.contains(RIGHT) -> RIGHT
                else -> {
                    val r = Random()
                    directions[r.nextInt(directions.size)]
                }
            }
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