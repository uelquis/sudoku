package dev.uelquis.sudoku.ui.controllers;

import static io.github.chrisdostert.guardclauses.Guards.guardThat;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
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

        val c = (Label) getCellbyPosition(new Pair<>(cellPos.getKey() + 1, cellPos.getValue() + 1));

        System.out.println(c.getText());
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
            this.validateInteger(GridPane.getRowIndex(cell.getParent())),
            this.validateInteger(GridPane.getColumnIndex(cell.getParent()))
        );

        val cellPosition = new Pair<>(
            this.validateInteger(GridPane.getRowIndex(cell)),
            this.validateInteger(GridPane.getColumnIndex(cell))
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

    @Override
    public Node getCellbyPosition(Pair<Integer, Integer> pos) {
        guardThat("row position", pos.getKey())
            .isGreaterThan(-1)
            .isLessThan(9);

        guardThat("column position", pos.getValue())
            .isGreaterThan(-1)
            .isLessThan(9);

        for(Node chunk : this.sudokuGrid.getChildren()) {
            for(Node cell : ((GridPane)chunk).getChildren()) {
                if(!this.getCellPosition(cell).equals(pos)) continue;

                return cell;

            }
        }

        return null;
    }
}
