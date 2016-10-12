package com.example.slope.androiddriver.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.example.slope.androiddriver.basicclass.Car;
import com.example.slope.androiddriver.basicclass.News;

import java.util.List;

/**
 * Created by Slope on 2016/9/17.
 */
public class ServicesDataBase {
    ManagerDataBase managerDataBase;
    public ServicesDataBase(Context context) {
        this.managerDataBase = new ManagerDataBase(context);
    }
    /**
     * 要存的新闻的列表
     */
    public long addOne(News obj){
        ContentValues values=new ContentValues();
        long id=-1;
            values.put(CarDataBase.TITLE,obj.getTitle());
            values.put(CarDataBase.URL,obj.getArticle_url());
            id=managerDataBase.addNew(values);
            values.clear();
        return id;
    }
    public Cursor selectAll(String tableName){
        return managerDataBase.selectAll(tableName);

    }

    /**
     * 要查看的新闻列表
     */
    public Cursor selectAllNew(){
        return managerDataBase.selectAllNews();
    }
    /**
     * 查询新闻是否存在
     */
    public Cursor selectOneNew(News news){
        String selection=CarDataBase.URL+"=?";
        String[] selectionArgs=new String[]{news.getArticle_url()};
        return managerDataBase.selectNew(selection,selectionArgs);
    }
    /**
     * 从新闻列表删除某条新闻
     */
    public int delete(News news){
        String whereUrl = CarDataBase.URL + "=?";
        String[] whereArgs = new String[]{news.getArticle_url()};
        return managerDataBase.deleteNews(whereUrl, whereArgs);
    }

    /**
     * 要存的题列表
     * @param objList
     * @param name
     * @return
     */
    public long addAllSubject(List<Car>objList,String name){
        ContentValues values=new ContentValues();
        long id=-1;
        for(Car obj:objList){
            values.put(CarDataBase.ID,Integer.valueOf(obj.getId()));
            values.put(CarDataBase.QUESTION,obj.getQuestion());
            values.put(CarDataBase.ANSWER,obj.getAnswer());
            values.put(CarDataBase.ITEM_1,obj.getItem1());
            values.put(CarDataBase.ITEM_2,obj.getItem2());
            values.put(CarDataBase.ITEM_3,obj.getItem3());
            values.put(CarDataBase.ITEM_4,obj.getItem4());
            values.put(CarDataBase.EXPLAINS,obj.getExplains());
            values.put(CarDataBase.SUBJECT_URL,obj.getUrl());
            id=managerDataBase.addAll(values,name);
            values.clear();
        }
        return id;
    }

    public long addSubjectRand(List<Car>objList,String name){
        ContentValues values=new ContentValues();
        long id=-1;
        for(Car obj:objList){
            values.put(CarDataBase.ID,Integer.valueOf(obj.getId()));
            values.put(CarDataBase.QUESTION,obj.getQuestion());
            values.put(CarDataBase.ANSWER,obj.getAnswer());
            values.put(CarDataBase.ITEM_1,obj.getItem1());
            values.put(CarDataBase.ITEM_2,obj.getItem2());
            values.put(CarDataBase.ITEM_3,obj.getItem3());
            values.put(CarDataBase.ITEM_4,obj.getItem4());
            values.put(CarDataBase.EXPLAINS,obj.getExplains());
            values.put(CarDataBase.SUBJECT_URL,obj.getUrl());
            id=managerDataBase.addAll(values,name);
            values.clear();
        }
        return id;
    }
    /**
     * 查询题的列表
     * @param subject
     * @param id
     * @return
     */
    public Cursor selectCarOne(int subject,int id) {
        return managerDataBase.selectCarOne(subject,id);
    }

    /**
     * 删除整个表
     * @param tableName
     */
    public void deleteOne(String tableName){
        String string ="DELETE FROM "+tableName;
       managerDataBase.deleteCarOne(string);
    }
    public int deleteErrorQuestion(Car car){
        String where = CarDataBase.ID + "=? ";
        String[] whereArgs = new String[]{String.valueOf(car.getId())};
        return managerDataBase.delectErrorQuestion(where, whereArgs);
    }
    public int deleteCollectQuestion(Car car){
        String where = CarDataBase.ID + "=? ";
        String[] whereArgs = new String[]{String.valueOf(car.getId())};
        return managerDataBase.delectCollectQuestion(where, whereArgs);
    }
}
