package data.labyrinthe

class State(val playerPos: Coordinates) {
    private var up: Coordinates = playerPos.up()
    private var right: Coordinates = playerPos.right()
    private var down: Coordinates = playerPos.down()
    private var left: Coordinates = playerPos.left()

    fun getPossibleDirections(labyrinthe: Labyrinthe): Array<Boolean> {
        val res = Array(4, { false })

        res[0] = labyrinthe.getCase(up) != Case.WALL
        res[1] = labyrinthe.getCase(right) != Case.WALL
        res[2] = labyrinthe.getCase(down) != Case.WALL
        res[3] = labyrinthe.getCase(left) != Case.WALL

        return res
    }

    fun show(labyrinthe: Labyrinthe): String {
        return " " + labyrinthe.getCase(up) + " \n" +
                labyrinthe.getCase(left) + labyrinthe.getCase(playerPos) + labyrinthe.getCase(right) + "\n" +
                " " + labyrinthe.getCase(down) + " "
    }
}