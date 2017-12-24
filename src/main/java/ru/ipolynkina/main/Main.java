package ru.ipolynkina.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main extends Application {

    public final static ConfigurableApplicationContext ctx = new ClassPathXmlApplicationContext("spring/spring.xml");

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/fxml/main.fxml"));
        Parent fxmlMain = fxmlLoader.load();

        primaryStage.setTitle("Генератор настроек для TS");
        primaryStage.setMinWidth(450);
        primaryStage.setMinHeight(425);
        primaryStage.setScene(new Scene(fxmlMain, 450, 425));

        ContextMenu contextMenu = new ContextMenu();
        MenuItem menuItem = new MenuItem("О программе");
        menuItem.setOnAction(event -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Генератор настроек для TS");
            alert.setHeaderText("version: beta");
            alert.setContentText("release: xx.12.2017\n" + "author: Irina Polynkina\n" + "email: irina.polynkina.dex@yandex.ru");
            alert.showAndWait();
        });

        contextMenu.getItems().add(menuItem);
        fxmlMain.setOnContextMenuRequested(event -> contextMenu.show(primaryStage, event.getScreenX(), event.getScreenY()));
        primaryStage.show();
    }
}
