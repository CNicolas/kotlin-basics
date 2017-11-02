package football.game

import football.player.Team

data class State(val team1: Team, val team2: Team, val ball: Ball, val shouldAnimate: Boolean = true) {
    override fun toString(): String {
        return "$team1 | $team2 | $ball"
    }
}