package data.labyrinth.runner

import data.labyrinth.board.Coordinates
import data.labyrinth.board.Labyrinth
import data.labyrinth.runner.state.LabyrinthRunnerState
import java.util.*

class RandomLabyrinthRunner(labyrinth: Labyrinth) : LabyrinthRunner(labyrinth) {
    override fun moveOnceTowards(coordinates: Coordinates) {
        val r = Random()
        val accessibleCoordinates = states.last().getAccessibleCoordinates()

        playerPos = accessibleCoordinates[r.nextInt(accessibleCoordinates.size)]
        states.add(LabyrinthRunnerState(playerPos, labyrinth))
    }
}