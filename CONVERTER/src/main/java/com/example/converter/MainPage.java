package com.example.converter;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainPage extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(MainPage.class.getResource("mainPage.fxml"));
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
        stage.show();

    }
}
