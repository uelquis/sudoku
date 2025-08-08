package dev.uelquis.sudoku;

import dev.uelquis.sudoku.ui.controllers.GameController;
import dev.uelquis.sudoku.ui.controllers.SudokuGame;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import lombok.val;

import java.io.IOException;
import java.net.URL;

public class SceneBuilder {
    private SceneBuilder() {}

    private static URL getFXML(String name) { return  SceneBuilder.class.getResource(name); }

    public static Scene buildMainMenu() throws IOException {
        try {

            return new Scene(FXMLLoader.load(getFXML("/MainMenu.fxml")));
        } catch (IOException e) { System.out.println(e.getMessage()); }

        return null;
    }

    public static Scene buildGameScene() {
        try {
            val gameScene = new Scene(FXMLLoader.load(getFXML("/Game.fxml")));

            gameScene.addEventHandler(KeyEvent.KEY_PRESSED, GameController::onKeyPressed);

            return gameScene;
        } catch (IOException e) { System.out.printf("failed to get %s\n", e.getMessage()); }

        return null;
    }
}
