package ru.ipolynkina.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/main.fxml"));
        Parent fxmlMain = fxmlLoader.load();

        primaryStage.setTitle("Генератор настроек для TS");
        primaryStage.setMinWidth(450);
        primaryStage.setMinHeight(425);
        primaryStage.setScene(new Scene(fxmlMain, 450, 425));
        primaryStage.show();
    }
}
