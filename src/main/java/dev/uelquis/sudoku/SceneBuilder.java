package dev.uelquis.sudoku;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

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

            return new Scene(FXMLLoader.load(getFXML("/Game.fxml")));
        } catch (IOException e) { System.out.println(e.getMessage()); }

        return null;
    }

    public static Scene buildConfigGameScene() {
        try {

            return new Scene(FXMLLoader.load(getFXML("/ConfigGame.fxml")));
        } catch (IOException e) { System.out.println(e.getMessage()); }

        return null;
    }
}
