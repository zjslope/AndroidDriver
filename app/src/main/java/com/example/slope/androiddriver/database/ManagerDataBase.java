package com.example.slope.androiddriver.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.slope.androiddriver.basicclass.Car;

/**
 * Created by Slope on 2016/9/17.
 */
public class ManagerDataBase {
    SQLiteDatabase sqLiteDatabase;
    CarDataBase carDataBase;
    public ManagerDataBase(Context context){
        carDataBase=new CarDataBase(context);
        sqLiteDatabase=carDataBase.getWritableDatabase();
    }

    /**
     * 将题库插入到数据库
     * @param valies
     * @param name
     * @return
     */
    public long addAll(ContentValues valies, String name){
        return sqLiteDatabase.insert(name,null,valies);
    }
    public Cursor selectAll(String tableName){
        return sqLiteDatabase.query(tableName,null,null,null,null,null,null);

    }
    /**
     * 添加新闻
     * @param values
     * @return
     */
    public long addNew(ContentValues values){
        return sqLiteDatabase.insert(CarDataBase.TABLE_COLLECT_NEWS,null,values);
    }


    /**
     * 查询新闻
     * @return
     */
    public Cursor selectAllNews(){
        return sqLiteDatabase.query(CarDataBase.TABLE_COLLECT_NEWS,null,null,null,null,null,null);
    }

    /**
     * 查询新闻是否存在
     * @param selection
     * @param selectionArgs
     * @return
     */
    public Cursor selectNew(String selection,String[] selectionArgs){
        return sqLiteDatabase.query(CarDataBase.TABLE_COLLECT_NEWS,null,selection,selectionArgs,null,null,null);
    }
    /**
     * 删除新闻
     * @param whereUrl
     * @param whereArgs
     * @return
     */
    public int deleteNews(String whereUrl, String[] whereArgs){
        return sqLiteDatabase.delete(CarDataBase.TABLE_COLLECT_NEWS,whereUrl,whereArgs);
    }

    /**
     * 根据类别查询
     * @param subject
     * @param id
     * @return
     */
    public Cursor selectCarOne(int subject,int id) {
        if (subject==1){
            return sqLiteDatabase.query(CarDataBase.TABLE_NAME_1,null," id=? ",new String[]{String.valueOf(id)},
                    null,null,null,null);
        }
        else if (subject==4){
            return sqLiteDatabase.query(CarDataBase.TABLE_NAME_4,null," id=? ",new String[]{String.valueOf(id)},
                    null,null,null,null);
        }
        else  if (subject==3){
            return sqLiteDatabase.query(CarDataBase.TABLE_MYERROR,null," id=? ",new String[]{String.valueOf(id)},
                    null,null,null,null);
        }
        else {
            return sqLiteDatabase.query(CarDataBase.TABLE_RAND,null," id=? ",new String[]{String.valueOf(id)},
                    null,null,null,null);
        }
    }

    /**
     * 删除整张表
     * @param whereArgs
     */
    public void deleteCarOne(String whereArgs){
        sqLiteDatabase.execSQL(whereArgs);
    }

    /**
     * 删除某条单独的题
     * @param whereUrl
     * @param whereArgs
     * @return
     */
    public int delectErrorQuestion(String whereUrl, String[] whereArgs){
        return sqLiteDatabase.delete(CarDataBase.TABLE_MYERROR,whereUrl,whereArgs);
    }
    public int delectCollectQuestion(String whereUrl, String[] whereArgs){
        return sqLiteDatabase.delete(CarDataBase.TABLE_COLLECT_QUESTION,whereUrl,whereArgs);
    }
}
