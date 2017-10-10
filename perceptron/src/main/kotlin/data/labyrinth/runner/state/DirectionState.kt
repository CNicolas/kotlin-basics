package data.labyrinth.runner.state

import data.labyrinth.board.Coordinates
import data.labyrinth.board.Labyrinth

class DirectionState(val direction: Direction, val coordinates: Coordinates, labyrinth: Labyrinth) {
    val isAccessible: Boolean = labyrinth.isInLabyrinth(coordinates) && !labyrinth.isWall(coordinates)

}