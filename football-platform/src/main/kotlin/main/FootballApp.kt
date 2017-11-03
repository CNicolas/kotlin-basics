package main

import football.FieldContext
import football.game.Ball
import football.game.GameRunner
import football.game.TransitionsManager
import football.player.TeamFactory
import helpers.GameSide
import javafx.application.Application
import javafx.scene.Scene
import javafx.scene.layout.BorderPane
import javafx.scene.paint.Color
import javafx.stage.Stage

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

        rootPane.children.add(Ball.instance.circle)

        val factory = TeamFactory()
        val team1 = factory.DoesNothingUP_DoesNothingDOWN(Color.BLUE, GameSide.HOME)
        val team2 = factory.FixedGoalKeeper_RunAndShootStraightUP(Color.RED, GameSide.AWAY)

        rootPane.children.add(team1.player1.circle)
        if (team1.player2 !== null) rootPane.children.add(team1.player2!!.circle)
        if (team1.player3 !== null) rootPane.children.add(team1.player3!!.circle)
        if (team1.player4 !== null) rootPane.children.add(team1.player4!!.circle)

        rootPane.children.add(team2.player1.circle)
        if (team2.player2 !== null) rootPane.children.add(team2.player2!!.circle)
        if (team2.player3 !== null) rootPane.children.add(team2.player3!!.circle)
        if (team2.player4 !== null) rootPane.children.add(team2.player4!!.circle)

        primaryStage?.title = "Football"
        primaryStage?.scene = Scene(rootPane, FieldContext.width - 10, FieldContext.height - 10, FieldContext.grassColor)
        primaryStage?.centerOnScreen()
        primaryStage?.isResizable = false
        primaryStage?.show()

        val runner = GameRunner(team1, team2, turns = 15)
        runner.play()

        val transitionsManager = TransitionsManager()
        transitionsManager.play(runner.states)
    }
}