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

    public static GameState state = GameState.NOT_STARTED;

    @Getter
    private static Cell selectedCell = null;

    @FXML
    public GridPane sudokuGrid;

    @FXML @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initCells(this.sudokuGrid);

        state = GameState.UNFINISHED;
    }

    @FXML
    public void onCellClick(MouseEvent event) {
        val cell = new Cell((Node) event.getSource(), this.sudokuGrid);

        // paint cell selection
        selectCell(cell);

    }

    private void selectCell(Cell cell) {

        val finalCellDefaultStyle = String.format("""
            -fx-background-color: #e9ecef;
            -fx-text-fill: %s;
        """, "#008a5b");

        val finalCellHighlightedtStyle = String.format("""
            -fx-background-color: #90e8ff;
            -fx-text-fill: %s;
        """, "#008a5b");

        if(!Objects.isNull(selectedChunk)) selectedChunk.getChildren().forEach(n -> {
            if(Objects.isNull(n.getUserData())) n.setStyle("-fx-background-color: #e9ecef;");
            else n.setStyle(finalCellDefaultStyle);
        });

        if(!Objects.isNull(selectedRow)) selectedRow.forEach(n -> {
            if(Objects.isNull(n.getUserData())) n.setStyle("-fx-background-color: #e9ecef;");
            else n.setStyle(finalCellDefaultStyle);
        });

        if(!Objects.isNull(selectedColumn)) selectedColumn.forEach(n -> {
            if(Objects.isNull(n.getUserData())) n.setStyle("-fx-background-color: #e9ecef;");
            else n.setStyle(finalCellDefaultStyle);
        });

        selectedCell = cell;
        selectedChunk = cell.getChunk();
        selectedRow = cell.getRow();
        selectedColumn = cell.getColumn();

        cell.getChunk().getChildren().forEach(n -> {
            if(Objects.isNull(n.getUserData())) n.setStyle("-fx-background-color: #90e8ff;");
            else n.setStyle(finalCellHighlightedtStyle);
        });

        cell.getRow().forEach(n -> {
            if(Objects.isNull(n.getUserData())) n.setStyle("-fx-background-color: #90e8ff;");
            else n.setStyle(finalCellHighlightedtStyle);
        });

        cell.getColumn().forEach(n -> {
            if(Objects.isNull(n.getUserData())) n.setStyle("-fx-background-color: #90e8ff;");
            else n.setStyle(finalCellHighlightedtStyle);
        });

        if (cell.isFinal()) {
            cell.setStyle(String.format("-fx-background-color: #fffe4e; -fx-text-fill: %s", "#008a5b"));
        } else {
            cell.setStyle("-fx-background-color: #fffe4e;");
        }
    }

    public static void onKeyPressed(KeyEvent key) {
        val selectedCell = GameController.getSelectedCell();
        if(Objects.isNull(selectedCell)) return;

        if(isDelete(key) && !selectedCell.isFinal()) {
            selectedCell.setNumber(0);
            return;
        }

        if(!isNumeric(key)) return;

        val number = parseNumber(key);
        if(number == 0) return;

        if(selectedCell.isFinal() || selectedCell.getNumber() != 0) return;

        selectedCell.setNumber(number);

        // cell validation works, but it doesn't directly help the user find a solution!
        System.out.println(CellValidator.validate(selectedCell)? "cell is valid" : "cell is not valid");
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

    private static boolean isDelete(KeyEvent key) {
        return key.getCode() == KeyCode.DELETE || key.getCode() == KeyCode.BACK_SPACE;
    }
}
