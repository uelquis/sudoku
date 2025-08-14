package dev.uelquis.sudoku.ui.controllers;

import javafx.scene.layout.GridPane;
import javafx.util.Pair;
import lombok.Getter;
import lombok.val;

import java.util.HashMap;

sealed public interface SudokuGame permits GameController {
    enum GameState {
        NOT_STARTED("not started"),
        UNFINISHED("unfinished"),
        FINISHED("finished");

        @Getter
        private final String label;

        GameState(String label) {
            this.label = label;
        }
    }

    default void initCells(GridPane sudokuGrid) {

        val initValues = new HashMap<Pair<Integer, Integer>, Integer>();

        // chunk 1
        initValues.put(new Pair<>(0,0), 5);
        initValues.put(new Pair<>(0,1), 3);
        initValues.put(new Pair<>(1,0), 6);
        initValues.put(new Pair<>(2,1), 9);
        initValues.put(new Pair<>(2,2), 8);

        //chunk 2
        initValues.put(new Pair<>(0,5), 7);
        initValues.put(new Pair<>(1,3), 1);
        initValues.put(new Pair<>(1,4), 9);
        initValues.put(new Pair<>(1,5), 5);

        //chunk 3
        initValues.put(new Pair<>(2,7), 6);

        //chunk 4
        initValues.put(new Pair<>(3,0), 8);
        initValues.put(new Pair<>(4,0), 4);
        initValues.put(new Pair<>(5,0), 7);

        //chunk 5
        initValues.put(new Pair<>(3,4), 6);
        initValues.put(new Pair<>(4,3), 8);
        initValues.put(new Pair<>(4,5), 3);
        initValues.put(new Pair<>(5,4), 2);

        //chunk 6
        initValues.put(new Pair<>(3,8), 3);
        initValues.put(new Pair<>(4,8), 1);
        initValues.put(new Pair<>(5,8), 6);

        //chunk 7
        initValues.put(new Pair<>(6,1), 6);

        //chunk 8
        initValues.put(new Pair<>(7,3), 4);
        initValues.put(new Pair<>(7,4), 1);
        initValues.put(new Pair<>(7,5), 9);
        initValues.put(new Pair<>(8,4), 8);

        //chunk 9
        initValues.put(new Pair<>(6,6), 2);
        initValues.put(new Pair<>(6,7), 8);
        initValues.put(new Pair<>(7,8), 5);
        initValues.put(new Pair<>(8,7), 7);
        initValues.put(new Pair<>(8,8), 9);

        initValues.forEach((pos, value) -> {
            Cell.getCellFromPosition(pos, sudokuGrid).setNumber(value);
            Cell.getCellFromPosition(pos, sudokuGrid).setFinal(true);

            Cell.getCellFromPosition(pos, sudokuGrid).setStyle("-fx-text-fill: #008a5b;");
        });
    }
}
