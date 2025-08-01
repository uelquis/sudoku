package dev.uelquis.sudoku.ui.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.util.Pair;
import lombok.val;

import java.net.URL;
import java.util.ResourceBundle;

import static dev.uelquis.sudoku.ui.controllers.SudokuGame.ChunkPositions.*;

public final class GameController implements Initializable, SudokuGame {
    @FXML
    public GridPane sudokuGrid;

    @FXML @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {}

    public void onCellClick(MouseEvent event) {
        val cell = (Node) event.getSource();
        val cellPos = getCellPosition(cell);

        System.out.println(cellPos.getKey() + ":" + cellPos.getValue());
    }

    private Pair<Integer, Integer> offsetCellPosition(int offsetRow, int offsetColumn, Pair<Integer, Integer> pos) {
        return new Pair<>(pos.getKey() + offsetRow, pos.getValue() + offsetColumn);
    }

    private int validateInteger(Integer num) {
        return num == null ? 0 : num;
    }

    @Override
    public Pair<Integer, Integer> getCellPosition(Node cell) {
        /*
        *   0,0 - 0,1 - 0,2       0,8
        *   1,0 - 1,1 - 1,2 . . . 1,8
        *   2,0 - 2,1 - 2,2       2,8
        *           .              .
        *           .              .
        *           .              .
        *   8,0 - 8,1 - 8,2 . . . 8,8
        * */

        val chunkPosition = new Pair<>(
            validateInteger(GridPane.getRowIndex(cell.getParent())),
            validateInteger(GridPane.getColumnIndex(cell.getParent()))
        );

        val cellPosition = new Pair<>(
            validateInteger(GridPane.getRowIndex(cell)),
            validateInteger(GridPane.getColumnIndex(cell))
        );

        if(chunkPosition.equals(_00.value)) {
            return cellPosition;
        }
        else if(chunkPosition.equals(_01.value)) {
            return offsetCellPosition(0, 3, cellPosition);
        }
        else if(chunkPosition.equals(_02.value)) {
            return offsetCellPosition(0, 6, cellPosition);
        }

        else if(chunkPosition.equals(_10.value)) {
            return offsetCellPosition(3, 0, cellPosition);
        }
        else if(chunkPosition.equals(_11.value)) {
            return offsetCellPosition(3,3, cellPosition);
        }
        else if(chunkPosition.equals(_12.value)) {
            return offsetCellPosition(3, 6, cellPosition);
        }

        else if(chunkPosition.equals(_20.value)) {
            return offsetCellPosition(6, 0, cellPosition);
        }
        else if(chunkPosition.equals(_21.value)) {
            return offsetCellPosition(6, 3, cellPosition);
        }
        else if(chunkPosition.equals(_22.value)) {
            return offsetCellPosition(6, 6, cellPosition);
        }
        else {
            return null;
        }
    }
}
