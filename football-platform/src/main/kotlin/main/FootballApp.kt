package main

import football.Ball
import football.FieldContext
import football.Team
import helpers.Side
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

        val team1 = Team(Color.BLUE, Side.LEFT)
        val team2 = Team(Color.RED, Side.RIGHT)

        rootPane.children.add(team1.player1.circle)
        rootPane.children.add(team1.player2.circle)
        rootPane.children.add(team2.player1.circle)
        rootPane.children.add(team2.player2.circle)

        primaryStage?.title = "Football"
        primaryStage?.scene = Scene(rootPane, FieldContext.width - 10, FieldContext.height - 10, FieldContext.grassColor)
        primaryStage?.centerOnScreen()
        primaryStage?.isResizable = false
        primaryStage?.show()
    }
}