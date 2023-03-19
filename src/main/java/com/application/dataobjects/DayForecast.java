package com.application.dataobjects;

import java.util.ArrayList;

public class DayForecast
{
    private ArrayList<HourForecast> hours;

    private DayAverage average;
    private String month, day;

    public DayForecast()
    {

    }

    /*
    * Here be the getters and setters
    */

    public String getMonth()
    {return month;}
    public void setMonth(String month)
    {this.month = month;}

    public String getDay()
    {return day;}
    public void setDay(String day)
    {this.day = day;}

    public ArrayList<HourForecast> getHours()
    {return hours;}
    public void setHours(ArrayList<HourForecast> hours)
    {this.hours = hours;}

    public DayAverage getAverage() {
        return average;
    }
    public void setAverage(DayAverage average) {
        this.average = average;
    }


    @Override
    public String toString()
    {
        String ret = "";
        ret+="Month: " + month + "\n";
        ret+="Day: " + day + "\n";
        ret+= average.toString() +"\n";
        ret+="hours: \n";

        for(HourForecast hour: hours)
        {
            ret += hour.toString() + "\n";
        }

        return ret;
    }
}
