package com.example.hello;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

public class List_New extends AppCompatActivity {

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

    ListView mListView;
    ListViewAdapter adapter;
    ArrayList<Choice_New_Activity> list;
    ArrayList<String> revol = new ArrayList<String>();

    ChoiceActivity choiceActivity = (ChoiceActivity) ChoiceActivity.activity;

    Button btn_Select;
    CheckBox check_Korean;
    CheckBox check_Chinese;
    CheckBox check_Western;
    CheckBox check_Amount;
    CheckBox check_Etc;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        Toolbar tb = (Toolbar) findViewById(R.id.app_toolbar) ;
        setSupportActionBar(tb) ;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        adapter = new ListViewAdapter() ;
        mListView = (ListView) findViewById(R.id.list_view);
        mListView.setAdapter(adapter);


        Intent Code = getIntent();
        int sep = Code.getExtras().getInt("Code");
        list = (ArrayList<Choice_New_Activity>) Code.getSerializableExtra("list");


        calculation();
        setListView();


        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                ListViewItem item = (ListViewItem) parent.getItemAtPosition(position) ;
                String titleStr = item.getTitleStr();
                Drawable img = item.getIconDrawble();

                Intent intent = new Intent(List_New.this, DetailActivity.class);
                intent = intent.putExtra("position", position-1);
                intent = intent.putExtra("name", titleStr);
                v.getContext().startActivity(intent);

                //Toast.makeText(v.getContext(), titleStr, Toast.LENGTH_SHORT).show();
            }
        });

    }



    void calculation(){
        for (int i=0; i<list.size(); i++){
            if (re_1.contains(String.valueOf(list.get(i)))){
                if (!revol.contains("돼지고기 김치찌개")) {
                    String str = "돼지고기 김치찌개";
                    revol.add(str);
                }
            }
            if (re_2.contains(String.valueOf(list.get(i)))){
                if (!revol.contains("된장찌개")) {
                    revol.add("된장찌개");
                }
            }
            if (re_3.contains(String.valueOf(list.get(i)))){
                if (!revol.contains("제육볶음")) {
                    revol.add("제육볶음");
                }
            }
            if (re_4.contains(String.valueOf(list.get(i)))){
                if (!revol.contains("탕수육")) {
                    revol.add("탕수육");
                }
            }
            if (re_5.contains(String.valueOf(list.get(i)))){
                if (!revol.contains("짜장면")) {
                    revol.add("짜장면");
                }
            }
            if (re_6.contains(String.valueOf(list.get(i)))){
                if (!revol.contains("고기만두")) {
                    revol.add("고기만두");
                }
            }
            if (re_7.contains(String.valueOf(list.get(i)))){
                if (!revol.contains("크림파스타")) {
                    revol.add("크림파스타");
                }
            }
            if (re_8.contains(String.valueOf(list.get(i)))){
                if (!revol.contains("프라이드 치킨")) {
                    revol.add("프라이드 치킨");
                }
            }
            if (re_9.contains(String.valueOf(list.get(i)))){
                if (!revol.contains("시저 샐러드")) {
                    revol.add("시저 샐러드");
                }
            }
            if (re_10.contains(String.valueOf(list.get(i)))){
                if (!revol.contains("어묵우동")) {
                    revol.add("어묵우동");
                }
            }
            if (re_11.contains(String.valueOf(list.get(i)))){
                if (!revol.contains("일식 돈까스")) {
                    revol.add("일식 돈까스");
                }
            }
            if (re_12.contains(String.valueOf(list.get(i)))){
                if (!revol.contains("쌀국수")) {
                    revol.add("쌀국수");
                }
            }
        }
    }

    void setListView(){
        adapter.removeItem();

        for (int i = 0; i< revol.size(); i++) {
            String aa = String.valueOf(revol.get(i));
            ListviewOn(aa);
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
            case android.R.id.home: { //toolbar의 back키 눌렀을 때 동작
                finish();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }
}
