package com.example.hello;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import static android.content.ContentValues.TAG;

public class Choice_New_Activity extends AppCompatActivity {

    public static Activity activity;

    private SQLiteDatabase db;
    DBHelper helper;
    String dbName = "ingredient_table.db";
    int dbVersion = 1;
    String tag = "SQLite";
    int val=0;

    private Intent intent;
    String number;
    Button button;
    int[] flag = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
    CheckBox[] mBtnArray = new CheckBox[30];
    ArrayList<String> intent_bag = new ArrayList<String>();


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice);

        activity = Choice_New_Activity.this;

        Toolbar tb1 = (Toolbar) findViewById(R.id.app_toolbar1);
        setSupportActionBar(tb1);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        helper = new DBHelper(this, "DB", null, 1);
        db = helper.getWritableDatabase();


        button = findViewById(R.id.button31);
        button.setText("레시피 추천받기");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                for (int i=0; i<flag.length;i++){
                    if (flag[i]==1){
                        select(i+1);
                        intent_bag.add(number);
                    }
                }

                if (intent_bag.size() == 0){
                    Toast.makeText(v.getContext(), "재료를 선택해주세요", Toast.LENGTH_SHORT).show();
                }
                else {
                    Intent intent = new Intent(v.getContext(), List_New.class);
                    intent.putExtra("Code", 0);
                    intent.putExtra("list", intent_bag);
                    v.getContext().startActivity(intent);
                    finish();
                }
            }
        });

        buttonControl();
    }



    void select(int aa){
        String sql;
        switch (aa){
            case 1:
                sql = "select * from Ingredient where Number = '파'";
                break;
            case 2:
                sql = "select * from Ingredient where Number = '양파'";
                break;
            case 3:
                sql = "select * from Ingredient where Number = '당근'";
                break;
            case 4:
                sql = "select * from Ingredient where Number = '감자'";
                break;
            case 5:
                sql = "select * from Ingredient where Number = '마늘'";
                break;
            case 6:
                sql = "select * from Ingredient where Number = '양배추'";
                break;
            case 7:
                sql = "select * from Ingredient where Number = '토마토'";
                break;
            case 8:
                sql = "select * from Ingredient where Number = '시금치'";
                break;
            case 9:
                sql = "select * from Ingredient where Number = '고추'";
                break;
            case 10:
                sql = "select * from Ingredient where Number = '브로콜리'";
                break;
            case 11:
                sql = "select * from Ingredient where Number = '콩나물'";
                break;
            case 12:
                sql = "select * from Ingredient where Number = '버섯'";
                break;
            case 13:
                sql = "select * from Ingredient where Number = '오이'";
                break;
            case 14:
                sql = "select * from Ingredient where Number = '파프리카'";
                break;
            case 15:
                sql = "select * from Ingredient where Number = '숙주'";
                break;
            case 16:
                sql = "select * from Ingredient where Number = '가지'";
                break;
            case 17:
                sql = "select * from Ingredient where Number = '돼지고기'";
                break;
            case 18:
                sql = "select * from Ingredient where Number = '닭고기'";
                break;
            case 19:
                sql = "select * from Ingredient where Number = '베이컨'";
                break;
            case 20:
                sql = "select * from Ingredient where Number = '계란'";
                break;
            case 21:
                sql = "select * from Ingredient where Number = '오징어'";
                break;
            case 22:
                sql = "select * from Ingredient where Number = '바지락'";
                break;
            case 23:
                sql = "select * from Ingredient where Number = '홍합'";
                break;
            case 24:
                sql = "select * from Ingredient where Number = '새우'";
                break;
            case 25:
                sql = "select * from Ingredient where Number = '두부'";
                break;
            case 26:
                sql = "select * from Ingredient where Number = '어묵'";
                break;
            case 27:
                sql = "select * from Ingredient where Number = '참치통조림'";
                break;
            case 28:
                sql = "select * from Ingredient where Number = '떡'";
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + aa);
        }
        Cursor cursor = db.rawQuery(sql, null);
        while (cursor.moveToNext()){
            String id = cursor.getString(0);
            number = cursor.getString(1);
            val = Integer.parseInt(cursor.getString(2));
            Log.d(TAG,  "select: " + id + number + val);
        }
    }

    void buttonColor(int dd){
        if(flag[dd]==0){
            mBtnArray[dd].setChecked(true);
            //mBtnArray[dd].setButtonDrawable(R.drawable.selector3);
            flag[dd] = 1;
        }
        else {
            mBtnArray[dd].setChecked(false);
            //mBtnArray[dd].setButtonDrawable(R.drawable.selector3);
            flag[dd] = 0;
        }
    }

    public void buttonControl(){
        mBtnArray[0] = (CheckBox) findViewById(R.id.button3);
        mBtnArray[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonColor(0);
            }
        });
        mBtnArray[1] = (CheckBox) findViewById(R.id.button4);
        mBtnArray[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonColor(1);
            }
        });
        mBtnArray[2] = (CheckBox) findViewById(R.id.button5);
        mBtnArray[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonColor(2);
            }
        });
        mBtnArray[3] = (CheckBox) findViewById(R.id.button6);
        mBtnArray[3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonColor(3);
            }
        });
        mBtnArray[4] = (CheckBox) findViewById(R.id.button7);
        mBtnArray[4].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonColor(4);
            }
        });
        mBtnArray[5] = (CheckBox) findViewById(R.id.button8);
        mBtnArray[5].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonColor(5);
            }
        });
        mBtnArray[6] = (CheckBox) findViewById(R.id.button9);
        mBtnArray[6].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonColor(6);
            }
        });
        mBtnArray[7] = (CheckBox) findViewById(R.id.button10);
        mBtnArray[7].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonColor(7);
            }
        });
        mBtnArray[8] = (CheckBox) findViewById(R.id.button11);
        mBtnArray[8].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonColor(8);
            }
        });
        mBtnArray[9] = (CheckBox) findViewById(R.id.button12);
        mBtnArray[9].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonColor(9);
            }
        });
        mBtnArray[10] = (CheckBox) findViewById(R.id.button13);
        mBtnArray[10].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonColor(10);
            }
        });
        mBtnArray[11] = (CheckBox) findViewById(R.id.button14);
        mBtnArray[11].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonColor(11);
            }
        });
        mBtnArray[12] = (CheckBox) findViewById(R.id.button15);
        mBtnArray[12].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonColor(12);
            }
        });
        mBtnArray[13] = (CheckBox) findViewById(R.id.button16);
        mBtnArray[13].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonColor(13);
            }
        });
        mBtnArray[14] = (CheckBox) findViewById(R.id.button17);
        mBtnArray[14].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonColor(14);
            }
        });
        mBtnArray[15] = (CheckBox) findViewById(R.id.button18);
        mBtnArray[15].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonColor(15);
            }
        });
        mBtnArray[16] = (CheckBox) findViewById(R.id.button19);
        mBtnArray[16].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonColor(16);
            }
        });
        mBtnArray[17] = (CheckBox) findViewById(R.id.button20);
        mBtnArray[17].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonColor(17);
            }
        });
        mBtnArray[18] = (CheckBox) findViewById(R.id.button21);
        mBtnArray[18].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonColor(18);
            }
        });
        mBtnArray[19] = (CheckBox) findViewById(R.id.button22);
        mBtnArray[19].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonColor(19);
            }
        });
        mBtnArray[20] = (CheckBox) findViewById(R.id.button23);
        mBtnArray[20].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonColor(20);
            }
        });
        mBtnArray[21] = (CheckBox) findViewById(R.id.button24);
        mBtnArray[21].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonColor(21);
            }
        });
        mBtnArray[22] = (CheckBox) findViewById(R.id.button25);
        mBtnArray[22].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonColor(22);
            }
        });
        mBtnArray[23] = (CheckBox) findViewById(R.id.button26);
        mBtnArray[23].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonColor(23);
            }
        });
        mBtnArray[24] = (CheckBox) findViewById(R.id.button27);
        mBtnArray[24].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonColor(24);
            }
        });
        mBtnArray[25] = (CheckBox) findViewById(R.id.button28);
        mBtnArray[25].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonColor(25);
            }
        });
        mBtnArray[26] = (CheckBox) findViewById(R.id.button29);
        mBtnArray[26].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonColor(26);
            }
        });
        mBtnArray[27] = (CheckBox) findViewById(R.id.button30);
        mBtnArray[27].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonColor(27);
            }
        });
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: {
                finish();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

}
