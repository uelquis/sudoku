package dev.uelquis.sudoku.ui.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.util.Pair;
import lombok.val;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static dev.uelquis.sudoku.ui.controllers.SudokuGame.ChunkPositions.*;

public final class GameController implements Initializable, SudokuGame {
    @FXML
    public GridPane sudokuGrid;

    @FXML @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {}

    public void onCellClick(MouseEvent event) {
        val cell = (Node) event.getSource();
        val cellPos = this.getCellPosition(cell);

        val row = this.getRowFromPosition(cellPos);
        val column = this.getColumnFromPosition(cellPos);

        row.forEach(c -> {

            if(cellPos.equals(this.getCellPosition(c))) return;

            ((Label)c).setStyle("-fx-background-color: #39FF4D;");
        });

        column.forEach(c -> {

            if(cellPos.equals(this.getCellPosition(c))) return;

            ((Label)c).setStyle("-fx-background-color: #FFF039;");
        });
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
        if(pos.getKey() < 0 || pos.getKey() > 8)
            throw new IllegalArgumentException("Row position must be between 0 and 8");

        if(pos.getValue() < 0 || pos.getValue() > 8)
            throw new IllegalArgumentException("Column position must be between 0 and 8");

        for(Node chunk : this.sudokuGrid.getChildren()) {
            for(Node cell : ((GridPane)chunk).getChildren()) {
                if(!this.getCellPosition(cell).equals(pos)) continue;

                return cell;

            }
        }

        return null;
    }

    @Override
    public ArrayList<Node> getRowFromPosition(Pair<Integer, Integer> pos) {
        val row = new ArrayList<Node>();
        val positions = new ArrayList<Pair<Integer, Integer>>();

        for (int column = 0; column < 9; column++) {
            positions.add(new Pair<>(pos.getKey(), column));
        }

        positions.forEach(position -> {
            row.add(this.getCellbyPosition(position));
        });

        return row;
    }

    @Override
    public ArrayList<Node> getColumnFromPosition(Pair<Integer, Integer> pos) {
        val column = new ArrayList<Node>();
        val positions = new ArrayList<Pair<Integer, Integer>>();

        for (int row = 0; row < 9; row++) {
            positions.add(new Pair<>(row, pos.getValue()));
        }

        positions.forEach(position -> {
            column.add(this.getCellbyPosition(position));
        });

        return column;
    }
}
