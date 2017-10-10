package data.labyrinth.runner.state

import data.labyrinth.board.Coordinates
import data.labyrinth.board.Labyrinth

class LabyrinthRunnerState(private val playerPos: Coordinates, labyrinth: Labyrinth) {
    var upAccessible: Boolean
    var rightAccessible: Boolean
    var downAccessible: Boolean
    var leftAccessible: Boolean

    var upCoordinates: Coordinates = playerPos.up()
    var rightCoordinates: Coordinates = playerPos.right()
    var downCoordinates: Coordinates = playerPos.down()
    var leftCoordinates: Coordinates = playerPos.left()

    val directions: List<DirectionState>

    init {
        directions = listOf(DirectionState(Direction.UP, upCoordinates, labyrinth),
                DirectionState(Direction.RIGHT, rightCoordinates, labyrinth),
                DirectionState(Direction.DOWN, downCoordinates, labyrinth),
                DirectionState(Direction.LEFT, leftCoordinates, labyrinth))

        upAccessible = labyrinth.isInLabyrinth(upCoordinates) && !labyrinth.isWall(upCoordinates)
        rightAccessible = labyrinth.isInLabyrinth(rightCoordinates) && !labyrinth.isWall(rightCoordinates)
        downAccessible = labyrinth.isInLabyrinth(downCoordinates) && !labyrinth.isWall(downCoordinates)
        leftAccessible = labyrinth.isInLabyrinth(leftCoordinates) && !labyrinth.isWall(leftCoordinates)
    }

    fun getAccessibleCoordinates(): Array<Coordinates> {
        val res = arrayListOf<Coordinates>()

        if (upAccessible) res.add(upCoordinates)
        if (rightAccessible) res.add(rightCoordinates)
        if (downAccessible) res.add(downCoordinates)
        if (leftAccessible) res.add(leftCoordinates)

        return res.toArray(arrayOf(Coordinates(0, 0)))
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as LabyrinthRunnerState

        if (playerPos != other.playerPos) return false

        return true
    }

    override fun hashCode(): Int {
        return playerPos.hashCode()
    }
}