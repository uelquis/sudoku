package dev.uelquis.sudoku.ui.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import lombok.Getter;
import lombok.val;

import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;


/*
*  TODO:
*       1. finish game logic
*       2. use keyboard arrows to select cells
*           2A. if no cell is selected, select the 0,0 one
* */

public final class GameController implements Initializable, SudokuGame {
    private static ArrayList<Node> selectedRow = null;
    private static ArrayList<Node> selectedColumn = null;
    private static GridPane selectedChunk = null;

    @Getter
    private static Cell selectedCell = null;

    @FXML
    public GridPane sudokuGrid;

    @FXML @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {}

    @FXML
    public void onCellClick(MouseEvent event) {
        val cell = new Cell((Node) event.getSource(), this.sudokuGrid);

        // paint cell selection
        selectCell(cell);

        //validateCell();
    }

    private void selectCell(Cell cell) {

        if(!Objects.isNull(selectedChunk)) {
            selectedChunk.getChildren().forEach(n -> n.setStyle("-fx-background-color: #e9ecef;"));
        }

        if(!Objects.isNull(selectedRow)) {
            selectedRow.forEach(n -> n.setStyle("-fx-background-color: #e9ecef;"));
        }

        if(!Objects.isNull(selectedColumn)) {
            selectedColumn.forEach(n -> n.setStyle("-fx-background-color: #e9ecef;"));
        }

        selectedCell = cell;
        selectedChunk = cell.getChunk();
        selectedRow = cell.getRow();
        selectedColumn = cell.getColumn();

        cell.getChunk().getChildren()
            .forEach(n -> n.setStyle("-fx-background-color: #90e8ff;"));

        cell.getRow()
            .forEach(n -> n.setStyle("-fx-background-color: #90e8ff;"));

        cell.getColumn()
            .forEach(n -> n.setStyle("-fx-background-color: #90e8ff;"));

        cell.setStyle("-fx-background-color: #fffe4e;");
    }

    @Override
    public boolean validateChunk() {
        return false;
    }

    @Override
    public boolean validateColumn() {
        return false;
    }

    @Override
    public boolean validateRow() {
        return false;
    }

    public static void onKeyPressed(KeyEvent key) {
        if(!isNumeric(key)) return;

        val selectedCell = GameController.getSelectedCell();
        if(Objects.isNull(selectedCell)) return;

        val number = parseNumber(key);
        if(number == 0) return;

        selectedCell.setNumber(number);
    }

    private static int parseNumber(KeyEvent key) {
        return Integer.parseInt(key.getText());
    }

    private static boolean isNumeric(KeyEvent key) {
        return switch (key.getCode()) {
            case KeyCode.DIGIT0,
                 KeyCode.DIGIT6,
                 KeyCode.DIGIT1,
                 KeyCode.DIGIT2,
                 KeyCode.DIGIT3,
                 KeyCode.DIGIT4,
                 KeyCode.DIGIT5,
                 KeyCode.DIGIT7,
                 KeyCode.DIGIT8,
                 KeyCode.DIGIT9,

                 KeyCode.NUMPAD0,
                 KeyCode.NUMPAD1,
                 KeyCode.NUMPAD2,
                 KeyCode.NUMPAD3,
                 KeyCode.NUMPAD4,
                 KeyCode.NUMPAD5,
                 KeyCode.NUMPAD6,
                 KeyCode.NUMPAD7,
                 KeyCode.NUMPAD8,
                 KeyCode.NUMPAD9 -> true;
            default -> false;
        };
    }
}
