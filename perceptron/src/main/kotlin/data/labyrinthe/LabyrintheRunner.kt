package data.labyrinthe

class LabyrintheRunner(val labyrinthe: Labyrinthe, val player: Player) {
    var playerPos: Coordinates

    private var steps: Int = 0

    init {
        playerPos = labyrinthe.start
    }

    fun run(): Int {
        var hasFinished = false

        println("${player.name} starts in $playerPos")

        while (!hasFinished) {
            if (!moveOnClock()) {
                throw Exception("Stuck !!! " + playerPos)
            }

            steps++

            println("$playerPos after $steps steps.")

            if (playerPos == labyrinthe.end) {
                hasFinished = true
            }
        }

        return steps
    }

    private fun moveOnClock(): Boolean {
        if (moveRight()) {
            return true
        }
        if (moveDown()) {
            return true
        }
        if (moveLeft()) {
            return true
        }

        return moveUp()
    }

    private fun moveUp(): Boolean {
        val up = playerPos.up()

        if (isNextCaseWall(up)) {
            return false
        }

        playerPos = up

        return true
    }

    private fun moveLeft(): Boolean {
        val left = playerPos.left()

        if (isNextCaseWall(left)) {
            return false
        }

        playerPos = left

        return true
    }

    private fun moveDown(): Boolean {
        val down = playerPos.down()

        if (isNextCaseWall(down)) {
            return false
        }

        playerPos = down

        return true
    }

    private fun moveRight(): Boolean {
        val right = playerPos.right()

        if (isNextCaseWall(right)) {
            return false
        }

        playerPos = right

        return true
    }

    private fun isNextCaseWall(left: Coordinates) = labyrinthe.getCaseByCoordinates(left) == Case.WALL
}