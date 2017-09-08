package gameoflife


import org.assertj.core.api.Assertions.assertThat
import org.testng.annotations.Test

class CellTest {
    @Test
    fun should_be_dead_on_init() {
        val cell = Cell()
        assertThat(cell.state).isEqualTo(State.DEAD)
        assertThat(cell.isDead()).isTrue()
        assertThat(cell.isAlive()).isFalse()
    }

    @Test
    fun should_die() {
        val cell = Cell()
        cell.die()
        assertThat(cell.state).isEqualTo(State.DEAD)
        assertThat(cell.isDead()).isTrue()
        assertThat(cell.isAlive()).isFalse()
    }

    @Test
    fun should_be_born() {
        val cell = Cell()
        cell.beBorn()
        assertThat(cell.state).isEqualTo(State.ALIVE)
        assertThat(cell.isDead()).isFalse()
        assertThat(cell.isAlive()).isTrue()
    }
}
