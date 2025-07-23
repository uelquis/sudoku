package dev.uelquis.sudoku;

/*
* Sudoku explained: https://www.youtube.com/watch?v=8zRXDsGydeQ
*
* requisitos: https://github.com/digitalinnovationone/exercicios-java-basico/blob/main/projetos/2%20-%20Programa%C3%A7%C3%A3o%20Orientada%20a%20Objetos%20e%20Estruturas%20de%20Dados%20com%20Java.md
*
* learning sources:
*   https://www.youtube.com/watch?v=LMdjhuYSrqg
*   https://www.youtube.com/watch?v=9XJicRt_FaI
*
* mvn compile -> mvn javafx:run
*
* */

import javafx.application.Application;
import javafx.stage.Stage;
import lombok.val;
import java.io.IOException;

public class Main extends Application {

    public static void main(String... args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Sudoku");
        primaryStage.setWidth(615);
        primaryStage.setHeight(638);
        primaryStage.setResizable(false);

        //val indexStyleSheet = getClass().getResource("/style/index.css").toExternalForm();

        try {

            val mainMenu = SceneBuilder.build().mainMenu();

            primaryStage.setScene(mainMenu);

        } catch (IOException e) { System.out.println(e.getMessage()); }

        primaryStage.show();
    }


}