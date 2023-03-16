package com.application.weatherapplication;

import javafx.fxml.FXML;
import javafx.scene.Camera;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


import java.util.ArrayList;
import java.util.Calendar;


public class CalendarViewController
{

    private int daysAcross = 7, weeksDown = 6;
    @FXML
    private VBox vCalendar;

    @FXML
    private Button previousMonth, nextMonth, currentMonth;
    private int currentDay;

    private Calendar cal;

    public void initialize()
    {
        cal = Calendar.getInstance();
        generateCalendar();
    }

    @FXML
    protected void getPreviousMonth()
    {
        if(cal.get(Calendar.MONTH) == 0)
        {
            cal.roll(Calendar.YEAR, false);
            cal.set(Calendar.MONTH, 11);
        }
        else
        {
            cal.roll(Calendar.MONTH, false);
        }

        generateCalendar();
    }

    @FXML
    protected void getNextMonth()
    {
        if(cal.get(Calendar.MONTH) == 11)
        {
            cal.roll(Calendar.YEAR, true);
            cal.set(Calendar.MONTH, 0);
        }
        else
        {
            cal.roll(Calendar.MONTH, true);
        }

        generateCalendar();
    }

    ///may be worth generating the whole year at once, and then just setting the
    ///children of the VBox to the 7 HBox starting from the 1st of the month..

    private void generateCalendar()
    {
        vCalendar.getChildren().clear();

        currentMonth.setText(String.valueOf(cal.get(Calendar.MONTH)));

        /**
         * this part gets the previous month, and its number of days
         */

        //cal.add(Calendar.MONTH, -1);
        cal.roll(Calendar.MONTH, false);
        var prevMonthDays = cal.getActualMaximum(Calendar.DAY_OF_MONTH);

        /**
         * sets the month back to the current month, then gets the number of days in this month
         */

        //cal.add(Calendar.MONTH, 1);
        cal.roll(Calendar.MONTH, true);
        var currentMonthDays = cal.getActualMaximum(Calendar.DAY_OF_MONTH);


        /**
         * gets the number of days to represent from the previous month, before starting this month
         */
        var currentDay = cal.get(Calendar.DAY_OF_MONTH);
        var weekday = cal.get(Calendar.DAY_OF_WEEK);

        var offset = weekday-(currentDay % 7);

        if(offset == -1)
        {
            offset = 6;
        }

        var currentYear = cal.get(Calendar.YEAR);
        var calCurrentMonth = cal.get(Calendar.MONTH);

        int day = 1;
        ArrayList<Button> buttons = new ArrayList<Button>();
        for(int i = 1; i <= (7*6)+1; i++)
        {
            //set all the "universal" attributes here
            //id should be YearMonthDay
            Button b = new Button();
            b.getStyleClass().add("calendar-button");
            buttons.add(b);
            //this is for the buttons that are in the previous month
            if(i <= offset)
            {
                b.setText(String.valueOf(prevMonthDays - (offset - i)));

                if(calCurrentMonth == 0)
                {
                    //will be the previous year, the last month, and the current day
                    b.setId(String.valueOf(currentYear-1) + String.valueOf(11) + b.getText());
                }
                else
                {
                    b.setId(String.valueOf(currentYear) + String.valueOf(calCurrentMonth-1) + b.getText());
                }
                continue;
            }


            b.setText(String.valueOf(day));
            b.setId(String.valueOf(currentYear) + String.valueOf(calCurrentMonth) + String.valueOf(day));
            day++;


            if(day > currentMonthDays)
            {
                day = 1;
                cal.add(Calendar.MONTH, 1);
            }
        }
        cal.roll(Calendar.MONTH, false);

        int counter = 0;
        HBox box = new HBox();
        for ( Button b : buttons)
        {
            if(counter == 0)
            {
                vCalendar.getChildren().add(box);
                box = new HBox();
            }

            box.getChildren().add(b);
            counter++;
            if(counter == 7)
            {
                counter = 0;
            }

        }
    }

}
