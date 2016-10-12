package com.example.slope.androiddriver.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Slope on 2016/9/17.
 */
public class CarDataBase extends SQLiteOpenHelper {

    public static final String TABLE_COLLECT_NEWS="tb_collect_news";
    public static final String TITLE="title";
    public static final String URL="article_url";
    public static final String SQL_News="create table "+TABLE_COLLECT_NEWS+"( "
            +TITLE+" text not null, "
            +URL+" text not null)";

    public static final String TABLE_COLLECT_QUESTION="tb_collect_question";

    public static final String TABLE_MYERROR="tb_myerror";


    public static final String TABLE_NAME_1="tb_car_1";
    public static final String TABLE_NAME_4="tb_car_4";
    public static final String TABLE_RAND="tb_rand";

    public static final String ID="id";
    public static final String QUESTION="question";
    public static final String ANSWER="answer";
    public static final String ITEM_1="item1";
    public static final String ITEM_2="item2";
    public static final String ITEM_3="item3";
    public static final String ITEM_4="item4";
    public static final String EXPLAINS="explains";
    public static final String SUBJECT_URL="url";
    public static final String SQL_1="create table "+TABLE_NAME_1+"( "
            +ID+" integer not null,"
            +QUESTION+" text not null,"
            +ANSWER+" text not null,"
            +ITEM_1+" text not null,"
            +ITEM_2+" text not null,"
            +ITEM_3+" text not null,"
            +ITEM_4+" text not null,"
            +EXPLAINS+" text not null,"
            +SUBJECT_URL+" text not null)";
    public static final String SQL_4="create table "+TABLE_NAME_4+"( "
            +ID+" integer not null,"
            +QUESTION+" text not null,"
            +ANSWER+" text not null,"
            +ITEM_1+" text not null,"
            +ITEM_2+" text not null,"
            +ITEM_3+" text not null,"
            +ITEM_4+" text not null,"
            +EXPLAINS+" text not null,"
            +SUBJECT_URL+" text not null)";
    public static final String SQL_RAND="create table "+TABLE_RAND+"( "
            +ID+" integer not null,"
            +QUESTION+" text not null,"
            +ANSWER+" text not null,"
            +ITEM_1+" text not null,"
            +ITEM_2+" text not null,"
            +ITEM_3+" text not null,"
            +ITEM_4+" text not null,"
            +EXPLAINS+" text not null,"
            +SUBJECT_URL+" text not null)";

    public static final String SQL_ERROR="create table "+TABLE_MYERROR+"( "
            +ID+" integer not null,"
            +QUESTION+" text not null,"
            +ANSWER+" text not null,"
            +ITEM_1+" text not null,"
            +ITEM_2+" text not null,"
            +ITEM_3+" text not null,"
            +ITEM_4+" text not null,"
            +EXPLAINS+" text not null,"
            +SUBJECT_URL+" text not null)";
    public static final String SQL_QUESTION="create table "+TABLE_COLLECT_QUESTION+"( "
            +ID+" integer not null,"
            +QUESTION+" text not null,"
            +ANSWER+" text not null,"
            +ITEM_1+" text not null,"
            +ITEM_2+" text not null,"
            +ITEM_3+" text not null,"
            +ITEM_4+" text not null,"
            +EXPLAINS+" text not null,"
            +SUBJECT_URL+" text not null)";
    public CarDataBase(Context context) {
        super(context, "car.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_News);
        db.execSQL(SQL_QUESTION);
        db.execSQL(SQL_ERROR);
        db.execSQL(SQL_1);
        db.execSQL(SQL_4);
        db.execSQL(SQL_RAND);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
