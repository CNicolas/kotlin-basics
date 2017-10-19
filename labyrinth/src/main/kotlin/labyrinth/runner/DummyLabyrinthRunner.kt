package labyrinth.runner

import labyrinth.board.Coordinates
import labyrinth.board.Labyrinth

class DummyLabyrinthRunner(labyrinth: Labyrinth) : LabyrinthRunner(labyrinth) {
    override fun moveOnceTowards(coordinates: Coordinates) {
        val currentState = states.last()

        when {
            playerPos.x > coordinates.x && currentState.upAccessible -> moveUp()
            playerPos.x < coordinates.x && currentState.downAccessible -> moveDown()
            else -> when {
                playerPos.y > coordinates.y && currentState.leftAccessible -> moveLeft()
                playerPos.y < coordinates.y && currentState.rightAccessible -> moveRight()
                else -> throw IllegalStateException("I'm stuck here : $playerPos")
            }
        }
    }
}