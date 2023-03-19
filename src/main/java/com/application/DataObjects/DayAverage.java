package com.application.DataObjects;

public class DayAverage
{
    private String maxtemp_c;
    private String mintemp_c;
    private String avgtemp_c;
    private String maxwind_kph;
    private String totalprecip_mm;
    private String totalsnow_cm;
    private String avghumdity;
    private String condition;
    private String iconURL;

    public DayAverage ()
    {}

    private DayAverage(String maxtemp_c, String mintemp_c, String avgtemp_c, String maxwind_kph,
                            String totalprecip_mm, String totalsnow_cm, String avghumdity,
                            String condition, String iconURL)
    {

        this.maxtemp_c = maxtemp_c;
        this.mintemp_c = mintemp_c;
        this.avgtemp_c = avgtemp_c;
        this.maxwind_kph = maxwind_kph;
        this.totalprecip_mm = totalprecip_mm;
        this.totalsnow_cm = totalsnow_cm;
        this.avghumdity = avghumdity;
        this.condition = condition;
        this.iconURL = iconURL;
    }

    public String getMaxtemp_c() {
        return maxtemp_c;
    }

    public void setMaxtemp_c(String maxtemp_c) {
        this.maxtemp_c = maxtemp_c;
    }

    public String getMintemp_c() {
        return mintemp_c;
    }

    public void setMintemp_c(String mintemp_c) {
        this.mintemp_c = mintemp_c;
    }

    public String getAvgtemp_c() {
        return avgtemp_c;
    }

    public void setAvgtemp_c(String avgtemp_c) {
        this.avgtemp_c = avgtemp_c;
    }

    public String getMaxwind_kph() {
        return maxwind_kph;
    }

    public void setMaxwind_kph(String maxwind_kph) {
        this.maxwind_kph = maxwind_kph;
    }

    public String getTotalprecip_mm() {
        return totalprecip_mm;
    }

    public void setTotalprecip_mm(String totalprecip_mm) {
        this.totalprecip_mm = totalprecip_mm;
    }

    public String getTotalsnow_cm() {
        return totalsnow_cm;
    }

    public void setTotalsnow_cm(String totalsnow_cm) {
        this.totalsnow_cm = totalsnow_cm;
    }

    public String getAvghumdity() {
        return avghumdity;
    }

    public void setAvghumdity(String avghumdity) {
        this.avghumdity = avghumdity;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getIconURL() {
        return iconURL;
    }

    public void setIconURL(String iconURL) {
        this.iconURL = iconURL;
    }
}
