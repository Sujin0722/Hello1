package com.example.hello;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import static android.content.ContentValues.TAG;

public class DBHelper extends SQLiteOpenHelper {

    static final String TABLE_NAME = "Ingredient";
    static final String TABLE_NAME1 = "Menu";


    public DBHelper(Context context, String name, CursorFactory factory, int version) {
        super(context, name, factory, version);
        Log.d(TAG, "DBHelper 생성자 호출");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d(TAG, "Table Create");
        String createQuery = "CREATE TABLE " + TABLE_NAME +
                "( ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "Number TEXT, " +
                "Value INTEGER );";
        db.execSQL(createQuery);

    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d(TAG, "Table onUpgrade");
        // 테이블 재정의하기 위해 현재의 테이블 삭제
        String createQuery = "DROP TABLE IF EXISTS " + TABLE_NAME + ";";
        db.execSQL(createQuery);

    }

}