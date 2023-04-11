package com.application.weatherapplication;

import com.application.api.DataConversion;
import com.application.api.Querying;
import com.application.dataobjects.Forecast;
import com.application.dataobjects.IDataProvider;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


import java.time.LocalDateTime;
import java.util.Calendar;

public class WeatherViewController implements IDataProvider {

    @FXML
    private Button RefreshWeather;
    @FXML
    private Label cityname,localtime;

    @FXML
    ImageView MainIcon;

    private Forecast forecast;

    @FXML
    private ImageView currentweatherlogo;

    @FXML
    private ImageView conditionimage1, conditionimage2, conditionimage3, conditionimage4, conditionimage5;

    @FXML
    private ImageView conditionimage6, conditionimage7, conditionimage8, conditionimage9, conditionimage10;

    @FXML
    private Label time01, time02, time03, time04, time05, time06, time07, time08, time09, time10;
    @FXML
    private Label temp01, temp02, temp03, temp04, temp05, temp06, temp07, temp08, temp09, temp10;
    @FXML
    private Label Today, day01, day02, day03;
    @FXML
    private Label hightemp01, hightemp02, hightemp03, hightemp04;
    @FXML
    private Label lowtemp01, lowtemp02, lowtemp03, lowtemp04;
    private ImageView[] conditionImages;
    private Label[] temps, times, weekdays, hightemps, lowtemps;


    @FXML
    public void initialize()
    {
        refreshWeather();
    }


    @Override
    public void update(Forecast forecast) {

        conditionImages = new ImageView[]
                {conditionimage1, conditionimage2, conditionimage3,
                        conditionimage4, conditionimage5, conditionimage6,
                        conditionimage7, conditionimage8, conditionimage9, conditionimage10};

        temps = new Label[]
                {temp01, temp02, temp03, temp04, temp05, temp06, temp07, temp08, temp09, temp10};
        times = new Label[]
                {time01, time02, time03, time04, time05, time06, time07, time08, time09, time10};
        weekdays = new Label[]
                {Today, day01, day02, day03};
        hightemps = new Label[]
                {hightemp01, hightemp02, hightemp03, hightemp04};
        lowtemps = new Label[]
                {lowtemp01, lowtemp02, lowtemp03, lowtemp04};
        //do stuff with the forecast
        this.forecast = forecast;

        var datetime = LocalDateTime.now();

        cityname.setText(forecast.getName());
        localtime.setText("Last Updated:" + datetime.getHour() + ":" + datetime.getMinute());

        if (forecast != null) {
            var url = forecast.getCurrent().getIconURL();
            var temp = this.getClass().getResource("WeatherIcons" + url);
            currentweatherlogo.setImage(new Image(temp.toString()));
        }

        var days = forecast.getForecast().getDays();
        var todayHours = days.get(0).getHours();

        var hour = LocalDateTime.now().getHour();


        for (int i = 0; i < 10; i++) {
            var hourObject = todayHours.get(hour);
            var url = todayHours.get(hour).getIconURL();
            var temp = this.getClass().getResource("WeatherIcons" + url);
            conditionImages[i].setImage(new Image(temp.toString()));
            hour++;
            temps[i].setText(hourObject.getTemp_c() + "°C");
            times[i].setText(hourObject.getTime());

            if (hour == 24) {
                todayHours = days.get(1).getHours();
                hour = 0;
            }

        }
        //set weekday, set lowtemp, set hightemp.

        var weekday = Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
        for (int i = 0; i < 3; i++) {
            hightemps[i].setText(days.get(i).getAverage().getMaxtemp_c());
            lowtemps[i].setText(days.get(i).getAverage().getMintemp_c());
            if (i != 0) {
                weekdays[i].setText(getWeekday(weekday));

            }
            weekday++;
            if (weekday == 8) {
                weekday = 1;
            }
        }

    }

    private String getWeekday ( int num){
            String ret = "";
            switch (num) {
                case 1: {
                    ret = "Sunday";
                    break;
                }
                case 2: {
                    ret = "Monday";
                    break;
                }
                case 3: {
                    ret = "Tuesday";
                    break;
                }
                case 4: {
                    ret = "Wednesday";
                    break;
                }
                case 5: {
                    ret = "Thursday";
                    break;
                }
                case 6: {
                    ret = "Friday";
                    break;
                }
                case 7: {
                    ret = "Saturday";
                    break;
                }
            }
            return ret;
        }


        @FXML
        private void refreshWeather()
        {
            var apiResult = Querying.getWeatherInformation(Querying.QueryType.FORECAST_WEEK);
            var forecast = DataConversion.interpretData(apiResult);

            update(forecast);
        }

        @Override
        public void closing(){};
}