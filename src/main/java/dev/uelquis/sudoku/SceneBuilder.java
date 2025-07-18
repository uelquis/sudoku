package dev.uelquis.sudoku;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import java.io.IOException;
import java.net.URL;

public class SceneBuilder {
    private SceneBuilder() {}

    public static SceneBuilder build() {
        return new SceneBuilder();
    }

    private URL getFXML(String name) { return  getClass().getResource(name); }

    public Scene mainMenu() throws IOException {
        return new Scene(FXMLLoader.load(getFXML("/MainMenu.fxml")));
    }

    public Scene gameScene() throws IOException {
        return new Scene(FXMLLoader.load(getFXML("/Game.fxml")));
    }
}
