package labyrinth.runner

import labyrinth.board.Coordinates
import labyrinth.board.Labyrinth
import labyrinth.runner.state.LabyrinthRunnerState

abstract class LabyrinthRunner(val labyrinth: Labyrinth) {
    var playerPos: Coordinates
    protected val states: MutableList<LabyrinthRunnerState> = mutableListOf()

    init {
        playerPos = labyrinth.start
        states.add(LabyrinthRunnerState(playerPos, labyrinth))
    }

    fun run(print: Boolean = false): Int {
        var steps = 0

        while (playerPos != labyrinth.end) {
            if (steps > 0) labyrinth.setPlayerLocation(playerPos)

            moveOnceTowards(labyrinth.end)

            steps++

            if (print) println(labyrinth)
        }

        if (!print) println(labyrinth)

        return steps
    }

    abstract protected fun moveOnceTowards(coordinates: Coordinates)

    protected fun moveUp() {
        playerPos = playerPos.up()
        states.add(LabyrinthRunnerState(playerPos, labyrinth))
    }

    protected fun moveRight() {
        playerPos = playerPos.right()
        states.add(LabyrinthRunnerState(playerPos, labyrinth))
    }

    protected fun moveDown() {
        playerPos = playerPos.down()
        states.add(LabyrinthRunnerState(playerPos, labyrinth))
    }

    protected fun moveLeft() {
        playerPos = playerPos.left()
        states.add(LabyrinthRunnerState(playerPos, labyrinth))
    }
}