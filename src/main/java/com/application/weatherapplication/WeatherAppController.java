package com.application.weatherapplication;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

/**
 * This class is the controller class for the design aspect of the window
 * this means that this is where the logic that goes with the design is put.
 * This includes button click handlers, and any logic that changes any content in the UI
 */
public class WeatherAppController {
    @FXML
    private Label welcomeText;


    @FXML
    /**
     * this is a button event handler
     */
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}