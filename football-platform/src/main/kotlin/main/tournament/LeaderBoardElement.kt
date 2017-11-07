package main.tournament

import football.game.Team

class LeaderBoardElement(val team: Team) {
    var score: Int = 0
    var victories: Int = 0
    var draws: Int = 0
    var losses: Int = 0

    var maxPadding: Int = 130

    override fun toString(): String {
        return team.strategies.toString().padEnd(maxPadding) +
                " | " + score.toString().padEnd(3) +
                " | " + victories.toString().padEnd(3) +
                " | " + draws.toString().padEnd(3) +
                " | " + losses.toString().padEnd(3) + ""
    }
}