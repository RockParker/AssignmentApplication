package com.application.dataobjects;

public class HourForecast
{
    private String date, time, temp_c, is_day;
    private String condition, icon;
    private String wind_kph, precip_mm, humidity, feelslike_c;


    public HourForecast(String date, String time, String temp_c, String is_day,
                            String condition, String icon, String wind_kph, String precip_mm,
                                String humidity, String feelslike_c )
    {
        this.date = date;
        this.time = time;
        this.temp_c = temp_c;
        this.is_day = is_day;
        this.condition = condition;
        this.icon = icon;
        this.wind_kph = wind_kph;
        this.precip_mm = precip_mm;
        this.humidity = humidity;
        this.feelslike_c = feelslike_c;
    }
    public HourForecast(){}

    @Override
    public String toString()
    {
        String ret = "";

        ret += date+"\n";
        ret += time+"\n";
        ret += temp_c+"\n";
        ret += is_day+"\n";
        ret += condition+"\n";
        ret += icon+"\n";
        ret += wind_kph+"\n";
        ret += precip_mm+"\n";
        ret += humidity+"\n";
        ret += feelslike_c+"\n";

        return ret;
    }



    /*
        Here be the getters and setters.
        They are self-explanatory
     */

    public String getDate()
    {return date;}
    public void setDate(String date)
    {this.date = date;}

    public String getTime()
    {return time;}
    public void setTime(String time)
    {this.time = time;}
    public String getTemp_c()
    {return temp_c;}
    public void setTemp_c(String temp_c)
    {this.temp_c = temp_c;}

    public boolean getIsDay()
    {
        if(is_day.equals("1"))
        {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }
    public void setIsDay(String isDay)
    {
        this.is_day = isDay;
    }

    public String getCondition()
    {return condition;}
    public void setCondition(String condition)
    {this.condition = condition;}
    public String getIconURL()
    {return icon;}
    public void setIconURL(String iconURL)
    {this.icon = iconURL;}

    public String getWindKPH()
    {return wind_kph;}
    public void setWindKPH(String windKPH)
    {this.wind_kph = windKPH;}

    public String getPrecip_mm()
    {return precip_mm;}
    public void setPrecip_mm(String precip_mm)
    {this.precip_mm = precip_mm;}

    public String getHumidity()
    {return humidity;}
    public void setHumidity(String humidity)
    {this.humidity = humidity;}

    public String getFeelslike_c()
    {return feelslike_c;}
    public void setFeelslike_c(String feelslike_c)
    {this.feelslike_c = feelslike_c;}
}
