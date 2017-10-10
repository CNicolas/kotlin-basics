package data.labyrinthe.runner

import data.labyrinthe.Coordinates
import data.labyrinthe.Labyrinth
import data.labyrinthe.State
import java.util.*

class RandomLabyrinthRunner(labyrinth: Labyrinth) : LabyrinthRunner(labyrinth) {
    override fun moveOnceTowards(coordinates: Coordinates) {
        val r = Random()
        val accessibleCoordinates = states.last().getAccessibleCoordinates()

        playerPos = accessibleCoordinates[r.nextInt(accessibleCoordinates.size)]
        states.add(State(playerPos, labyrinth))
    }
}