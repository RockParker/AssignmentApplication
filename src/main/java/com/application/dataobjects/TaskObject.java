package com.application.dataobjects;


import javafx.scene.control.Label;

public class TaskObject
{
    private String title, description, date, buttonID;
    private Label label;

    public TaskObject(String title, String description, String date, String buttonID)
    {
        this.title = title;
        this.description = description;
        this.date = date;
        this.buttonID = buttonID;
    }
    public TaskObject(){}


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getButtonID() {
        return buttonID;
    }

    public void setButtonID(String buttonID) {
        this.buttonID = buttonID;
    }

    public Label getLabel() {
        return label;
    }

    public void setLabel(Label label) {
        this.label = label;
    }
}
