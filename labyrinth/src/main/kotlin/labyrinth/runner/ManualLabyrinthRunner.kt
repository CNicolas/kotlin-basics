package labyrinth.runner

import labyrinth.board.Coordinates
import labyrinth.board.Labyrinth
import labyrinth.runner.state.LabyrinthRunnerState

class ManualLabyrinthRunner(labyrinth: Labyrinth) : LabyrinthRunner(labyrinth) {
    override fun moveOnceTowards(coordinates: Coordinates) {
        val currentState = LabyrinthRunnerState(playerPos, labyrinth)

        when {
            playerPos.x > coordinates.x -> needToGoUp(currentState, coordinates)
            playerPos.x < coordinates.x -> needToGoDown(currentState, coordinates)
            else -> sameLine(currentState, coordinates)
        }
    }

    private fun sameLine(state: LabyrinthRunnerState, coordinates: Coordinates) {
        when {
            playerPos.y > coordinates.y -> when {
                state.leftAccessible -> moveLeft()
                state.upAccessible -> moveUp()
                state.downAccessible -> moveDown()
                else -> moveRightOrThrowByState()
            }
            playerPos.y < coordinates.y -> when {
                state.rightAccessible -> moveRight()
                state.upAccessible -> moveUp()
                state.downAccessible -> moveDown()
                else -> moveLeftOrThrowByState()
            }
            else -> throw IllegalStateException("I'm where I'm supposed to be !")
        }
    }

    private fun needToGoDown(state: LabyrinthRunnerState, coordinates: Coordinates) {
        when {
            state.downAccessible -> moveDown()
            playerPos.y > coordinates.y -> when {
                state.leftAccessible -> moveLeft()
                state.upAccessible -> moveUp()
                else -> moveRightOrThrowByState()
            }
            playerPos.y < coordinates.y -> when {
                state.rightAccessible -> moveRight()
                state.upAccessible -> moveUp()
                else -> moveLeftOrThrowByState()
            }
            else -> when {
                state.rightAccessible -> moveRight()
                state.leftAccessible -> moveLeft()
                else -> moveUpOrThrowByState()
            }
        }
    }


    private fun needToGoUp(state: LabyrinthRunnerState, coordinates: Coordinates) {
        when {
            state.upAccessible -> moveUpOrThrowByState()
            playerPos.y > coordinates.y -> when {
                state.leftAccessible -> moveLeft()
                state.rightAccessible -> moveRight()
                else -> moveDownOrThrowByState()
            }
            playerPos.y < coordinates.y -> when {
                state.rightAccessible -> moveRight()
                state.downAccessible -> moveDown()
                else -> moveLeftOrThrowByState()
            }
            else -> when {
                state.rightAccessible -> moveRight()
                state.leftAccessible -> moveLeft()
                else -> moveDownOrThrowByState()
            }
        }
    }

    private fun moveUpOrThrowByState() {
        if (!states.contains(LabyrinthRunnerState(playerPos.up(), labyrinth))) {
            moveUp()
        } else {
            throw IllegalStateException("I've already been there ${playerPos.up()} before...")
        }
    }

    private fun moveRightOrThrowByState() {
        if (!states.contains(LabyrinthRunnerState(playerPos.right(), labyrinth))) {
            moveRight()
        } else {
            throw IllegalStateException("I've already been there ${playerPos.right()} before...")
        }
    }

    private fun moveDownOrThrowByState() {
        if (!states.contains(LabyrinthRunnerState(playerPos.down(), labyrinth))) {
            moveDown()
        } else {
            throw IllegalStateException("I've already been there ${playerPos.down()} before...")
        }
    }

    private fun moveLeftOrThrowByState() {
        if (!states.contains(LabyrinthRunnerState(playerPos.left(), labyrinth))) {
            moveLeft()
        } else {
            throw IllegalStateException("I've already been there ${playerPos.left()} before...")
        }
    }
}