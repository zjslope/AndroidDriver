package com.example.slope.androiddriver.json;

import com.example.slope.androiddriver.basicclass.Car;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Slope on 2016/9/19.
 */
public class CarJson {
    public static List<Car> superJson(JSONArray array){
        List<Car>list=new ArrayList<>();
        for (int i = 0; i <array.length() ; i++) {
            try {

                JSONObject array_obj=array.getJSONObject(i);
                int id=array_obj.getInt("id");
                String question=array_obj.getString("question");
                String answer=array_obj.getString("answer");
                String item1=array_obj.getString("item1");
                String item2=array_obj.getString("item2");
                String item3=array_obj.getString("item3");
                String item4=array_obj.getString("item4");
                String explains=array_obj.getString("explains");
                String url=array_obj.getString("url");
                list.add(new Car(id,question,answer,item1,item2,item3,item4,explains,url));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return list;
    }
    public static List<Car> randJson(JSONArray array){
        List<Car>list=new ArrayList<>();
        for (int i = 0; i <array.length() ; i++) {
            try {
                JSONObject array_obj=array.getJSONObject(i);
                int id=i;
                String question=array_obj.getString("question");
                String answer=array_obj.getString("answer");
                String item1=array_obj.getString("item1");
                String item2=array_obj.getString("item2");
                String item3=array_obj.getString("item3");
                String item4=array_obj.getString("item4");
                String explains=array_obj.getString("explains");
                String url=array_obj.getString("url");
                list.add(new Car(id,question,answer,item1,item2,item3,item4,explains,url));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

}
