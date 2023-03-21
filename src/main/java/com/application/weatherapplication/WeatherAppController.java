package com.application.weatherapplication;
import com.application.api.DataConversion;
import com.application.api.Querying;
import com.application.dataobjects.Forecast;
import com.application.dataobjects.IDataProvider;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

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
    private VBox buttonHost;

    @FXML
    private BorderPane ViewHost;

    private Pane weatherView, calendarView;

    private IDataProvider weatherViewController, CalendarViewController;

    private Forecast forecast;


    public void loadBorderPane()
    {
        try
        {
            var loader = new FXMLLoader(getClass().getResource("WeatherViewView.fxml"));
            weatherView = loader.load();
            weatherViewController = loader.getController();
            calendarView = FXMLLoader.load(getClass().getResource("CalendarViewView.fxml"));


            update(weatherViewController);


        }

        catch (Exception e)
        {
            e.printStackTrace();
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
        update(weatherViewController);
    }

    @FXML
    protected void CalendarClick()
    {
        setContent(calendarView);
    }

    protected void update(IDataProvider entity)
    {
        if(entity == null)
            return;


        forecast = DataConversion.interpretData(Querying.getWeatherInformation(Querying.QueryType.FORECAST_WEEK));

        entity.update(forecast);
    }
}