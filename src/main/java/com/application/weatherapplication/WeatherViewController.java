package com.application.weatherapplication;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;

public class WeatherViewController
{

    @FXML
    private Label LocationLabel;

    @FXML
    ImageView MainIcon;


    /**
     * this will hold the information of the weather data.
     * We can use this to fill our view with information
     */
    private Object dataObject = WeatherApplication.getDataObject();

    @FXML
    public void initialize()
    {
        SetIcon("blah", MainIcon);
        MainIcon.setFitHeight(350);
        MainIcon.setFitWidth(350);
        LocationLabel.setText("Changed The Icon in the controller class");
    }

    private void SetIcon(String condition, ImageView view)
    {
        File newFile = new File("Images/default_icon-2.jpg");
        //var imageUrl = WeatherViewController.class.getResource("Images/default_icon.png").toString();
        Image image = new Image(newFile.toURI().toString(), true);
        //System.out.println(imageUrl);



        //here, based on the condition, we
        //will set the desired image to be displayed in the image view
        switch (condition)
        {
            case "":
            {
                break;
            }
        }

        view.setImage(image);
    }
}
