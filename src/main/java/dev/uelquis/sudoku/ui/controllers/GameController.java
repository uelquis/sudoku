package dev.uelquis.sudoku.ui.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import lombok.val;

import java.net.URL;
import java.util.ResourceBundle;


public final class GameController implements Initializable {
    @FXML
    public GridPane sudokuGrid;

    @FXML @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {}

    public void onCellClick(MouseEvent event) {
        val cell = new Cell((Node) event.getSource(), this.sudokuGrid);
        val cellPos = cell.getPosition();

        val row = cell.getRow();
        val column = cell.getColumn();

        row.forEach(c -> {

            if(cellPos.equals(new Cell(c, this.sudokuGrid).getPosition())) return;

            ((Label)c).setStyle("-fx-background-color: #39FF4D;");
        });

        column.forEach(c -> {

            if(cellPos.equals(new Cell(c, this.sudokuGrid).getPosition())) return;

            ((Label)c).setStyle("-fx-background-color: #FFF039;");
        });
    }
}
