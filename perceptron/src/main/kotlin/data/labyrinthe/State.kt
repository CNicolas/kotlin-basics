package data.labyrinthe

class State(val playerPos: Coordinates) {
    private var up: Coordinates = playerPos.up()
    private var right: Coordinates = playerPos.right()
    private var down: Coordinates = playerPos.down()
    private var left: Coordinates = playerPos.left()

    fun getPossibleDirections(labyrinth: Labyrinth): Array<Boolean> {
        val res = Array(4, { false })

        res[0] = labyrinth.isInLabyrinth(up) && !labyrinth.isWall(up)
        res[1] = labyrinth.isInLabyrinth(right) && !labyrinth.isWall(right)
        res[2] = labyrinth.isInLabyrinth(down) && !labyrinth.isWall(down)
        res[3] = labyrinth.isInLabyrinth(left) && !labyrinth.isWall(left)

        return res
    }

    fun show(labyrinth: Labyrinth): String {
        return " " + labyrinth.getCase(up) + " \n" +
                labyrinth.getCase(left) + labyrinth.getCase(playerPos) + labyrinth.getCase(right) + "\n" +
                " " + labyrinth.getCase(down) + " "
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