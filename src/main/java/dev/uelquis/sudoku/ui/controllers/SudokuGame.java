package dev.uelquis.sudoku.ui.controllers;


sealed public interface SudokuGame permits GameController {

    default boolean validateCell() {
        return validateChunk() && validateColumn() && validateRow();
    }

    boolean validateChunk();

    boolean validateColumn();

    boolean validateRow();
}
