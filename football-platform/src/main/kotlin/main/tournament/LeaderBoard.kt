package main.tournament

import football.game.Team

class LeaderBoard(teamsList: List<Team>) {
    var gamesPlayed: Int = 0
        private set
    var leaderBoard: List<LeaderBoardElement>
            = teamsList.mapIndexed { index, team -> LeaderBoardElement(index, team, 0) }
        private set

    fun win(index: Int) {
        leaderBoard[index].score += 3
    }

    fun draw(index1: Int, index2: Int) {
        leaderBoard[index1].score += 1
        leaderBoard[index2].score += 1
    }

    fun getWinner(): LeaderBoardElement =
            leaderBoard.sortedByDescending { leaderBoardElement -> leaderBoardElement.score }
                    .first()

    fun oneMoreGamePlayed() {
        gamesPlayed++
    }

    fun orderDescendingByScore() {
        leaderBoard = leaderBoard.sortedByDescending { it.score }
    }
}