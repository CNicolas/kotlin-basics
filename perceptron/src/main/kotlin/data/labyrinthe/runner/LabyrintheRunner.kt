package data.labyrinthe.runner

import data.labyrinthe.Coordinates
import data.labyrinthe.Labyrinth
import data.labyrinthe.State

abstract class LabyrintheRunner(val labyrinth: Labyrinth) {
    var playerPos: Coordinates
    protected val states: MutableList<State> = mutableListOf()

    init {
        playerPos = labyrinth.start
        states.add(State(playerPos))
    }

    fun run(): Int {
        var steps = 0

        while (playerPos != labyrinth.end) {
            if (steps > 0) labyrinth.setPlayerLocation(playerPos)

            moveOnceTowards(labyrinth.end)

            steps++
            println(labyrinth)
        }

        return steps
    }

    abstract protected fun moveOnceTowards(coordinates: Coordinates)

    protected fun moveUp() {
        playerPos = playerPos.up()
        states.add(State(playerPos))
    }

    protected fun moveRight() {
        playerPos = playerPos.right()
        states.add(State(playerPos))
    }

    protected fun moveDown() {
        playerPos = playerPos.down()
        states.add(State(playerPos))
    }

    protected fun moveLeft() {
        playerPos = playerPos.left()
        states.add(State(playerPos))
    }
}