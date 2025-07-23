package dev.uelquis.sudoku.ui.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import lombok.val;

import java.net.URL;
import java.util.ResourceBundle;

import static co.nstant.in.pipe.Pipe.apply;

public class GameController implements Initializable {
    @FXML
    public GridPane sudokuGrid;

    @FXML @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        val windowWidth = apply((AnchorPane)sudokuGrid.getParent())
                .pipe(AnchorPane::prefWidthProperty)
                .result()
                .get();
    }

    public void onCellClick(MouseEvent event) {
        System.out.println("Clicked on cell!");
    }
}
