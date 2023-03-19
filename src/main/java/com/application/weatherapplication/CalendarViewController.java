package com.application.weatherapplication;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


import java.util.ArrayList;
import java.util.Calendar;


public class CalendarViewController
{

    private final int daysAcross = 7;
    private final int weeksDown = 6;
    @FXML
    private VBox vCalendar;
    @FXML
    private VBox vCalendarHost;
    @FXML
    private VBox Tasks;

    @FXML
    private Button previousMonth, nextMonth, currentMonth;
    private int currentDay;

    private Calendar cal;

    public void initialize()
    {
        cal = Calendar.getInstance();

        currentDay = cal.get(Calendar.DAY_OF_WEEK_IN_MONTH);

        generateCalendar();
    }


    private void calendarButtonClick()
    {

        //do stuff here
        //this will display the information for the tasks on that day?
        // or maybe will just scroll the window to that task. and the window will just always show a certain number of tasks?
    }

    @FXML
    protected void getPreviousMonth()
    {
        cal.add(Calendar.MONTH, -1);

        generateCalendar();
    }

    @FXML
    protected void getNextMonth()
    {
        cal.add(Calendar.MONTH, 1);

        generateCalendar();
    }

    ///may be worth generating the whole year at once, and then just setting the
    ///children of the VBox to the 7 HBox starting from the 1st of the month.

    private void generateCalendar()
    {
        vCalendar.getChildren().clear();

        currentMonth.setText(cal.get(Calendar.YEAR) +" / "+ (cal.get(Calendar.MONTH) + 1));

        /*
          this part gets the previous month, and its number of days
         */

        cal.add(Calendar.MONTH, -1);
        var prevMonthDays = cal.getActualMaximum(Calendar.DAY_OF_MONTH);

        /*
          sets the month back to the current month, then gets the number of days in this month
         */

        cal.add(Calendar.MONTH, 1);
        var currentMonthDays = cal.getActualMaximum(Calendar.DAY_OF_MONTH);


        /*
          gets the number of days to represent from the previous month, before starting this month
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
        boolean pastDate = false;
        var buttons = new ArrayList<Button>();
        for(int i = 1; i <= (daysAcross*weeksDown)+1; i++)
        {
            //set all the "universal" attributes here
            //id should be YearMonthDay
            Button b = new Button();
            b.getStyleClass().add("calendar-button");
            b.setOnAction(actionEvent -> calendarButtonClick());

            if(pastDate)
            {
                b.getStyleClass().add("out-of-range-button");
            }
            buttons.add(b);
            //this is for the buttons that are in the previous month
            if(i <= offset)
            {
                b.setText(String.valueOf(prevMonthDays - (offset - i)));
                b.getStyleClass().add("out-of-range-button");
                if(calCurrentMonth == 0)
                {
                    //will be the previous year, the last month, and the current day
                    b.setId(String.valueOf(currentYear-1) + 11 + b.getText());
                }
                else
                {
                    b.setId(String.valueOf(currentYear) + (calCurrentMonth - 1) + b.getText());
                }
                continue;
            }


            b.setText(String.valueOf(day));
            b.setId(String.valueOf(currentYear) + calCurrentMonth + day);
            day++;


            if(day > currentMonthDays)
            {
                day = 1;
                pastDate= true;
                cal.add(Calendar.MONTH, 1);
            }
        }
        cal.add(Calendar.MONTH, -1);

        int counter = 0;
        HBox box = new HBox();
        box.getStyleClass().add("calendar-hbox");
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
