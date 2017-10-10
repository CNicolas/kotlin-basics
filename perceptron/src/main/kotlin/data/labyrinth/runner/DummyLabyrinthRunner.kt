package data.labyrinth.runner

import data.labyrinth.Coordinates
import data.labyrinth.Labyrinth

class DummyLabyrinthRunner(labyrinth: Labyrinth) : LabyrinthRunner(labyrinth) {
    override fun moveOnceTowards(coordinates: Coordinates) {
        when {
            playerPos.x > coordinates.x && !labyrinth.isWall(playerPos.up()) -> moveUp()
            playerPos.x < coordinates.x && !labyrinth.isWall(playerPos.down()) -> moveDown()
            else -> when {
                playerPos.y > coordinates.y && !labyrinth.isWall(playerPos.left()) -> moveLeft()
                playerPos.y < coordinates.y && !labyrinth.isWall(playerPos.right()) -> moveRight()
                else -> throw IllegalStateException("WTF ? I'm stuck here : $playerPos")
            }
        }
    }
}