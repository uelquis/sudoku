package dev.uelquis.sudoku.ui.controllers;

import dev.uelquis.sudoku.SceneBuilder;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import lombok.val;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Supplier;

import static co.nstant.in.pipe.Pipe.apply;

public final class MainMenuController implements Initializable {
    @FXML
    public VBox vbox;
    @FXML
    public Label gameTitle;

    public void startGame(ActionEvent event) {
        goTo((Node) event.getTarget(), SceneBuilder::buildGameScene);
    }

    @FXML @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        val windowWidth = apply((AnchorPane)vbox.getParent())
            .pipe(AnchorPane::prefWidthProperty)
            .result()
            .get();

    }

    private void goTo(Node node, Supplier<Scene> buildScene) {
        val window = (Stage) node.getScene().getWindow();

        window.setScene(buildScene.get());
    }
}


