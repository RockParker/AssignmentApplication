package com.application.weatherapplication;


import com.application.dataobjects.TaskObject;
import javafx.concurrent.Task;
import javafx.scene.control.Label;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.*;
import java.util.ArrayList;

public class FileHandler
{

    public static void SaveToJson(ArrayList<TaskObject> list)
    {
        try {
            File file = new File("tasks.json");
            FileWriter writer = new FileWriter(file);
            JSONArray array = new JSONArray();
            for (TaskObject o : list) {
                JSONObject obj = new JSONObject();
                obj.put("Title", o.getTitle());
                obj.put("Date", o.getDate());
                obj.put("id", o.getButtonID());
                obj.put("Description", o.getDescription());
                array.add(obj);

            }

            if(!file.exists())
            {
                file.createNewFile();
                System.out.println(file.getPath());
            }

            writer.write(array.toJSONString());

            writer.close();
        }
        catch (Exception e)
        {
            System.out.println("Error Writing File");
            e.printStackTrace();
        }
    }

    public static ArrayList<TaskObject> ReadFromJson()
    {
        ArrayList<TaskObject> list = new ArrayList<>();

        try
        {
            FileReader reader = new FileReader(new File("tasks.json"));
            JSONParser parser = new JSONParser();
            var jsonList = (JSONArray) parser.parse(reader);

            for(Object o : jsonList)
            {
                var temp = (JSONObject) o;
                if(temp == null)
                {
                    System.out.println("Null object");
                    break;
                }
                var title = temp.get("Title").toString();
                var date = temp.get("Date").toString();
                var id = temp.get("id").toString();
                var desc = temp.get("Description").toString();

                list.add(new TaskObject(title, desc, date, id));

            }

        }
        catch (Exception e){System.out.println("Error reading file"); e.printStackTrace();}
        return list;
    }
}
