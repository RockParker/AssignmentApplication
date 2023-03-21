package com.application.weatherapplication;

import com.application.dataobjects.Forecast;
import com.application.dataobjects.IDataProvider;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.awt.*;
import java.io.File;
import java.time.LocalDateTime;

public class WeatherViewController implements IDataProvider
{

    @FXML
    private Label cityname;

    @FXML
    ImageView MainIcon;

    private Forecast forecast;

    @FXML
    private ImageView currentweatherlogo;

    @FXML
    private ImageView conditionimage1, conditionimage2, conditionimage3, conditionimage4, conditionimage5;

    @FXML
    private ImageView conditionimage6, conditionimage7, conditionimage8, conditionimage9, conditionimage10;


    private ImageView[] conditionImages;


    @FXML
    public void initialize()
    {
        //cityname.setText("Changed The Icon in the controller class");

    }

    private void SetIcon(String condition, ImageView view)
    {
        File newFile = new File("Images/default_icon-2.jpg");
        //var imageUrl = WeatherViewController.class.getResource("Images/default_icon.png").toString();
        Image image = new Image(newFile.toURI().toString(), true);
        //System.out.println(imageUrl);



        //here, based on the condition, we
        //will set the desired image to be displayed in the image view
        if (condition.equals("")) {
        }

        view.setImage(image);
    }


    @Override
    public void update(Forecast forecast)
    {

        conditionImages =new ImageView[]
                {conditionimage1, conditionimage2, conditionimage3,
                        conditionimage4, conditionimage5, conditionimage6,
                        conditionimage7, conditionimage8, conditionimage9, conditionimage10};


        //do stuff with the forecast
        this.forecast = forecast;
        this.cityname.setText("WORKED");

        if(forecast != null)
        {
            var url = forecast.getCurrent().getIconURL();
            var temp = this.getClass().getResource("WeatherIcons"+url);
            currentweatherlogo.setImage(new Image(temp.toString()));
        }

        var days = forecast.getForecast().getDays();
        var todayHours = days.get(0).getHours();

        var hour = LocalDateTime.now().getHour();


        for(int i = 0; i < 10; i++)
        {
            var url = todayHours.get(hour).getIconURL();
            var temp = this.getClass().getResource("WeatherIcons"+url);
            conditionImages[i].setImage(new Image(temp.toString()));
            hour++;

            if(hour == 23)
            {
                todayHours = days.get(1).getHours();
                hour = 0;
            }

        }

        /*
        var url = todayHours.get(hour).getIconURL();
        var temp = this.getClass().getResource("WeatherIcons"+url);
        conditionimage1.setImage(new Image(temp.toString()));
        hour++;


        url = todayHours.get(hour).getIconURL();
        temp = this.getClass().getResource("WeatherIcons"+url);
        conditionimage2.setImage(new Image(temp.toString()));
        hour++;

        url = todayHours.get(hour).getIconURL();
        temp = this.getClass().getResource("WeatherIcons"+url);
        conditionimage3.setImage(new Image(temp.toString()));
        hour++;

        url = todayHours.get(hour).getIconURL();
        temp = this.getClass().getResource("WeatherIcons"+url);
        conditionimage4.setImage(new Image(temp.toString()));
        hour++;

        url = todayHours.get(hour).getIconURL();
        temp = this.getClass().getResource("WeatherIcons"+url);
        conditionimage5.setImage(new Image(temp.toString()));
        hour++;

        url = todayHours.get(hour).getIconURL();
        temp = this.getClass().getResource("WeatherIcons"+url);
        conditionimage6.setImage(new Image(temp.toString()));*/



    }

}
