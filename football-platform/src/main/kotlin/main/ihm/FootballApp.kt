package main.ihm

import football.Ball
import football.FieldContext
import football.game.GameSide
import football.game.Team
import football.player.SideInTeam.UP
import football.player.strategy.combined.midfield.RecoverAndShoot
import football.player.strategy.simple.attack.dumbRushers.DumbRusherShoot
import javafx.application.Application
import javafx.scene.Scene
import javafx.scene.layout.BorderPane
import javafx.scene.paint.Color
import javafx.stage.Screen
import javafx.stage.Stage
import main.GameRunner

class FootballApp : Application() {
    fun mainStart(args: Array<String>) {
        launch(*args)
    }

    override fun start(primaryStage: Stage?) {
        val rootPane = BorderPane()

        rootPane.children.add(FieldContext.mediane)
        rootPane.children.add(FieldContext.centerRing)
        rootPane.children.add(FieldContext.leftSurface)
        rootPane.children.add(FieldContext.rightSurface)
        rootPane.children.add(FieldContext.leftCage)
        rootPane.children.add(FieldContext.rightCage)

        rootPane.children.add(Ball.instance.circle)

        val (team1, team2) = createTeams()

        rootPane.children.add(team1.player1.circle)
        if (team1.player2 !== null) rootPane.children.add(team1.player2!!.circle)
        if (team1.player3 !== null) rootPane.children.add(team1.player3!!.circle)
        if (team1.player4 !== null) rootPane.children.add(team1.player4!!.circle)

        rootPane.children.add(team2.player1.circle)
        if (team2.player2 !== null) rootPane.children.add(team2.player2!!.circle)
        if (team2.player3 !== null) rootPane.children.add(team2.player3!!.circle)
        if (team2.player4 !== null) rootPane.children.add(team2.player4!!.circle)

        val primaryScreenBounds = Screen.getPrimary().visualBounds

        primaryStage?.title = "Football"
        primaryStage?.scene = Scene(rootPane, FieldContext.fieldTotalWidth - 10, FieldContext.fieldTotalHeight - 10, FieldContext.grassColor)
        primaryStage?.x = primaryScreenBounds.minX + primaryScreenBounds.width - FieldContext.fieldTotalWidth - 10
        primaryStage?.y = primaryScreenBounds.minY + primaryScreenBounds.height - FieldContext.fieldTotalHeight - 20
        primaryStage?.isResizable = false
        primaryStage?.show()

        val runner = GameRunner(team1, team2)
        runner.play()

        val transitionsManager = TransitionsManager()
        transitionsManager.play(runner.states)
    }

    private fun createTeams(): Pair<Team, Team> {
        val home = Team(Color.BLUE, listOf(RecoverAndShoot()))
        home.gameSide = GameSide.HOME
        val away = Team(Color.RED, listOf(DumbRusherShoot(UP)))
        away.gameSide = GameSide.AWAY

        return Pair(home, away)
    }
}