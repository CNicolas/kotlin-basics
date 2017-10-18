package labyrinth.runner

import labyrinth.board.Coordinates
import labyrinth.board.Labyrinth
import labyrinth.runner.state.LabyrinthRunnerState
import java.util.*

class RandomLabyrinthRunner(labyrinth: Labyrinth) : LabyrinthRunner(labyrinth) {
    override fun moveOnceTowards(coordinates: Coordinates) {
        val r = Random()
        val accessibleCoordinates = states.last().getAccessibleCoordinates()

        playerPos = accessibleCoordinates[r.nextInt(accessibleCoordinates.size)]
        states.add(LabyrinthRunnerState(playerPos, labyrinth))
    }
}