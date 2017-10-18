package labyrinth

import org.testng.annotations.Test

class LabyrinthTest {

    @Test
    fun should_initialize_correctly() {
        LabyrinthFactory.createRandomEmptyLabyrinthOfSize(10)
    }
}