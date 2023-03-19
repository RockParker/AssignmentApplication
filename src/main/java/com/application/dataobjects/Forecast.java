package com.application.dataobjects;

public class Forecast
{
    //location fields
    private String name, country, date, time;

    private CurrentObject current;

    private MultiDayForecast forecast;

    public Forecast()
    {}

    public Forecast(String name, String country, String date, String time,
                        CurrentObject current, MultiDayForecast mdf)
    {
        this.name = name;
        this.country = country;
        this.date = date;
        this.time = time;
        this.current = current;
        this.forecast = mdf;

    }


    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }
    public void setCountry(String country) {
        this.country = country;
    }

    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }
    public void setTime(String time) {
        this.time = time;
    }

    public CurrentObject getCurrent() {
        return current;
    }
    public void setCurrent(CurrentObject current) {
        this.current = current;
    }

    public MultiDayForecast getForecast() {
        return forecast;
    }
    public void setForecast(MultiDayForecast forecast) {
        this.forecast = forecast;
    }

    @Override
    public String toString()
    {
        String ret = "";

        ret+="country: " + country + "\n";
        ret+="City: " + name + "\n";
        ret+="date: " + date + "\n";
        ret+="time: " + time + "\n";

        ret+="Current: " + current + "\n";

        ret+="Forecast: " + forecast + "\n";


        return ret;
    }
}
