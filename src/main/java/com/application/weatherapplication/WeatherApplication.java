package com.application.weatherapplication;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class WeatherApplication extends Application {

    private TrayIcon icon;

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
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent windowEvent) {
                var tray = SystemTray.getSystemTray();
                tray.remove(icon);
            }
        });

        //uses the controller class to load the border pane with the "index" view
        controller.loadBorderPane();
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
        {}

        icon = trayIcon;
    }

    private ActionListener closer = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            System.exit(0);
        }
    };

    private ActionListener focus = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e)
        {

        }
    };
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