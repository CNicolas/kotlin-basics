package data.labyrinthe.runner

import data.labyrinthe.Coordinates
import data.labyrinthe.Labyrinth
import data.labyrinthe.State

class BasicLabyrintheRunner(labyrinth: Labyrinth) : LabyrintheRunner(labyrinth) {
    override fun moveOnceTowards(coordinates: Coordinates) {
        val currentState = State(playerPos)
        val possibleDirections = currentState.getPossibleDirections(labyrinth)

        when {
            playerPos.x > coordinates.x -> needToGoUp(possibleDirections, coordinates)
            playerPos.x < coordinates.x -> needToGoDown(possibleDirections, coordinates)
            else -> sameLine(coordinates, possibleDirections)
        }
    }

    private fun sameLine(coordinates: Coordinates, possibleDirections: Array<Boolean>) {
        when {
            playerPos.y > coordinates.y -> when {
                possibleDirections[3] -> moveLeft()
                possibleDirections[0] -> moveUp()
                possibleDirections[2] -> moveDown()
                else -> moveRightOrThrowByState()
            }
            playerPos.y < coordinates.y -> when {
                possibleDirections[1] -> moveRight()
                possibleDirections[0] -> moveUp()
                possibleDirections[2] -> moveDown()
                else -> moveLeftOrThrowByState()
            }
            else -> throw IllegalStateException("I'm where I'm supposed to be !")
        }
    }

    private fun needToGoDown(possibleDirections: Array<Boolean>, coordinates: Coordinates) {
        when {
            possibleDirections[2] -> moveDown()
            playerPos.y > coordinates.y -> when {
                possibleDirections[3] -> moveLeft()
                possibleDirections[0] -> moveUp()
                else -> moveRightOrThrowByState()
            }
            playerPos.y < coordinates.y -> when {
                possibleDirections[1] -> moveRight()
                possibleDirections[0] -> moveUp()
                else -> moveLeftOrThrowByState()
            }
            else -> when {
                possibleDirections[1] -> moveRight()
                possibleDirections[3] -> moveLeft()
                else -> moveUpOrThrowByState()
            }
        }
    }


    private fun needToGoUp(possibleDirections: Array<Boolean>, coordinates: Coordinates) {
        when {
            possibleDirections[0] -> moveUpOrThrowByState()
            playerPos.y > coordinates.y -> when {
                possibleDirections[3] -> moveLeft()
                possibleDirections[1] -> moveRight()
                else -> moveDownOrThrowByState()
            }
            playerPos.y < coordinates.y -> when {
                possibleDirections[1] -> moveRight()
                possibleDirections[2] -> moveDown()
                else -> moveLeftOrThrowByState()
            }
            else -> when {
                possibleDirections[1] -> moveRight()
                possibleDirections[3] -> moveLeft()
                else -> moveDownOrThrowByState()
            }
        }
    }

    private fun moveUpOrThrowByState() {
        if (!states.contains(State(playerPos.up()))) {
            moveUp()
        } else {
            throw IllegalStateException("I've already been there ${playerPos.up()} before...")
        }
    }

    private fun moveRightOrThrowByState() {
        if (!states.contains(State(playerPos.right()))) {
            moveRight()
        } else {
            throw IllegalStateException("I've already been there ${playerPos.right()} before...")
        }
    }

    private fun moveDownOrThrowByState() {
        if (!states.contains(State(playerPos.down()))) {
            moveDown()
        } else {
            throw IllegalStateException("I've already been there ${playerPos.down()} before...")
        }
    }

    private fun moveLeftOrThrowByState() {
        if (!states.contains(State(playerPos.left()))) {
            moveLeft()
        } else {
            throw IllegalStateException("I've already been there ${playerPos.left()} before...")
        }
    }
}