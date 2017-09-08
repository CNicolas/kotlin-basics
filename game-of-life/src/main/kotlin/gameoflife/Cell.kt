package gameoflife

class Cell {
    var state: State = State.DEAD
        private set

    fun beBorn() {
        this.state = State.ALIVE
    }

    fun die() {
        this.state = State.DEAD
    }

    fun isAlive() = state === State.ALIVE
    fun isDead() = state === State.DEAD

    override fun toString(): String {
        return if (state === State.ALIVE) "*" else " "
    }
}
