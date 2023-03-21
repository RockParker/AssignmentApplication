package com.application.dataobjects;

import java.util.ArrayList;

/**
 * This class holds a list of days, each day holds its own hours
 */

public class MultiDayForecast
{
    private ArrayList<DayForecast> days;

    public MultiDayForecast()
    {
        days = new ArrayList<>();
    }

    public ArrayList<DayForecast> getDays()
    {
        return days;
    }
    public void setDays(ArrayList<DayForecast> days)
    {
        this.days = days;
    }

    public void addDay(DayForecast dayForecast)
    {
        days.add(dayForecast);
    }

    @Override
    public String toString()
    {
        String ret = "";

        for(DayForecast forecast : days)
        {
            ret += forecast.toString();
            ret+="\n";
        }

        return ret;
    }
}
