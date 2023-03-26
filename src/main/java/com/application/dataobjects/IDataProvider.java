package com.application.dataobjects;

import com.application.weatherapplication.WeatherApplication;

/**
 * this forces any implementors to have an update method, which is used to update the UI from outside the
 * implementing class
 */
public interface IDataProvider
{

    public void update(Forecast forecast);
    public void closing();
}
