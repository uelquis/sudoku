package dev.uelquis.sudoku.ui.controllers;

import dev.uelquis.sudoku.SceneBuilder;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import lombok.val;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static co.nstant.in.pipe.Pipe.apply;

public class MainMenuController implements Initializable {
    @FXML
    public VBox vbox;
    @FXML
    public Label gameTitle;

    public void startGame(ActionEvent event) {
        val window = (Stage) ((Node) event.getTarget()).getScene().getWindow();
        try {

            window.setScene(SceneBuilder.build().gameScene());

        } catch (IOException e) { System.out.println(e.getMessage()); }
    }

    @FXML @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        val windowWidth = apply((AnchorPane)vbox.getParent())
            .pipe(AnchorPane::prefWidthProperty)
            .result()
            .get();

        vbox.setPrefWidth(windowWidth);

    }
}


