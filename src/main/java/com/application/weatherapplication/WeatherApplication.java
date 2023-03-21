package com.application.weatherapplication;

import com.application.dataobjects.Forecast;
import com.application.api.DataConversion;
import com.application.api.Querying;
import com.application.dataobjects.IDataProvider;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.awt.*;
import java.awt.event.ActionListener;
import java.io.IOException;

public class WeatherApplication extends Application {

    private TrayIcon icon;
    private Forecast forecast;

    @Override
    public void start(Stage stage) throws IOException {
        addIconTray();

        FXMLLoader fxmlLoader = new FXMLLoader(WeatherApplication.class.getResource("WeatherAppView.fxml"));

        //gets the instance of the controller class
        Pane p = fxmlLoader.load();
        WeatherAppController controller = fxmlLoader.getController();
        Scene scene = new Scene(p, 1280, 800);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();

        //this makes sure that the tray icon is deleted when the window closes
        stage.setOnCloseRequest(windowEvent -> {
            var tray = SystemTray.getSystemTray();
            tray.remove(icon);
        });

        //uses the controller class to load the border pane with the "index" view
        controller.loadBorderPane();



        //making sure that the information loads
        this.forecast = getDataObject();
       // System.out.println(forecast);

    }

    private void addIconTray()
    {
        var sysTray = SystemTray.getSystemTray();
        var image = Toolkit.getDefaultToolkit().getImage("./resources/Images/tray_icon.png");

        var popup = new PopupMenu();

        var closeItem = new MenuItem("Exit");
        closeItem.addActionListener(closer);

        popup.add(closeItem);

        var trayIcon = new TrayIcon(image, "Open", popup);

        trayIcon.addActionListener(focus);

        try
        {
            sysTray.add(trayIcon);

        }
        catch(Exception e)
        { e.printStackTrace();}

        icon = trayIcon;
    }

    private final ActionListener closer = e -> System.exit(0);

    private final ActionListener focus = e -> {

    };
    public static void main(String[] args) {
        launch();
    }


    /**
     * In the future, this will not be Object
     */
    public static Forecast getDataObject()
    {

        var qu = Querying.getWeatherInformation(Querying.QueryType.FORECAST_24HR);

        return DataConversion.interpretData(qu);
    }


    /*
    * Data Management of the Weather information
    */

}