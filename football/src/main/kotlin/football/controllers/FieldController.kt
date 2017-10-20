package football.controllers

import javafx.fxml.FXML
import javafx.scene.shape.Circle
import javafx.scene.shape.Line
import javafx.scene.shape.Rectangle

class FieldController {
    val width = 500.0
    val height = 300.0

    @FXML private val mediane: Line? = null
    @FXML private val centralRound: Circle? = null
    @FXML private val leftSurface: Rectangle? = null
    @FXML private val rightSurface: Rectangle? = null
}