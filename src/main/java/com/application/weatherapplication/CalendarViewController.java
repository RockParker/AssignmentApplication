package com.application.weatherapplication;

import com.application.dataobjects.Forecast;
import com.application.dataobjects.IDataProvider;
import com.application.dataobjects.TaskObject;
import javafx.animation.KeyValue;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;


import java.util.ArrayList;
import java.util.Calendar;


public class CalendarViewController implements IDataProvider
{

    private final int daysAcross = 7, weeksDown = 6;
    private Label currentLabel, previousLabel;
    private TaskObject currentTO, previousTO;
    private int currentDay,currentMonth, numberOfTasks = 0;
    private Calendar cal;
    private String date;
    private Button selectedDay;
    private ArrayList<TaskObject> tasks;


    @FXML
    private VBox vCalendar, Tasks;
    @FXML
    private Button previousMonth, nextMonth, monthShowing;
    @FXML
    private TextField lblTitle, lblDate;
    @FXML
    private TextArea lblDesc;

    private Tooltip ttp = new Tooltip("Use Enter to Save All the Data");


    public void initialize()
    {
        cal = Calendar.getInstance();

        currentDay = cal.get(Calendar.DAY_OF_MONTH);
        currentMonth = cal.get(Calendar.MONTH);

        tasks = FileHandler.ReadFromJson();
        if(tasks == null)
        {
            tasks = new ArrayList<>();
            System.out.println("null list");
        }

        generateCalendar();

        loadTasks(selectedDay.getId());


        lblTitle.setTooltip(ttp);
        lblDate.setTooltip(ttp);
        lblDesc.setTooltip(ttp);

    }

    @Override
    public void closing()
    {
        //here I want to save the tasks from the calendar into a file that can be loaded later.
        if(currentTO!= null)
        {
            currentTO.setTitle(lblTitle.getText());
            currentTO.setDate(lblDate.getText());
            currentTO.setDescription(lblDesc.getText());
        }
        //now we want to store the list into a file

        FileHandler.SaveToJson(tasks);
    }

    @FXML
    public void textUpdated (KeyEvent e)
    {
        if(e.getCode() == KeyCode.ENTER)
        {
            currentTO.setTitle(lblTitle.getText());
            currentTO.setDate(lblDate.getText());
            currentTO.setDescription(lblDesc.getText());

            currentLabel.setText(currentTO.getTitle());

        }
    }

    private void calendarButtonClick(Button sender)
    {
        if(selectedDay != null)
        {
            selectedDay.getStyleClass().remove("selected-calendar");
        }

        selectedDay = sender;
        selectedDay.getStyleClass().add("selected-calendar");

        loadTasks(selectedDay.getId());

        //do stuff here
        //this will display the information for the tasks on that day?
        // or maybe will just scroll the window to that task. and the window will just always show a certain number of tasks?
    }

    public void loadAllTasks()
    {
        Tasks.getChildren().clear();
        for(TaskObject obj : tasks)
        {
            obj.setLabel(createLabel(obj.getTitle(), obj));
            Tasks.getChildren().add(obj.getLabel());
        }
    }

    public void loadTasks(String buttonId)
    {
        Tasks.getChildren().clear();

        for(TaskObject obj : tasks)
        {
            var id = obj.getButtonID();

            if(id.equals(buttonId))
            {
                obj.setLabel(createLabel(obj.getTitle(), obj));
                Tasks.getChildren().add(obj.getLabel());
            }
        }
    }

    @FXML
    protected void newTaskClick()
    {
        TaskObject to = new TaskObject();
        to.setButtonID(selectedDay.getId());
        to.setDate(selectedDay.getId());

        var lbl = createLabel("New Task", to);


        to.setTitle(lbl.getText());
        to.setDate("");
        to.setButtonID(selectedDay.getId());
        to.setDescription("");
        to.setLabel(lbl);
        tasks.add(to);

        Tasks.getChildren().add(lbl);
    }

    private Label createLabel(String title, TaskObject to)
    {
        var lbl = new Label(title);
        lbl.setTextFill(Paint.valueOf("white"));
        lbl.setAlignment(Pos.CENTER);
        lbl.setOnMouseClicked(mouseEvent ->
        {
            numberOfTasks++;
            if(currentLabel!=null)
            {
                currentLabel.getStyleClass().remove("selected");
                previousLabel = currentLabel;
                previousTO = currentTO;
            }

            currentTO = to;
            currentLabel = (Label) mouseEvent.getSource();
            currentLabel.getStyleClass().add("selected");

            loadTaskInfo();
        });

        return lbl;
    }
    private void loadTaskInfo()
    {
        if(previousLabel != null)
        {
            previousTO.setTitle(lblTitle.getText());
            previousTO.setDate(lblDate.getText());
            previousTO.setDescription(lblDesc.getText());

            previousLabel.setText(previousTO.getTitle());
            if(previousLabel.getText() == null)
            {
                previousLabel.setText("No Title Given");
            }

        }

        lblTitle.setText(currentTO.getTitle());
        lblDate.setText(currentTO.getDate());
        lblDesc.setText(currentTO.getDescription());
    }

    @FXML
    protected void deleteTaskClick()
    {
        if(numberOfTasks  <= 0)
        {
            return;
        }

        if(currentLabel != null)
        {
            Tasks.getChildren().remove(currentLabel);
            currentLabel = null;
            numberOfTasks--;

            tasks.remove(currentTO);
            currentTO = null;


            previousLabel = null;
            previousTO = null;
        }
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

    private void generateCalendar()
    {
        vCalendar.getChildren().clear();

        monthShowing.setText(cal.get(Calendar.YEAR) +" / "+ (cal.get(Calendar.MONTH) + 1));

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
        var weekday = cal.get(Calendar.DAY_OF_WEEK);

        var offset = weekday-(currentDay % 7);

        if(offset < 1)
        {
            offset += 7;
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
            b.setOnAction(actionEvent -> calendarButtonClick(b));

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

            if(day == currentDay && cal.get(Calendar.MONTH) == currentMonth)
            {
                b.getStyleClass().add("today-button");
                b.getStyleClass().add("selected-calendar");
                selectedDay = b;
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

    @Override
    public void update(Forecast forecast){}
}
