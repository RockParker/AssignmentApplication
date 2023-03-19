package com.application.api;

import com.application.dataobjects.*;
import javafx.scene.control.Alert;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.util.ArrayList;

public class DataConversion
{
    /*
      All the queries can be interpreted in much the same way.
      They all will have: Location, Current, forecast{forecastday[hour{}]}
      this means that all the processing can be exactly the same for all of them!!
     */

    /**
     * this method acts as the organizer of actions.
     * it won't do much of the heavy lifting, but it makes sure each step
     * happens
     * @param queryString the result from querying the api
     */
    public static Forecast interpretData(String queryString)
    {
        var forecast = new Forecast();
        try
        {
            var parser = new JSONParser();
            JSONObject root = (JSONObject) parser.parse(queryString);

            /* Getting the different needed things*/

            //here is the location information that is needed
            var location = (JSONObject) root.get("location");
            var city = location.get("name").toString();
            var country = location.get("country").toString();

            //gets the current information
            var current = getCurrentInfo(root);

            //this contains a list of single day forecast
            var multiDayForecast = getForecastInfo(root);

            forecast.setDate(current.getDate());
            forecast.setTime(current.getTime());
            forecast.setName(city);
            forecast.setCountry(country);
            forecast.setCurrent(current);
            forecast.setForecast(multiDayForecast);


        }
        catch(Exception e)
        {
            var alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Error");
            alert.setContentText("Error parsing the return from the api");
            alert.showAndWait();
            e.printStackTrace();
        }

        return forecast;

    }


    private static CurrentObject getCurrentInfo(JSONObject root)
    {
        // from current, we want basically a full hour object
        var current = (JSONObject) root.get("current");

        return createCurrent(current);

    }

    /**
     * Takes the root object and fetches all the days that are forecasted in the response
     * @param root the base json object
     * @return returns a multidayforcast object
     */
    private static MultiDayForecast getForecastInfo(JSONObject root)
    {
        /*
        * The forecast object hosts an array of "forecast day" objects
        *
        * forecast day objects host a "day" object and an "hour" array
        *
        * a day object hosts averages for the day
        *
        * an hour object hosts an array of hours in the day
        *
        * each holding information outlined in the HourForecast class
        */

        var multiDay = new MultiDayForecast();

        var forecast =(JSONObject) root.get("forecast");
        var forecastDay = (JSONArray) forecast.get("forecastday"); // array of forecast day objects

        for(Object forecastDayObj : forecastDay)
        {
            var day = (JSONObject) forecastDayObj;
            multiDay.addDay(createDayForecast(day));
        }

        return multiDay;
    }


    /**
     * Takes the forecastday json object, and converts it into a single day forecast object
     * @param forecastDay takes the json object that represents the forecast day
     * @return returns the day forcast
     */
    private static DayForecast createDayForecast(JSONObject forecastDay)
    {
        //day is the whole forecast, it hosts the average for the day, and the hourly breakdown
        var day = new DayForecast();

        //setting the date and stuff
        var date = forecastDay.get("date").toString();
        var split = date.split("-");
        day.setDay(split[2]);
        day.setMonth(split[1]);

        //this is the day object, which holds the dayAvg information
        var dayObj = (JSONObject)forecastDay.get("day");
        var dayAvg = new DayAverage();

        //gets the bulk of the information
        dayAvg.setMaxtemp_c(dayObj.get("maxtemp_c").toString());
        dayAvg.setMintemp_c(dayObj.get("mintemp_c").toString());
        dayAvg.setAvgtemp_c(dayObj.get("avgtemp_c").toString());
        dayAvg.setMaxwind_kph(dayObj.get("maxwind_kph").toString());
        dayAvg.setTotalprecip_mm(dayObj.get("totalprecip_mm").toString());
        dayAvg.setTotalsnow_cm(dayObj.get("totalsnow_cm").toString());
        dayAvg.setAvghumdity(dayObj.get("avghumidity").toString());

        //gets the relevant condition information
        var conditionObj = (JSONObject) dayObj.get("condition");
        var condition = getCondition(conditionObj);
        dayAvg.setCondition(condition[0]);
        dayAvg.setIconURL(condition[1]);

        day.setAverage(dayAvg);

        //this gets the json array of hours, then parses them into the hour objects
        var hourArray = (JSONArray) forecastDay.get("hour");

        day.setHours(createDayArray(hourArray));


        return day;
    }


    /**
     * takes the json array of hours
     * @param hourArray takes the json objecy which represents the hours in a day
     * @return returns a list of hour data objects
     */
    private static ArrayList<HourForecast> createDayArray(JSONArray hourArray)
    {
        var hours = new ArrayList<HourForecast>();
        for(Object obj : hourArray)
        {
            var hourObj = (JSONObject) obj;

            //parses the json
            hours.add(createHour(hourObj));
        }
        return hours;
    }


    private static CurrentObject createCurrent(JSONObject currentObj)
    {
        var current = new CurrentObject();
        //the time key holds the date and the time. must split them
        //and put them into the hour object
        var dateTime = getDateTime(currentObj.get("last_updated").toString());
        var date = dateTime[0];
        var time = dateTime[1];
        current.setDate(date);
        current.setTime(time);

        genericHourForecast(current, currentObj);

        return current;
    }

    private static HourForecast createHour(JSONObject jsonHourObj)
    {
        var hour = new HourForecast();
        //the time key holds the date and the time. must split them
        //and put them into the hour object
        var dateTime = getDateTime(jsonHourObj.get("time").toString());
        var date = dateTime[0];
        var time = dateTime[1];
        hour.setDate(date);
        hour.setTime(time);

        genericHourForecast(hour, jsonHourObj);

        return hour;
    }

    private static void genericHourForecast(HourForecast forecast, JSONObject jsonHourObj)
    {

        forecast.setTemp_c(jsonHourObj.get("temp_c").toString());
        forecast.setIsDay(jsonHourObj.get("is_day").toString());

        //getting the condition. which has a text and url
        var condition = getCondition((JSONObject) jsonHourObj.get("condition"));
        forecast.setCondition(condition[0]);
        forecast.setIconURL(condition[1]);


        forecast.setWindKPH(jsonHourObj.get("wind_kph").toString());
        forecast.setPrecip_mm(jsonHourObj.get("precip_mm").toString());
        forecast.setHumidity(jsonHourObj.get("humidity").toString());
        forecast.setFeelslike_c(jsonHourObj.get("feelslike_c").toString());
    }


    private static String[] getDateTime(String dateTime)
    {
        //index 0 = Date, index 1 = Time;
        var values = new String[2];

        values = dateTime.split(" ");

        return values;
    }


    /**
     * takes the condition object from the json file, it converts it to two strings
     * @param condition takes the json object that represents the condition
     * @return returns {index 0: text (sunndy, cloudy, etc.) , index 1: iconURL}
     */
    private static String[] getCondition(JSONObject condition)
    {
        var ret = new String[2];

        ret[0] = condition.get("text").toString();
        ret[1] = getIconURL(condition.get("icon").toString());

        return ret;
    }

    private static String getIconURL(String url)
    {
        String ret = "";
        int counter = 0;

        //this algorithm may need tweaking to get the first '/'
        //that appears before the day or night
        for(int i = url.length()-1; i > 0; i--)
        {
            if(url.charAt(i) == '/')
                counter++;

            if(counter == 2)
            {
                ret = url.substring(i);
                break;
            }
        }
        return ret;
    }

}
