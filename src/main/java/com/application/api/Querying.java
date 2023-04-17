package com.application.api;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class Querying
{
    //this is bad practice, but it will work for now
    private static final String API_KEY = "685562943fa340ba9f1162637232001";

    private static final String baseUrl = "https://api.weatherapi.com/v1/forecast.json?key=";

    /**
     * Attempting to use auto:ip to make the api figure out where the device is
     */

    private static final String forecastQuery_24hr = baseUrl + API_KEY + "&q=auto:ip&days=1";
    private static final String forecastQuery_Week = baseUrl  + API_KEY + "&q=auto:ip&days=7";

    public enum QueryType
    {
        CURRENT, FORECAST_24HR, FORECAST_WEEK
    }

    public static String getWeatherInformation(QueryType qType)
    {

        String ret = "";
        String query;

        switch (qType)
        {
            case FORECAST_WEEK, CURRENT:
            {
                query = forecastQuery_Week;
                break;
            }

            default:
            {
                query = forecastQuery_24hr;
                break;
            }
        }

        ret = queryAPI(query);

        return ret;
    }

    public static String getWeatherInformation(QueryType qType, String postalCode)
    {
        //baseUrl  + API_KEY + "&q=auto:ip&days=7";

        String ret = "";
        String query;

        switch (qType)
        {
            case FORECAST_WEEK, CURRENT:
            {
                query = baseUrl  + API_KEY + "&q="+postalCode+"&days=7";
                break;
            }

            default:
            {
                query = baseUrl  + API_KEY + "&q="+postalCode+"&days=1";
                break;
            }
        }

        ret = queryAPI(query);

        return ret;
    }


    private static String queryAPI (String query)
    {
        String ret = "";

        try
        {
            URL url = new URL(query);
            URLConnection port = url.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(port.getInputStream()));
            ret = in.readLine();

            in.close();
        }

        catch(Exception e)
        {
            e.printStackTrace();
        }

        return ret;
    }

}
