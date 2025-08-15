package dev.uelquis.sudoku.ui.controllers;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.util.Pair;
import lombok.Getter;
import lombok.SneakyThrows;
import lombok.val;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;

import static dev.uelquis.sudoku.ui.controllers.SudokuCell.ChunkPositions.*;

//https://pt.wikipedia.org/wiki/Sudoku

final class Cell implements SudokuCell {

    private record CellData(boolean isFinal) {}

    public static ArrayList<Cell> getAllCells(GridPane sudokuGrid) {
        val cells = new ArrayList<Cell>();

        sudokuGrid.getChildren().stream()
            .flatMap(chunk -> ((GridPane)chunk).getChildren().stream())
            .forEach(node -> cells.add(new Cell(node, sudokuGrid)));

        return cells;
    }

    public static Cell getCellFromPosition(Pair<Integer, Integer> pos, GridPane sudokuGrid) {
        if(pos.getKey() < 0 || pos.getKey() > 8)
            throw new IllegalArgumentException("Row position must be between 0 and 8");

        if(pos.getValue() < 0 || pos.getValue() > 8)
            throw new IllegalArgumentException("Column position must be between 0 and 8");

        AtomicReference<Node> node = new AtomicReference<>();

        sudokuGrid.getChildren().stream()
        .flatMap(chunk -> ((GridPane)chunk).getChildren().stream())
        .forEach(_node -> {
            if(!new Cell(_node, sudokuGrid).getPosition().equals(pos)) return;

            node.set(_node);
        });

        return new Cell(node.get(), sudokuGrid);
    }

    @Getter
    private final Node node;
    private final GridPane sudokuGrid;

    public Cell(Node node, GridPane sudokuGrid) {
        this.node = node;
        this.sudokuGrid = sudokuGrid;
    }

    public void setFinal(boolean isFinal) {
        this.node.setUserData(new CellData(true));
    }

    public boolean isFinal() {
        val data = this.node.getUserData();
        if(Objects.isNull(data)) return false;

        return ((CellData)data).isFinal;
    }

    private Pair<Integer, Integer> offsetCellPosition(int offsetRow, int offsetColumn, Pair<Integer, Integer> pos) {
        return new Pair<>(pos.getKey() + offsetRow, pos.getValue() + offsetColumn);
    }

    private int validateInteger(Integer num) {
        return num == null ? 0 : num;
    }

    public int getNumber() {
        val text = ((Label)this.node).getText();

        if(text.isEmpty()) return 0;

        return Integer.parseInt(text);
    }

    @SneakyThrows
    public void setNumber(int number) {
        if(this.isFinal()) throw new Exception("this cell is final!");

        val label = ((Label)this.node);

        if(number == 0) label.setText("");
        else label.setText(Integer.toString(number));
    }

    public void setStyle(String style) {
        this.node.setStyle(style);
    }

    public GridPane getChunk() {
        return (GridPane) this.node.getParent();
    }

    @Override
    public Pair<Integer, Integer> getPosition() {
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
            this.validateInteger(GridPane.getRowIndex(this.node.getParent())),
            this.validateInteger(GridPane.getColumnIndex(this.node.getParent()))
        );

        val cellPosition = new Pair<>(
            this.validateInteger(GridPane.getRowIndex(this.node)),
            this.validateInteger(GridPane.getColumnIndex(this.node))
        );

        val chunk = Arrays.stream(values())
            .filter(pos -> chunkPosition.equals(pos.value))
            .findFirst().get();

        return switch (chunk) {
            case _00 -> cellPosition;
            case _01 -> offsetCellPosition(0, 3, cellPosition);
            case _02 -> offsetCellPosition(0, 6, cellPosition);

            case _10 -> offsetCellPosition(3, 0, cellPosition);
            case _11 -> offsetCellPosition(3,3, cellPosition);
            case _12 -> offsetCellPosition(3, 6, cellPosition);

            case _20 -> offsetCellPosition(6, 0, cellPosition);
            case _21 -> offsetCellPosition(6, 3, cellPosition);
            case _22 -> offsetCellPosition(6, 6, cellPosition);
        };
    }

    @Override
    public ArrayList<Node> getRow() {
        val row = new ArrayList<Node>();
        val positions = new ArrayList<Pair<Integer, Integer>>();

        for (int column = 0; column < 9; column++) {
            positions.add(new Pair<>(this.getPosition().getKey(), column));
        }

        positions.forEach(position -> {
            row.add(Cell.getCellFromPosition(position, this.sudokuGrid).getNode());
        });

        return row;
    }

    @Override
    public ArrayList<Node> getColumn() {
        val column = new ArrayList<Node>();
        val positions = new ArrayList<Pair<Integer, Integer>>();

        for (int row = 0; row < 9; row++) {
            positions.add(new Pair<>(row, this.getPosition().getValue()));
        }

        positions.forEach(position -> {
            column.add(Cell.getCellFromPosition(position, this.sudokuGrid).getNode());
        });

        return column;
    }
}
