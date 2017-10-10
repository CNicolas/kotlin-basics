package data.labyrinth

class State(private val playerPos: Coordinates, labyrinth: Labyrinth) {
    var upAccessible: Boolean
    var rightAccessible: Boolean
    var downAccessible: Boolean
    var leftAccessible: Boolean

    private var upCoordinates: Coordinates = playerPos.up()
    private var rightCoordinates: Coordinates = playerPos.right()
    private var downCoordinates: Coordinates = playerPos.down()
    private var leftCoordinates: Coordinates = playerPos.left()

    init {
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

        return res.toArray(arrayOf(upCoordinates))
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as State

        if (playerPos != other.playerPos) return false

        return true
    }

    override fun hashCode(): Int {
        return playerPos.hashCode()
    }
}