package labyrinth.runner.state

import labyrinth.board.Coordinates
import labyrinth.board.Labyrinth

class DirectionState(val direction: Direction, val coordinates: Coordinates, labyrinth: Labyrinth) {
    val isAccessible: Boolean = labyrinth.isInLabyrinth(coordinates) && !labyrinth.isWall(coordinates)

}