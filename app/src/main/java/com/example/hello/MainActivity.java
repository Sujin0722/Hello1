package com.example.hello;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
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
    int vall;

    Toolbar myToolbar;
    TextView textView1;
    private Bundle savedInstanceState;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        this.savedInstanceState = savedInstanceState;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        helper = new DBHelper(this, "DB", null, 1);
        db = helper.getWritableDatabase();

        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        pagerAdapter = new Pager_Adapter(this);
        mViewPager.setAdapter(pagerAdapter);
        mViewPager.setCurrentItem(1); // 페이저어뎁터 초기 페이지 설정

    }

}