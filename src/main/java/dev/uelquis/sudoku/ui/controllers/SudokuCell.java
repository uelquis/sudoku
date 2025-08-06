package dev.uelquis.sudoku.ui.controllers;

import javafx.scene.Node;
import javafx.util.Pair;

import java.util.ArrayList;

sealed interface SudokuCell permits Cell {

    enum ChunkPositions {
        _00(new Pair<>(0,0)),
        _01(new Pair<>(0,1)),
        _02(new Pair<>(0,2)),

        _10(new Pair<>(1,0)),
        _11(new Pair<>(1,1)),
        _12(new Pair<>(1,2)),

        _20(new Pair<>(2,0)),
        _21(new Pair<>(2,1)),
        _22(new Pair<>(2,2));

        public final Pair<Integer, Integer> value;

        ChunkPositions(Pair<Integer, Integer> pos) {
            this.value = pos;
        }
    }

    Pair<Integer, Integer> getPosition();

    ArrayList<Node> getRow();

    ArrayList<Node> getColumn();
}
