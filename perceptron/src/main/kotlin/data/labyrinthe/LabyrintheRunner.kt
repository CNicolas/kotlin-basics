package data.labyrinthe

class LabyrintheRunner(val labyrinthe: Labyrinthe) {
    var playerPos: Coordinates
    private val states: MutableList<State> = mutableListOf()

    init {
        playerPos = labyrinthe.start
        states.add(State(playerPos))
    }

    fun run(): Int {
        return goToCoordinates(labyrinthe.end)
    }

    private fun goToCoordinates(coordinates: Coordinates): Int {
        var steps = 0

        while (playerPos != coordinates) {
            if (steps > 0) labyrinthe.setPlayerLocation(playerPos)

            if (playerPos.x > coordinates.x && !labyrinthe.isWall(playerPos.up())) {
                moveUp()
            } else if (playerPos.x < coordinates.x && !labyrinthe.isWall(playerPos.down())) {
                moveDown()
            } else {
                if (playerPos.y > coordinates.y && !labyrinthe.isWall(playerPos.left())) {
                    moveLeft()
                } else if (playerPos.y < coordinates.y && !labyrinthe.isWall(playerPos.right())) {
                    moveRight()
                }
            }

            steps++
            println(labyrinthe)
        }

        return steps
    }

    private fun moveUp() {
        playerPos = playerPos.up()
        states.add(data.labyrinthe.State(playerPos))
    }

    private fun moveRight() {
        playerPos = playerPos.right()
        states.add(data.labyrinthe.State(playerPos))
    }

    private fun moveDown() {
        playerPos = playerPos.down()
        states.add(data.labyrinthe.State(playerPos))
    }

    private fun moveLeft() {
        playerPos = playerPos.left()
        states.add(data.labyrinthe.State(playerPos))
    }
}