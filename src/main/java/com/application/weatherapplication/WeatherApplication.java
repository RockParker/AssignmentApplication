package com.application.weatherapplication;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class WeatherApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(WeatherApplication.class.getResource("WeatherAppView.fxml"));

        //gets the instance of the controller class
        Pane p = fxmlLoader.load();
        WeatherAppController controller = fxmlLoader.getController();

        Scene scene = new Scene(p, 1280, 800);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();

        //uses the controller class to load the border pane with the "index" view
        controller.loadBorderPane();
    }

    public static void main(String[] args) {
        launch();
    }


    /**
     * In the future, this will not be Object
     */
    public static Object getDataObject()
    {
        return null;
    }
}