package com.example.hello;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;



public class MainActivity extends AppCompatActivity {

    private ViewPager mViewPager ;
    private Pager_Adapter pagerAdapter;

    private SQLiteDatabase db;
    String dbName = "ingredient_table.db";
    DBHelper helper;
    int dbVersion = 1;
    String tag = "SQLite";
    int coin;

    Toolbar myToolbar;
    TextView textView1;
    private Bundle savedInstanceState;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        this.savedInstanceState = savedInstanceState;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        pagerAdapter = new Pager_Adapter(this);
        mViewPager.setAdapter(pagerAdapter);
        mViewPager.setCurrentItem(1); // 페이저어뎁터 초기 페이지 설정

        helper = new DBHelper(this, dbName, null, dbVersion); // cointable 관련
        try {
            db = helper.getWritableDatabase();
        } catch (SQLiteException e) {
            e.printStackTrace();
            Log.e(tag, "데이터베이스를 얻어올 수 없음");
            finish();
        }
    }


}