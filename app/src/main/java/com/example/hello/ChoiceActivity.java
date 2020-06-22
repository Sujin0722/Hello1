package com.example.hello;

import android.app.Activity;
import android.content.ContentValues;
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


public class ChoiceActivity<childUpdates> extends AppCompatActivity {

    public static Activity activity;

    private Intent intent;
    int vall;
    String value;
    String number;
    Button button;
    Button button32;
    CheckBox[] mBtnArray = new CheckBox[30];
    ArrayList<String> shopping_bag = new ArrayList<String>();
    ArrayList<String> intent_bag = new ArrayList<String>();
    ArrayList<String> nameeee = new ArrayList<String>();

    private SQLiteDatabase db;
    DBHelper helper;
    String dbName = "ingredient_table.db";
    int dbVersion = 1;
    String tag = "SQLite";
    int val=0;
    int colorvalue;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice);

        Toolbar tb = (Toolbar) findViewById(R.id.app_toolbar1);
        setSupportActionBar(tb);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        button = findViewById(R.id.button31);
        button.setText("장바구니로 이동!");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), Shopping_list.class);
                v.getContext().startActivity(intent);
                finish();

            }
        });

        button32 = findViewById(R.id.button32);
        button32.setVisibility(View.VISIBLE);
        button32.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                dbSelect1();

                if(nameeee.size() != 0) {
                    Intent intent = new Intent(v.getContext(), List_New.class);
                    intent.putExtra("Code", 11);
                    intent.putExtra("list", nameeee);
                    v.getContext().startActivity(intent);
                    finish();
                }
                else{
                    Toast.makeText(v.getContext(), "재료를 선택해주세요.", Toast.LENGTH_SHORT).show();
                }
            }
        });


        helper = new DBHelper(this, "DB", null, 1);
        db = helper.getWritableDatabase();


        //helper.onCreate(db);

        //dbInsert("Ingredient", "파", 0);
        //dbInsert("Ingredient", "양파", 0);
        //dbInsert("Ingredient", "당근", 0);
        //dbInsert("Ingredient", "감자", 0);
        //dbInsert("Ingredient", "마늘", 0);
        //dbInsert("Ingredient", "양배추", 0);
        //dbInsert("Ingredient", "토마토", 0);
        //dbInsert("Ingredient", "시금치", 0);
        //dbInsert("Ingredient", "고추", 0);
        //dbInsert("Ingredient", "브로콜리", 0);
        //dbInsert("Ingredient", "콩나물", 0);
        //dbInsert("Ingredient", "버섯", 0);
        //dbInsert("Ingredient", "오이", 0);
        //dbInsert("Ingredient", "파프리카", 0);
        //dbInsert("Ingredient", "숙주", 0);
        //dbInsert("Ingredient", "가지", 0);
        //dbInsert("Ingredient", "돼지고기", 0);
        //dbInsert("Ingredient", "닭고기", 0);
        //dbInsert("Ingredient", "베이컨", 0);
        //dbInsert("Ingredient", "계란", 0);
        //dbInsert("Ingredient", "오징어", 0);
        //dbInsert("Ingredient", "바지락", 0);
        //dbInsert("Ingredient", "홍합", 0);
        //dbInsert("Ingredient", "새우", 0);
        //dbInsert("Ingredient", "두부", 0);
        //dbInsert("Ingredient", "어묵", 0);
        //dbInsert("Ingredient", "참치통조림", 0);
        //dbInsert("Ingredient", "떡", 0);

        //dbInsert("Menu", "돼지고기 김치찌개", 0);
        //dbInsert("Menu", "된장찌개", 0);
        //dbInsert("Menu", "제육볶음", 0);
        //dbInsert("Menu", "탕수육", 0);
        //dbInsert("Menu", "짜장면", 0);
        //dbInsert("Menu", "고기만두", 0);
        //dbInsert("Menu", "크림파스타", 0);
        //dbInsert("Menu", "프라이드 치킨", 0);
        //dbInsert("Menu", "시저 샐러드", 0);
        //dbInsert("Menu", "어묵우동", 0);
        //dbInsert("Menu", "일식 돈까스", 0);
        //dbInsert("Menu", "쌀국수", 0);





        buttonControl();

        for (int i=0; i<28; i++){
            buttonColor(i);
        }


    }

    void dbSelect1(){
        String sql;
        int vall;
        nameeee.clear();
        sql = "select * from Ingredient where Value = '1'";
        Cursor cursor = db.rawQuery(sql, null);
        while (cursor.moveToNext()){
            String id = cursor.getString(0);
            String number = cursor.getString(1);
            vall = Integer.parseInt(cursor.getString(2));
            Log.d(TAG,  "select: " + id + number + vall);
            nameeee.add(number);
        }
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
            colorvalue = Integer.parseInt(cursor.getString(2));
            Log.d(TAG,  "select: " + id + number + vall);
        }
    }

    void dbSelect(int aa){
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
            vall = Integer.parseInt(cursor.getString(2));
            Log.d(TAG,  "select: " + id + number + vall);
        }

        if (vall == 0){
            Toast.makeText(this, "장바구니에 담겼습니다.", Toast.LENGTH_SHORT).show();
            dbUpdate1(aa);
            shopping_bag.add(number);
        }
        else if (vall == 1){
            Toast.makeText(this, "장바구니에서 제거되었습니다.", Toast.LENGTH_SHORT).show();
            dbUpdate0(aa);
            shopping_bag.remove(number);
        }
    }

    void dbUpdate1(int bb){
        switch (bb){
            case 1:
                db.execSQL("update Ingredient set Value = 1 where Number = '파'");
                break;
            case 2:
                db.execSQL("update Ingredient set Value =1 where Number = '양파'");
                break;
            case 3:
                db.execSQL("update Ingredient set Value =1 where Number = '당근'");
                break;
            case 4:
                db.execSQL("update Ingredient set Value =1 where Number = '감자'");
                break;
            case 5:
                db.execSQL("update Ingredient set Value =1 where Number = '마늘'");
                break;
            case 6:
                db.execSQL("update Ingredient set Value =1 where Number = '양배추'");
                break;
            case 7:
                db.execSQL("update Ingredient set Value =1 where Number = '토마토'");
                break;
            case 8:
                db.execSQL("update Ingredient set Value =1 where Number = '시금치'");
                break;
            case 9:
                db.execSQL("update Ingredient set Value =1 where Number = '고추'");
                break;
            case 10:
                db.execSQL("update Ingredient set Value =1 where Number = '브로콜리'");
                break;
            case 11:
                db.execSQL("update Ingredient set Value =1 where Number = '콩나물'");
                break;
            case 12:
                db.execSQL("update Ingredient set Value =1 where Number = '버섯'");
                break;
            case 13:
                db.execSQL("update Ingredient set Value =1 where Number = '오이'");
                break;
            case 14:
                db.execSQL("update Ingredient set Value =1 where Number = '파프리카'");
                break;
            case 15:
                db.execSQL("update Ingredient set Value =1 where Number = '숙주'");
                break;
            case 16:
                db.execSQL("update Ingredient set Value =1 where Number = '가지'");
                break;
            case 17:
                db.execSQL("update Ingredient set Value =1 where Number = '돼지고기'");
                break;
            case 18:
                db.execSQL("update Ingredient set Value =1 where Number = '닭고기'");
                break;
            case 19:
                db.execSQL("update Ingredient set Value =1 where Number = '베이컨'");
                break;
            case 20:
                db.execSQL("update Ingredient set Value =1 where Number = '계란'");
                break;
            case 21:
                db.execSQL("update Ingredient set Value =1 where Number = '오징어'");
                break;
            case 22:
                db.execSQL("update Ingredient set Value =1 where Number = '바지락'");
                break;
            case 23:
                db.execSQL("update Ingredient set Value =1 where Number = '홍합'");
                break;
            case 24:
                db.execSQL("update Ingredient set Value =1 where Number = '새우'");
                break;
            case 25:
                db.execSQL("update Ingredient set Value =1 where Number = '두부'");
                break;
            case 26:
                db.execSQL("update Ingredient set Value =1 where Number = '어묵'");
                break;
            case 27:
                db.execSQL("update Ingredient set Value =1 where Number = '참치통조림'");
                break;
            case 28:
                db.execSQL("update Ingredient set Value =1 where Number = '떡'");
                break;
        }
        Log.d(tag, "update1 완료~");
    }

    void dbUpdate0(int cc){
        switch (cc){
            case 1:
                db.execSQL("update Ingredient set Value =0 where Number = '파'");
                break;
            case 2:
                db.execSQL("update Ingredient set Value =0 where Number = '양파'");
                break;
            case 3:
                db.execSQL("update Ingredient set Value =0 where Number = '당근'");
                break;
            case 4:
                db.execSQL("update Ingredient set Value =0 where Number = '감자'");
                break;
            case 5:
                db.execSQL("update Ingredient set Value =0 where Number = '마늘'");
                break;
            case 6:
                db.execSQL("update Ingredient set Value =0 where Number = '양배추'");
                break;
            case 7:
                db.execSQL("update Ingredient set Value =0 where Number = '토마토'");
                break;
            case 8:
                db.execSQL("update Ingredient set Value =0 where Number = '시금치'");
                break;
            case 9:
                db.execSQL("update Ingredient set Value =0 where Number = '고추'");
                break;
            case 10:
                db.execSQL("update Ingredient set Value =0 where Number = '브로콜리'");
                break;
            case 11:
                db.execSQL("update Ingredient set Value =0 where Number = '콩나물'");
                break;
            case 12:
                db.execSQL("update Ingredient set Value =0 where Number = '버섯'");
                break;
            case 13:
                db.execSQL("update Ingredient set Value =0 where Number = '오이'");
                break;
            case 14:
                db.execSQL("update Ingredient set Value =0 where Number = '파프리카'");
                break;
            case 15:
                db.execSQL("update Ingredient set Value =0 where Number = '숙주'");
                break;
            case 16:
                db.execSQL("update Ingredient set Value =0 where Number = '가지'");
                break;
            case 17:
                db.execSQL("update Ingredient set Value =0 where Number = '돼지고기'");
                break;
            case 18:
                db.execSQL("update Ingredient set Value =0 where Number = '닭고기'");
                break;
            case 19:
                db.execSQL("update Ingredient set Value =0 where Number = '베이컨'");
                break;
            case 20:
                db.execSQL("update Ingredient set Value =0 where Number = '계란'");
                break;
            case 21:
                db.execSQL("update Ingredient set Value =0 where Number = '오징어'");
                break;
            case 22:
                db.execSQL("update Ingredient set Value =0 where Number = '바지락'");
                break;
            case 23:
                db.execSQL("update Ingredient set Value =0 where Number = '홍합'");
                break;
            case 24:
                db.execSQL("update Ingredient set Value =0 where Number = '새우'");
                break;
            case 25:
                db.execSQL("update Ingredient set Value =0 where Number = '두부'");
                break;
            case 26:
                db.execSQL("update Ingredient set Value =0 where Number = '어묵'");
                break;
            case 27:
                db.execSQL("update Ingredient set Value =0 where Number = '참치통조림'");
                break;
            case 28:
                db.execSQL("update Ingredient set Value =0 where Number = '떡'");
                break;
        }
        Log.d(tag, "update0 완료~");
    }

    void DBSearch(String tableName) {
        Cursor cursor = null;

        try {
            cursor = db.query(tableName, null, null, null, null, null, null);
            if (cursor != null) {
                while (cursor.moveToNext()) {
                    String id = cursor.getString(cursor.getColumnIndex("ID"));
                    String number = cursor.getString(cursor.getColumnIndex("Number"));
                    value = cursor.getString(cursor.getColumnIndex("Value"));
                    Log.d(TAG,  "재료 목록!!!!: " + id + number + value);
                }
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

    void dbInsert(String tableName, String number, Integer val){
        //Log.d(TAG, "Insert Data "+number);

        ContentValues contentValues = new ContentValues();
        contentValues.put("Number", number);
        contentValues.put("Value", val);



        long id = db.insert(tableName, null, contentValues);

        Log.d(TAG, "id: "+id);
    }

    void dbDelete(String tableName, String number){
        Log.d(TAG, "Delete Data"+ number);
        String numberArr[] = {number};

        int n = db.delete(tableName, "Number = ?", numberArr);
        Log.d(TAG, "n: " + n);
    }

    void buttonColor(int dd){
        select(dd+1);

        if( colorvalue == 1 ){
            mBtnArray[dd].setChecked(true);
        }
        else if( colorvalue == 0) {
            mBtnArray[dd].setChecked(false);
        }
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

    public void buttonControl(){
        mBtnArray[0] = (CheckBox) findViewById(R.id.button3);
        mBtnArray[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbSelect(1);
                buttonColor(0);
            }
        });
        mBtnArray[1] = (CheckBox) findViewById(R.id.button4);
        mBtnArray[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbSelect(2);
                buttonColor(1);
            }
        });
        mBtnArray[2] = (CheckBox) findViewById(R.id.button5);
        mBtnArray[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbSelect(3);
                buttonColor(2);
            }
        });
        mBtnArray[3] = (CheckBox) findViewById(R.id.button6);
        mBtnArray[3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbSelect(4);
                buttonColor(3);
            }
        });
        mBtnArray[4] = (CheckBox) findViewById(R.id.button7);
        mBtnArray[4].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbSelect(5);
                buttonColor(4);
            }
        });
        mBtnArray[5] = (CheckBox) findViewById(R.id.button8);
        mBtnArray[5].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbSelect(6);
                buttonColor(5);
            }
        });
        mBtnArray[6] = (CheckBox) findViewById(R.id.button9);
        mBtnArray[6].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbSelect(7);
                buttonColor(6);
            }
        });
        mBtnArray[7] = (CheckBox) findViewById(R.id.button10);
        mBtnArray[7].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbSelect(8);
                buttonColor(7);
            }
        });
        mBtnArray[8] = (CheckBox) findViewById(R.id.button11);
        mBtnArray[8].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbSelect(9);
                buttonColor(8);
            }
        });
        mBtnArray[9] = (CheckBox) findViewById(R.id.button12);
        mBtnArray[9].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbSelect(10);
                buttonColor(9);
            }
        });
        mBtnArray[10] = (CheckBox) findViewById(R.id.button13);
        mBtnArray[10].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbSelect(11);
                buttonColor(10);
            }
        });
        mBtnArray[11] = (CheckBox) findViewById(R.id.button14);
        mBtnArray[11].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbSelect(12);
                buttonColor(11);
            }
        });
        mBtnArray[12] = (CheckBox) findViewById(R.id.button15);
        mBtnArray[12].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbSelect(13);
                buttonColor(12);
            }
        });
        mBtnArray[13] = (CheckBox) findViewById(R.id.button16);
        mBtnArray[13].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbSelect(14);
                buttonColor(13);
            }
        });
        mBtnArray[14] = (CheckBox) findViewById(R.id.button17);
        mBtnArray[14].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbSelect(15);
                buttonColor(14);
            }
        });
        mBtnArray[15] = (CheckBox) findViewById(R.id.button18);
        mBtnArray[15].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbSelect(16);
                buttonColor(15);
            }
        });
        mBtnArray[16] = (CheckBox) findViewById(R.id.button19);
        mBtnArray[16].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbSelect(17);
                buttonColor(16);
            }
        });
        mBtnArray[17] = (CheckBox) findViewById(R.id.button20);
        mBtnArray[17].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbSelect(18);
                buttonColor(17);
            }
        });
        mBtnArray[18] = (CheckBox) findViewById(R.id.button21);
        mBtnArray[18].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbSelect(19);
                buttonColor(18);
            }
        });
        mBtnArray[19] = (CheckBox) findViewById(R.id.button22);
        mBtnArray[19].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbSelect(20);
                buttonColor(19);
            }
        });
        mBtnArray[20] = (CheckBox) findViewById(R.id.button23);
        mBtnArray[20].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbSelect(21);
                buttonColor(20);
            }
        });
        mBtnArray[21] = (CheckBox) findViewById(R.id.button24);
        mBtnArray[21].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbSelect(22);
                buttonColor(21);
            }
        });
        mBtnArray[22] = (CheckBox) findViewById(R.id.button25);
        mBtnArray[22].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbSelect(23);
                buttonColor(22);
            }
        });
        mBtnArray[23] = (CheckBox) findViewById(R.id.button26);
        mBtnArray[23].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbSelect(24);
                buttonColor(23);
            }
        });
        mBtnArray[24] = (CheckBox) findViewById(R.id.button27);
        mBtnArray[24].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbSelect(25);
                buttonColor(24);
            }
        });
        mBtnArray[25] = (CheckBox) findViewById(R.id.button28);
        mBtnArray[25].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbSelect(26);
                buttonColor(25);
            }
        });
        mBtnArray[26] = (CheckBox) findViewById(R.id.button29);
        mBtnArray[26].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbSelect(27);
                buttonColor(26);
            }
        });
        mBtnArray[27] = (CheckBox) findViewById(R.id.button30);
        mBtnArray[27].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbSelect(28);
                buttonColor(27);
            }
        });
    }
}
