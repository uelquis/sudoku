package dev.uelquis.sudoku.ui.controllers;

import javafx.scene.Node;
import javafx.scene.control.Label;
import lombok.val;

import java.util.stream.Collectors;

final class CellValidator {

    private CellValidator() {}

    public static boolean validate(Cell cell) {
        return validateChunk(cell) && validateColumn(cell) && validateRow(cell);
    }

    private static boolean validateChunk(Cell cell) {
        val chunk = cell.getChunk();

        // check if all numbers are different

        val originalSize = chunk.getChildren().stream()
            .mapToInt(CellValidator::getLabelNumber)
            .filter(num -> num != 0).count();

        val uniqueSize = chunk.getChildren().stream()
            .mapToInt(CellValidator::getLabelNumber)
            .filter(num -> num != 0)
            .boxed().collect(Collectors.toSet()).size();

        return originalSize == uniqueSize;
    }

    private static int getLabelNumber(Node node) {
        val text = ((Label)node).getText();
        return text.isEmpty() ? 0 : Integer.parseInt(text);
    }

    private static boolean validateColumn(Cell cell) {
        val column = cell.getRow();

        val originalSize = column.stream()
            .mapToInt(CellValidator::getLabelNumber)
            .filter(num -> num != 0).count();

        val uniqueSize = column.stream()
            .mapToInt(CellValidator::getLabelNumber)
            .filter(num -> num != 0)
            .boxed().collect(Collectors.toSet()).size();

        return originalSize == uniqueSize;
    }

    private static boolean validateRow(Cell cell) {
        val row = cell.getColumn();

        val originalSize = row.stream()
                .mapToInt(CellValidator::getLabelNumber)
                .filter(num -> num != 0).count();

        val uniqueSize = row.stream()
                .mapToInt(CellValidator::getLabelNumber)
                .filter(num -> num != 0)
                .boxed().collect(Collectors.toSet()).size();

        return originalSize == uniqueSize;
    }
}
