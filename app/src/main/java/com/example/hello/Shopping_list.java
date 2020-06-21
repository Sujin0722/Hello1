package com.example.hello;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import static android.content.ContentValues.TAG;

public class Shopping_list extends AppCompatActivity {

    private Intent intent;
    TextView textView;
    Button[] mBtnArray = new Button[36];
    String s;
    int size;
    int size1;
    int flag;
    ArrayList<String> intent_bag = new ArrayList<String>();
    ArrayList<String> bag = new ArrayList<String>();
    ArrayList<String> menu = new ArrayList<String>();
    public String[] ingredient = {
            "파", "양파", "당근","감자","마늘","양배추","토마토","시금치","고추","브로콜리","콩나물","버섯","오이","파프리카","숙주","가지",
            "돼지고기","닭고기","베이컨","계란",
            "오징어","바지락","홍합","새우",
            "두부","어묵","참치통조림","떡"
    };


    public String[] re1={"김치", "돼지고기", "두부", "파", "양파", "마늘", "고춧가루"};
    public String[] re2={"두부", "애호박", "양파", "된장", "쌈장", "고춧가루"};
    public String[] re3={"돼지고기", "고추장", "고춧가루", "맛술", "올리고당", "설탕", "마늘", "파", "당근", "양파", "참기름"};
    public String[] re4={"돼지고기","감자 전분", "옥수수 전분", "식초", "식용유", "양파", "당근", "오이", "물", "간장", "설탕"};
    public String[] re5={"양파", "파", "돼지고기", "새우", "오이", "춘장"};
    public String[] re6={"돼지고기", "소고기", "양배추", "쪽파", "마늘", "생강", "치킨스톡", "굴소스","참기름", "맛술", "만두피"};
    public String[] re7={"양파", "베이컨", "스파게티 면","올리브유","마늘","우유","체다치즈","후추"};
    public String[] re8={"닭고기", "튀김가루","식용유","우유"};
    public String[] re9={"로메인","빵","레몬즙","멸치액젓","설탕","올리브오일","올리보오일","파마산가루","양파","마늘","후추"};
    public String[] re10={"멸치","파","다시마","가스오부시","국간장","미림","청주","어묵","우동연"};
    public String[] re11={"돼지고기","요거트","후추","밀가루","우유","계란","빵가루"};
    public String[] re12={"쌀국수","소고기","숙주","양파","식초","설탕","다시마","치킨스톡","간장","피쉬소스","설탕","생강가루"};
    int[] length = {re1.length, re2.length, re3.length, re4.length, re5.length,re6.length,re7.length,re8.length,re9.length,re10.length,re11.length, re12.length};

    ArrayList<String> re_1 = new ArrayList<String>(Arrays.asList(re1));
    ArrayList<String> re_2 = new ArrayList<String>(Arrays.asList(re2));
    ArrayList<String> re_3 = new ArrayList<String>(Arrays.asList(re3));
    ArrayList<String> re_4 = new ArrayList<String>(Arrays.asList(re4));
    ArrayList<String> re_5 = new ArrayList<String>(Arrays.asList(re5));
    ArrayList<String> re_6 = new ArrayList<String>(Arrays.asList(re6));
    ArrayList<String> re_7 = new ArrayList<String>(Arrays.asList(re7));
    ArrayList<String> re_8 = new ArrayList<String>(Arrays.asList(re8));
    ArrayList<String> re_9 = new ArrayList<String>(Arrays.asList(re9));
    ArrayList<String> re_10 = new ArrayList<String>(Arrays.asList(re10));
    ArrayList<String> re_11 = new ArrayList<String>(Arrays.asList(re11));
    ArrayList<String> re_12 = new ArrayList<String>(Arrays.asList(re12));
    ArrayList<String> btnList = new ArrayList<String>();


    private SQLiteDatabase db;
    DBHelper helper;
    String dbName = "ingredient_table.db";
    int dbVersion = 1;
    String tag = "SQLite";
    int val=0;

    ListView mListView;
    ListViewAdapter adapter;
    private ArrayList<Integer> hoho = new ArrayList<Integer>();


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping);

        Toolbar tb = (Toolbar) findViewById(R.id.app_toolbar3) ;
        setSupportActionBar(tb) ;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        helper = new DBHelper(this, "DB", null, 1);
        db = helper.getWritableDatabase();


        adapter = new ListViewAdapter() ;
        mListView = (ListView) findViewById(R.id.shoppinglist_view);
        mListView.setAdapter(adapter);

        if(intent_bag.indexOf("파") == -1){
            for (int i=0; i<28; i++){
                intent_bag.add(ingredient[i]);
            }
            Log.d(TAG,  "**intent_bag: " + intent_bag);
        }

        dbSelect(); // 재료선택
        ButtonSet(); // 버튼 셋
        dbSelect_Menu(); // 찜한 메뉴 리스트에 정리
        setListView(); // 리스트뷰 초기화, listviewon 호출
        OnClick();

        btnList.clear();
        for(int r=0; r<menu.size(); r++){
            String name = menu.get(r);
            ButtonOn(name);
        }
        ReNewButton();

    }

    public void onResume() {
        super.onResume();
        adapter = new ListViewAdapter() ;
        mListView = (ListView) findViewById(R.id.shoppinglist_view);
        mListView.setAdapter(adapter);

        if(intent_bag.indexOf("파") == -1){
            for (int i=0; i<28; i++){
                intent_bag.add(ingredient[i]);
            }
            Log.d(TAG,  "**intent_bag: " + intent_bag);
        }

        dbSelect(); // 재료선택
        ButtonSet(); // 버튼 셋
        dbSelect_Menu(); // 찜한 메뉴 리스트에 정리
        setListView(); // 리스트뷰 초기화, listviewon 호출
        OnClick();

        btnList.clear();
        for(int r=0; r<menu.size(); r++){
            String name = menu.get(r);
            ButtonOn(name);
        }
        ReNewButton();
    }


    void dbSelect(){
        String sql;
        int vall;
        sql = "select * from Ingredient where Value = '1'";
        Cursor cursor = db.rawQuery(sql, null);
        while (cursor.moveToNext()){
            String id = cursor.getString(0);
            String number = cursor.getString(1);
            vall = Integer.parseInt(cursor.getString(2));
            bag.add(number);
            Log.d(TAG,  "select: " + id + number + vall);
        }
        size = bag.size();
        Log.d(TAG,  "Bag: " + bag);
    }

    void dbSelect_Menu(){
        String sql;
        menu.clear();
        sql = "select * from Menu where Value = '1'";
        Cursor cursor = db.rawQuery(sql, null);
        while (cursor.moveToNext()){
            int id = Integer.parseInt(cursor.getString(0));
            String number = cursor.getString(1);
            flag = Integer.parseInt(cursor.getString(2));
            if(flag == 1) {
                menu.add(number);
                hoho.add(id);
            }
            Log.d(TAG,  "select_Menu: " + id + number + flag);
        }
        size1 = menu.size();
        Log.d(TAG,  "menu: " + menu);
    }

    void dbInsert(View view, String tableName, int number){
        //Log.d(TAG, "Insert Data "+number);

        ContentValues contentValues = new ContentValues();
        for(int i=0; i<28; i++){
            contentValues = new ContentValues();
            contentValues.put("val", number);
        }


        Toast.makeText(this,number, Toast.LENGTH_SHORT).show();
        long id = db.insert(tableName, null, contentValues);

        Log.d(TAG, "Value Table: "+id);
    }

    void DBSearch(String tableName) {
        Cursor cursor = null;

        try {
            cursor = db.query(tableName, null, null, null, null, null, null);
            if (cursor != null) {
                while (cursor.moveToNext()) {
                    String number = cursor.getString(cursor.getColumnIndex("Number"));
                    Log.d(TAG,  "ingredient: " + number);
                }
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

    void ButtonSet(){
        mBtnArray[0] = (Button) findViewById(R.id.button36);
        mBtnArray[1] = (Button) findViewById(R.id.button37);
        mBtnArray[2] = (Button) findViewById(R.id.button38);
        mBtnArray[3] = (Button) findViewById(R.id.button39);
        mBtnArray[4] = (Button) findViewById(R.id.button40);
        mBtnArray[5] = (Button) findViewById(R.id.button41);
        mBtnArray[6] = (Button) findViewById(R.id.button42);
        mBtnArray[7] = (Button) findViewById(R.id.button43);
        mBtnArray[8] = (Button) findViewById(R.id.button44);
        mBtnArray[9] = (Button) findViewById(R.id.button45);
        mBtnArray[10] = (Button) findViewById(R.id.button46);
        mBtnArray[11] = (Button) findViewById(R.id.button47);
        mBtnArray[12] = (Button) findViewById(R.id.button48);
        mBtnArray[13] = (Button) findViewById(R.id.button49);
        mBtnArray[14] = (Button) findViewById(R.id.button50);
        mBtnArray[15] = (Button) findViewById(R.id.button51);
        mBtnArray[16] = (Button) findViewById(R.id.button52);
        mBtnArray[17] = (Button) findViewById(R.id.button53);
        mBtnArray[18] = (Button) findViewById(R.id.button54);
        mBtnArray[19] = (Button) findViewById(R.id.button55);
        mBtnArray[20] = (Button) findViewById(R.id.button56);
        mBtnArray[21] = (Button) findViewById(R.id.button57);
        mBtnArray[22] = (Button) findViewById(R.id.button58);
        mBtnArray[23] = (Button) findViewById(R.id.button59);
        mBtnArray[24] = (Button) findViewById(R.id.button60);
        mBtnArray[25] = (Button) findViewById(R.id.button61);
        mBtnArray[26] = (Button) findViewById(R.id.button62);
        mBtnArray[27] = (Button) findViewById(R.id.button63);
        mBtnArray[28] = (Button) findViewById(R.id.button64);
        mBtnArray[29] = (Button) findViewById(R.id.button65);
        mBtnArray[30] = (Button) findViewById(R.id.button66);
        mBtnArray[31] = (Button) findViewById(R.id.button67);
        mBtnArray[32] = (Button) findViewById(R.id.button68);
        mBtnArray[33] = (Button) findViewById(R.id.button69);
        mBtnArray[34] = (Button) findViewById(R.id.button70);
        mBtnArray[35] = (Button) findViewById(R.id.button71);


        for (int i=0; i<28; i++){
            mBtnArray[i].setVisibility(View.INVISIBLE);
        }
    }

    void ReNewButton(){
        for (int i=0; i<btnList.size(); i++){
            String btn = btnList.get(i);
            Log.d(TAG,  "bagg / k: " + btn );
            mBtnArray[i].setText(btn);
            mBtnArray[i].setVisibility(View.VISIBLE);
        }
    }

    void ButtonOn(String name){
        Log.d(TAG,  "@@@@: " + name);
        switch (name){
            case "돼지고기 김치찌개": {
                for (int i = 0; i < size; i++) {
                    re_1.remove(bag.get(i));
                }
                for(int k=0; k<re_1.size(); k++){
                    if(!btnList.contains(re_1.get(k))){
                        btnList.add(re_1.get(k));
                        ReNewButton();
                    }
                }
                break;
            }
            case "된장찌개": {
                for (int i = 0; i < size; i++) {
                re_2.remove(bag.get(i));
                }
                for(int k=0; k<re_2.size(); k++){
                    if(!btnList.contains(re_2.get(k))){
                        btnList.add(re_2.get(k));
                        ReNewButton();
                    }
                }
                break;
            }
            case "제육볶음": {
                for (int i = 0; i < size; i++) {
                    re_3.remove(bag.get(i));
                }
                for(int k=0; k<re_3.size(); k++){
                    if(!btnList.contains(re_3.get(k))){
                        btnList.add(re_3.get(k));
                        ReNewButton();
                    }
                }
                break;
            }
            case "탕수육":{
                for (int i = 0; i < size; i++) {
                    re_4.remove(bag.get(i));
                }
                for(int k=0; k<re_4.size(); k++){
                    if(!btnList.contains(re_4.get(k))){
                        btnList.add(re_4.get(k));
                        ReNewButton();
                    }
                }
                break;
            }
            case "짜장면":{
                for (int i = 0; i < size; i++) {
                    re_5.remove(bag.get(i));
                }
                for(int k=0; k<re_5.size(); k++){
                    if(!btnList.contains(re_5.get(k))){
                        btnList.add(re_5.get(k));
                        ReNewButton();
                    }
                }
                break;
            }
            case "고기만두":{
                for (int i = 0; i < size; i++) {
                    re_6.remove(bag.get(i));
                }
                for(int k=0; k<re_6.size(); k++){
                    if(!btnList.contains(re_6.get(k))){
                        btnList.add(re_6.get(k));
                        ReNewButton();
                    }
                }
                break;
            }
            case "크림파스타":{
                for (int i = 0; i < size; i++) {
                    re_7.remove(bag.get(i));
                }
                for(int k=0; k<re_7.size(); k++){
                    if(!btnList.contains(re_7.get(k))){
                        btnList.add(re_7.get(k));
                        ReNewButton();
                    }
                }
                break;
            }
            case "프라이드 치킨":{
                for (int i = 0; i < size; i++) {
                    re_8.remove(bag.get(i));
                }
                for(int k=0; k<re_8.size(); k++){
                    if(!btnList.contains(re_8.get(k))){
                        btnList.add(re_8.get(k));
                        ReNewButton();
                    }
                }
                break;
            }
            case "시저 샐러드":{
                for (int i = 0; i < size; i++) {
                    re_9.remove(bag.get(i));
                }
                for(int k=0; k<re_9.size(); k++){
                    if(!btnList.contains(re_9.get(k))){
                        btnList.add(re_9.get(k));
                        ReNewButton();
                    }
                }
                break;
            }
            case "어묵우동":{
                for (int i = 0; i < size; i++) {
                    re_10.remove(bag.get(i));
                }
                for(int k=0; k<re_10.size(); k++){
                    if(!btnList.contains(re_10.get(k))){
                        btnList.add(re_10.get(k));
                        ReNewButton();
                    }
                }
                break;
            }
            case "일식 돈까스":{
                for (int i = 0; i < size; i++) {
                    re_11.remove(bag.get(i));
                }
                for(int k=0; k<re_11.size(); k++){
                    if(!btnList.contains(re_11.get(k))){
                        btnList.add(re_11.get(k));
                        ReNewButton();
                    }
                }
                break;
            }
            case "쌀국수":{
                for (int i = 0; i < size; i++) {
                    re_12.remove(bag.get(i));
                }
                for(int k=0; k<re_12.size(); k++){
                    if(!btnList.contains(re_12.get(k))){
                        btnList.add(re_12.get(k));
                        ReNewButton();
                    }
                }
                break;
            }
        }
    }

    void OnClick(){
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                ListViewItem item = (ListViewItem) parent.getItemAtPosition(position) ;
                String titleStr = item.getTitleStr();
                Drawable img = item.getIconDrawble();

                Intent intent = new Intent(Shopping_list.this, DetailActivity.class);
                intent = intent.putExtra("position", hoho.get(position)-1);
                intent = intent.putExtra("name", titleStr);
                v.getContext().startActivity(intent);
                //Toast.makeText(v.getContext(), titleStr, Toast.LENGTH_SHORT).show();
            }
        });
    }

    void setListView(){
        adapter.removeItem();

        for (int i=0; i<size1; i++){
            String menuu = menu.get(i);
            ListviewOn(menuu);
        }
    }

    void ListviewOn(String aa){
        switch (aa){
            case "돼지고기 김치찌개":
                adapter.addItem(ContextCompat.getDrawable(this, R.drawable.kimchi), "돼지고기 김치찌개") ;
                break;
            case "된장찌개":
                adapter.addItem(ContextCompat.getDrawable(this, R.drawable.doenjang), "된장찌개") ;
                break;
            case "제육볶음":
                adapter.addItem(ContextCompat.getDrawable(this, R.drawable.jaeyuk), "제육볶음") ;
                break;
            case "탕수육":
                adapter.addItem(ContextCompat.getDrawable(this, R.drawable.tangsuyuk), "탕수육") ;
                break;
            case "짜장면":
                adapter.addItem(ContextCompat.getDrawable(this, R.drawable.chinese_noodle), "짜장면") ;
                break;
            case "고기만두":
                adapter.addItem(ContextCompat.getDrawable(this, R.drawable.mandu), "고기만두") ;
                break;
            case "크림파스타":
                adapter.addItem(ContextCompat.getDrawable(this, R.drawable.creampasta), "크림파스타") ;
                break;
            case "프라이드 치킨":
                adapter.addItem(ContextCompat.getDrawable(this, R.drawable.fried_chicken), "프라이드 치킨") ;
                break;
            case "시저 샐러드":
                adapter.addItem(ContextCompat.getDrawable(this, R.drawable.salad), "시저 샐러드") ;
                break;
            case "어묵우동":
                adapter.addItem(ContextCompat.getDrawable(this, R.drawable.udon), "어묵우동") ;
                break;
            case "일식 돈까스":
                adapter.addItem(ContextCompat.getDrawable(this, R.drawable.donka), "일식 돈까스") ;
                break;
            case "쌀국수":
                adapter.addItem(ContextCompat.getDrawable(this, R.drawable.vietnam), "쌀국수") ;
                break;
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
}
