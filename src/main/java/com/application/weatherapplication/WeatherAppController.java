package com.application.weatherapplication;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

/**
 * This class is the controller class for the design aspect of the window
 * this means that this is where the logic that goes with the design is put.
 * This includes button click handlers, and any logic that changes any content in the UI
 */
public class WeatherAppController
{
    @FXML
    private Label MenuLabel;
    @FXML
    private Button weatherSelector, calendarSelector;

    @FXML
    private BorderPane ViewHost;

    private Pane weatherView, calendarView;

    public void loadBorderPane()
    {
        try
        {
            weatherView = FXMLLoader.load(getClass().getResource("WeatherViewView.fxml"));
            calendarView = FXMLLoader.load(getClass().getResource("CalendarViewView.fxml"));
        }

        catch (Exception e)
        {
            throw new RuntimeException(e);
        }

        setContent(weatherView);
    }

    private void setContent(Pane pane)
    {
        ViewHost.setCenter(null);
        ViewHost.setCenter(pane);
    }

    @FXML
    protected void WeatherClick()
    {
        setContent(weatherView);
    }

    @FXML
    protected void CalendarClick()
    {
        setContent(calendarView);
    }
}