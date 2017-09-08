package gameoflife

class GameOfLife() {
    var size: Int = 10
    var grid: Array<Array<Cell>> = Array(size) { Array(size) { Cell() } }
        private set

    constructor(grid: Array<Array<Cell>>) : this() {
        this.grid = grid
        this.size = grid.size
    }

    fun turn() {
        val newGrid = grid

        for (line in 0 until size) {
            for (column in 0 until size) {
                //FOR EACH CELL
                val currentCell = newGrid[line][column]

                var aliveCells = 0
                for (nearbyLine in (line - 1)..(line + 1)) {
                    for (nearbyColumn in (column - 1)..(column + 1)) {
                        if (!(line == nearbyLine && column == nearbyColumn))
                            if (isCellValid(nearbyLine, nearbyColumn)) {
                                if (grid[nearbyLine][nearbyColumn].isAlive())
                                    aliveCells++
                            }
                    }
                }

                if (aliveCells == 3)
                    currentCell.beBorn()
                else if (aliveCells < 2 || aliveCells > 3)
                    currentCell.die()
            }
        }

        grid = newGrid
    }

    private fun isCellValid(line: Int, column: Int): Boolean {
        return line in 0..(size - 1) && column in 0..(size - 1)
    }

    fun stringRepresentation(grid: Array<Array<Cell>> = this.grid): String {
        var res = "\n"

        for (line in 0 until size) {
            for (column in 0 until size) {
                res += grid[line][column].toString()
            }
            res += "\n"
        }

        return res
    }
}
