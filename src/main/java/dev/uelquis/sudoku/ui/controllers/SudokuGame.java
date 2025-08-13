package dev.uelquis.sudoku.ui.controllers;

import javafx.scene.layout.GridPane;
import javafx.util.Pair;

sealed public interface SudokuGame permits GameController {

    default void initCells(GridPane sudokuGrid) {
        // chunk 1
        Cell.getCellFromPosition(new Pair<>(0,0), sudokuGrid).setNumber(5);
        Cell.getCellFromPosition(new Pair<>(0,1), sudokuGrid).setNumber(3);
        Cell.getCellFromPosition(new Pair<>(1,0), sudokuGrid).setNumber(6);
        Cell.getCellFromPosition(new Pair<>(2,1), sudokuGrid).setNumber(9);
        Cell.getCellFromPosition(new Pair<>(2,2), sudokuGrid).setNumber(8);

        //chunk 2
        Cell.getCellFromPosition(new Pair<>(0,4), sudokuGrid).setNumber(7);
        Cell.getCellFromPosition(new Pair<>(1,3), sudokuGrid).setNumber(1);
        Cell.getCellFromPosition(new Pair<>(1,4), sudokuGrid).setNumber(9);
        Cell.getCellFromPosition(new Pair<>(1,5), sudokuGrid).setNumber(5);

        //chunk 3
        Cell.getCellFromPosition(new Pair<>(2,7), sudokuGrid).setNumber(6);

        //chunk 4
        Cell.getCellFromPosition(new Pair<>(3,0), sudokuGrid).setNumber(8);
        Cell.getCellFromPosition(new Pair<>(4,0), sudokuGrid).setNumber(4);
        Cell.getCellFromPosition(new Pair<>(5,0), sudokuGrid).setNumber(7);

        //chunk 5
        Cell.getCellFromPosition(new Pair<>(3,4), sudokuGrid).setNumber(6);
        Cell.getCellFromPosition(new Pair<>(4,3), sudokuGrid).setNumber(8);
        Cell.getCellFromPosition(new Pair<>(4,5), sudokuGrid).setNumber(3);
        Cell.getCellFromPosition(new Pair<>(5,4), sudokuGrid).setNumber(2);

        //chunk 6
        Cell.getCellFromPosition(new Pair<>(3,8), sudokuGrid).setNumber(3);
        Cell.getCellFromPosition(new Pair<>(4,8), sudokuGrid).setNumber(1);
        Cell.getCellFromPosition(new Pair<>(5,8), sudokuGrid).setNumber(6);

        //chunk 7
        Cell.getCellFromPosition(new Pair<>(6,1), sudokuGrid).setNumber(6);

        //chunk 8
        Cell.getCellFromPosition(new Pair<>(7,3), sudokuGrid).setNumber(4);
        Cell.getCellFromPosition(new Pair<>(7,4), sudokuGrid).setNumber(1);
        Cell.getCellFromPosition(new Pair<>(7,5), sudokuGrid).setNumber(9);
        Cell.getCellFromPosition(new Pair<>(8,4), sudokuGrid).setNumber(8);

        //chunk 9
        Cell.getCellFromPosition(new Pair<>(6,6), sudokuGrid).setNumber(2);
        Cell.getCellFromPosition(new Pair<>(6,7), sudokuGrid).setNumber(8);
        Cell.getCellFromPosition(new Pair<>(7,8), sudokuGrid).setNumber(5);
        Cell.getCellFromPosition(new Pair<>(8,7), sudokuGrid).setNumber(7);
        Cell.getCellFromPosition(new Pair<>(8,8), sudokuGrid).setNumber(9);
    }
}
