package football.game

class Game(val home: Team, val away: Team) {
    var score: Score = Score.DRAW
}