package com.example.hello;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import java.util.ArrayList;
import java.util.Arrays;

import static android.content.ContentValues.TAG;

public class DetailActivity extends YouTubeBaseActivity {

    private Intent intent;
    String nameTitle;
    int position;
    TextView tileset;
    TextView detail;
    TextView[] textView = new TextView[15];
    TextView[] detailText = new TextView[12];
    CheckBox btn_heart;
    YouTubePlayerView youTubePlayerView;
    YouTubePlayer.OnInitializedListener listener;
    String Id;
    int flag;
    int val;
    int me;

    private SQLiteDatabase db;
    DBHelper helper;
    String dbName = "menu_table.db";
    int dbVersion = 1;
    String tag = "SQLite";

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

    ArrayList<String> arrayList1 = new ArrayList<String>();
    ArrayList<String> arrayList2 = new ArrayList<String>();
    ArrayList<String> arrayList3 = new ArrayList<String>();
    ArrayList<String> arrayList4 = new ArrayList<String>();
    ArrayList<String> arrayList5 = new ArrayList<String>();
    ArrayList<String> arrayList6 = new ArrayList<String>();
    ArrayList<String> arrayList7 = new ArrayList<String>();
    ArrayList<String> arrayList8 = new ArrayList<String>();
    ArrayList<String> arrayList9 = new ArrayList<String>();
    ArrayList<String> arrayList10 = new ArrayList<String>();
    ArrayList<String> arrayList11 = new ArrayList<String>();
    ArrayList<String> arrayList12 = new ArrayList<String>();

    char str1;


    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_detail);

        helper = new DBHelper(this, "DB", null, 1);
        db = helper.getWritableDatabase();

        intent = getIntent();
        nameTitle = intent.getExtras().getString("name");
        position = intent.getExtras().getInt("position");
        me = intent.getExtras().getInt("qwe");
        Position();

        tileset = findViewById(R.id.textView3);
        tileset.setText(nameTitle);

        youTubePlayerView = findViewById(R.id.youtubeView);
        listener = new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                youTubePlayer.cueVideo(Id);
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

            }
        };
        youTubePlayerView.initialize("sujin", listener);


        Log.d(TAG,  "디테일" + position);

        dbSelect_Menu(position+1);




        btn_heart = (CheckBox) findViewById(R.id.checkBox);
        if(flag == 1){
            btn_heart.setChecked(true);
        }else if(flag == 0){
            btn_heart.setChecked(false);
        }
        btn_heart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (btn_heart.isChecked()){
                    dbUpdate1(position+1);
                    Toast.makeText(v.getContext(), "장바구니에 담겼습니다!", Toast.LENGTH_SHORT).show();
                }
                else {
                    dbUpdate0(position+1);
                    Toast.makeText(v.getContext(), "장바구니에서 제거되었습니다.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        setDetailText();
        TextViewlist();
        setTextView();
        setStory();

    }

    void TextViewlist(){
        textView[0] = (TextView) findViewById(R.id.textView25);
        textView[1] = (TextView) findViewById(R.id.textView26);
        textView[2] = (TextView) findViewById(R.id.textView27);
        textView[3] = (TextView) findViewById(R.id.textView28);
        textView[4] = (TextView) findViewById(R.id.textView29);
        textView[5] = (TextView) findViewById(R.id.textView30);
        textView[6] = (TextView) findViewById(R.id.textView31);
        textView[7] = (TextView) findViewById(R.id.textView32);
        textView[8] = (TextView) findViewById(R.id.textView33);
        textView[9] = (TextView) findViewById(R.id.textView34);
        textView[10] = (TextView) findViewById(R.id.textView35);
        textView[11] = (TextView) findViewById(R.id.textView36);
        textView[12] = (TextView) findViewById(R.id.textView37);
        textView[13] = (TextView) findViewById(R.id.textView38);
        textView[14] = (TextView) findViewById(R.id.textView39);
    }

    void setTextView(){
        switch (position){
            case 0:
                for(int i=0; i<length[position];i++){
                    String abc = re1[i];
                    textView[i].setText(abc);
                }
                break;
            case 1:
                for(int i=0; i<length[position];i++){
                    String abc = re2[i];
                    textView[i].setText(abc);
                }
                break;
            case 2:
                for(int i=0; i<length[position];i++){
                    String abc = re3[i];
                    textView[i].setText(abc);
                }
                break;
            case 3:
                for(int i=0; i<length[position];i++){
                    String abc = re4[i];
                    textView[i].setText(abc);
                }
                break;
            case 4:
                for(int i=0; i<length[position];i++){
                    String abc = re5[i];
                    textView[i].setText(abc);
                }
                break;
            case 5:
                for(int i=0; i<length[position];i++){
                    String abc = re6[i];
                    textView[i].setText(abc);
                }
                break;
            case 6:
                for(int i=0; i<length[position];i++){
                    String abc = re7[i];
                    textView[i].setText(abc);
                }
                break;
            case 7:
                for(int i=0; i<length[position];i++){
                    String abc = re8[i];
                    textView[i].setText(abc);
                }
                break;
            case 8:
                for(int i=0; i<length[position];i++){
                    String abc = re9[i];
                    textView[i].setText(abc);
                }
                break;
            case 9:
                for(int i=0; i<length[position];i++){
                    String abc = re10[i];
                    textView[i].setText(abc);
                }
                break;
            case 10:
                for(int i=0; i<length[position];i++){
                    String abc = re11[i];
                    textView[i].setText(abc);
                }
                break;
            case 11:
                for(int i=0; i<length[position];i++){
                    String abc = re12[i];
                    textView[i].setText(abc);
                }
                break;
        }


    }

    void dbUpdate1(int bb){
        switch (bb){
            case 1:
                db.execSQL("update Menu set Value = 1 where Number = '돼지고기 김치찌개'");
                break;
            case 2:
                db.execSQL("update Menu set Value = 1 where Number = '된장찌개'");
                break;
            case 3:
                db.execSQL("update Menu set Value = 1 where Number = '제육볶음'");
                break;
            case 4:
                db.execSQL("update Menu set Value = 1 where Number = '탕수육'");
                break;
            case 5:
                db.execSQL("update Menu set Value = 1 where Number = '짜장면'");
                break;
            case 6:
                db.execSQL("update Menu set Value = 1 where Number = '고기만두'");
                break;
            case 7:
                db.execSQL("update Menu set Value = 1 where Number = '크림파스타'");
                break;
            case 8:
                db.execSQL("update Menu set Value = 1 where Number = '프라이드 치킨'");
                break;
            case 9:
                db.execSQL("update Menu set Value = 1 where Number = '시저 샐러드'");
                break;
            case 10:
                db.execSQL("update Menu set Value = 1 where Number = '어묵우동'");
                break;
            case 11:
                db.execSQL("update Menu set Value = 1 where Number = '일식 돈까스'");
                break;
            case 12:
                db.execSQL("update Menu set Value = 1 where Number = '쌀국수'");
                break;
        }
        Log.d(tag, "update1 완료~");
    }

    void dbUpdate0(int bb){
        switch (bb){
            case 1:
                db.execSQL("update Menu set Value = 0 where Number = '돼지고기 김치찌개'");
                break;
            case 2:
                db.execSQL("update Menu set Value = 0 where Number = '된장찌개'");
                break;
            case 3:
                db.execSQL("update Menu set Value = 0 where Number = '제육볶음'");
                break;
            case 4:
                db.execSQL("update Menu set Value = 0 where Number = '탕수육'");
                break;
            case 5:
                db.execSQL("update Menu set Value = 0 where Number = '짜장면'");
                break;
            case 6:
                db.execSQL("update Menu set Value = 0 where Number = '고기만두'");
                break;
            case 7:
                db.execSQL("update Menu set Value = 0 where Number = '크림파스타'");
                break;
            case 8:
                db.execSQL("update Menu set Value = 0 where Number = '프라이드 치킨'");
                break;
            case 9:
                db.execSQL("update Menu set Value = 0 where Number = '시저 샐러드'");
                break;
            case 10:
                db.execSQL("update Menu set Value = 0 where Number = '어묵우동'");
                break;
            case 11:
                db.execSQL("update Menu set Value = 0 where Number = '일식 돈까스'");
                break;
            case 12:
                db.execSQL("update Menu set Value = 0 where Number = '쌀국수'");
                break;
        }
        Log.d(tag, "update0 완료~");
    }

    void dbSelect_Menu(int bb){
        String sql;
        switch (bb){
            case 1:
                sql = "select * from Menu where Number = '돼지고기 김치찌개'";
                break;
            case 2:
                sql = "select * from Menu where Number = '된장찌개'";
                break;
            case 3:
                sql = "select * from Menu where Number = '제육볶음'";
                break;
            case 4:
                sql = "select * from Menu where Number = '탕수육'";
                break;
            case 5:
                sql = "select * from Menu where Number = '짜장면'";
                break;
            case 6:
                sql = "select * from Menu where Number = '고기만두'";
                break;
            case 7:
                sql = "select * from Menu where Number = '크림파스타'";
                break;
            case 8:
                sql = "select * from Menu where Number = '프라이드 치킨'";
                break;
            case 9:
                sql = "select * from Menu where Number = '시저 샐러드'";
                break;
            case 10:
                sql = "select * from Menu where Number = '어묵우동'";
                break;
            case 11:
                sql = "select * from Menu where Number = '일식 돈까스'";
                break;
            case 12:
                sql = "select * from Menu where Number = '쌀국수'";
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + bb);
        }
        Cursor cursor = db.rawQuery(sql, null);
        while (cursor.moveToNext()){
            String id = cursor.getString(0);
            String number = cursor.getString(1);
            flag = Integer.parseInt(cursor.getString(2));
            Log.d(TAG,  "select_Menu: " + id + number + flag);
        }
    }

    void dbInsert(View view, String tableName, String number, Integer val){

        ContentValues contentValues = new ContentValues();
        contentValues.put("Number", number);
        contentValues.put("Value", val);

        long id = db.insert(tableName, null, contentValues);

        Log.d(TAG, "id: "+number);
    }

    void DBSearch(String tableName) {
        Cursor cursor = null;

        try {
            cursor = db.query(tableName, null, null, null, null, null, null);
            if (cursor != null) {
                while (cursor.moveToNext()) {
                    String number = cursor.getString(cursor.getColumnIndex("Number"));
                    String value = cursor.getString(cursor.getColumnIndex("Value"));
                    Log.d(TAG,  "ingredient: " + number + value);
                }
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

    void setDetailText(){
        detailText[0] = findViewById(R.id.textView7);
        detailText[1] = findViewById(R.id.textView13);
        detailText[2] = findViewById(R.id.textView14);
        detailText[3] = findViewById(R.id.textView15);
        detailText[4] = findViewById(R.id.textView16);
        detailText[5] = findViewById(R.id.textView17);
        detailText[6] = findViewById(R.id.textView18);
        detailText[7] = findViewById(R.id.textView19);
        detailText[8] = findViewById(R.id.textView20);
        detailText[9] = findViewById(R.id.textView21);
        detailText[10] = findViewById(R.id.textView22);
        detailText[11] = findViewById(R.id.textView23);
    }

    void setStory(){

        String[] text0={"1.고기는 기름기가 많은 삼겹살 부위를 사용하시면 더 맛있어요",
                "2. 같이 넣어줄 야채는 먹기좋게 잘라서 준비해주시고요",
                "3. 잘라놓은 고기를 팬에 올려 기름이 나올때까지 구워주세요 ~",
                "4. 고기를 적당히 구워서 기름이 살짝 나올때쯤 썰어둔 대파를 몽땅 넣고 같이 한번 볶아주세요",
                "5. 고기가 노릇노릇하게 구워졌을 때 설탕 1큰술을 먼저 넣어 한번 볶아주시고요",
                "6. 볶아주다가 고기가 많이 노릇해지면 물을 1/2컵 넣어주세요",
                "7. 그리고 다진마늘 1큰술, 고추장 1/2큰술, 고춧가루 2큰술, 간장 3큰술 넣어주세요",
                "8. 어느정도 어우러지면 준비해두었던 야채도 몽땅 넣고 볶아주세요~" ,
                "9. 야채와 고기가 어느정도 잘 어우러졌을 때 마지막으로 참기름 살짝 넣고 마무리 해주시면 완성"};
        arrayList1.addAll(Arrays.asList(text0));
        String[] text1 = {"① 냄비나 뚝배기에 밥공기 가득 물을 붓고 끓인다. (이 때 감자넣기) " ,
                "② 물이 끓으면 된장1, 쌈장1을 넣고 끓인다. " ,
                "③ 보글보글 끓으면 애호박, 양파를 넣는다. (2~3분) " ,
                "④ 두부를 넣고 고춧가루 반스푼 넣고 3분정도 더 끓여준다. " ,
                "⑤ 간을 보고 짜면 물을 추가한다. " ,
                "⑥ 맛이 부족하다 느껴지면 다시다를 조금 넣는다."};
        arrayList2.addAll(Arrays.asList(text1));
        String[] text2 = {"1.돼지고기 앞다리살 1근(600g), 진간장 2큰술, 설탕 2큰술을 잘 섞어 양념장을 만드는 동안 잠시 재워둬요. " ,
                "2.고춧가루 2큰술, 고추장 2큰술, 맛술 2큰술, 물엿(올리고당) 2큰술, 굴소스 1큰술, 다진 마늘 1큰술을 넣고 양념장을 만들어요. " ,
                "3.대파 100g, 양파 1개, 당근 1/3개,고추 2~3개를 썰어 준비해요. " ,
                "4.팬에 기름을 두르고 재워둔 고기를 센불에서 볶아요.(끝까지 센불 유지). " ,
                "5.고기가 반쯤 익으면 양념장을 넣고 볶아줘요. " ,
                "6.고기가 80%쯤 익었을 때 채소를 넣고 볶아요. " ,
                "7.양파가 투명해지면 고추, 통깨, 참기름, 후추를 넣고 잘 섞어주면 완성이에요."};
        arrayList3.addAll(Arrays.asList(text2));
        String[] text3 = {"1. 고기는 우유에 재워둡니다" ,
                "2. 양파는 깍둑썰기로 당근은 얇은 반달모양으로 썰어줍니다" ,
                "3. 소스재료를 넣고 섞어주세요" ,
                "4. 재워둔 고기는 한입크기로 썰어주세요" ,
                "5. 썰어둔 고기에 튀김가루를 묻혀줍니다" ,
                "6. 튀김가루를 입은 고기에 튀김옷을 입혀주세요" ,
                "7. 예열해둔 기름에 고기를 넣고 튀겨줍니다" ,
               "9. 파향이 올라오면 당근과 양파를 넣고 볶아줍니다" ,
                "10. 양파가 익으면 소스를 넣고 물을 적당히 넣고 끓여줍니다." ,
                "11. 소스가 보글보글 끓어오르면 잠시 기다렸다가 전분물을 넣고 농도를 맞춰줍니다"};
        arrayList4.addAll(Arrays.asList(text3));
        String[] text4 = {"1. 후라이팬에 식용유 2컵을 붓고 춘장 1봉지를 넣고 기름에 춘장을 튀겨줍니다." ,
                "2. 짜장면 야채를 준비합니다. 오이는 돌려깎이해서 채썰고(고명용) 양배추와 양파는 큼직큼직 썰어주고 파는 잘게 잘게 썰어서 준비합니다." ,
                "3. 불을 켜지 않은 후라이팬에 식용유를 붓고 파를 넣고 볶아서 파기름을 내줍니다." ,
                "4. 파기름이 얼추 나면 잘게 썰어 놓은 돼지고기를 넣고 볶아줍니다." ,
                "5. 고기가 익으면 오이를 제외한 양배추와 양파를 넣고 볶아줍니다.",
                "6. 튀긴 춘장을 1/3컵 정도 넣고 설탕 1T를 넣고 볶아줍니다." ,
                "7. 춘장이 야채와 고루 섞이게 볶아줍니다. 이때 먹으면 흔히보던 간짜장이 됩니다." ,
                "8. 물을 재료가 자박자박 할때까지 넣어줍니다. 끓여 주다가 물 : 전분 = 3 : 1로 타준 전분물로 짜장의 농도를 걸쭉하게 만들어 줍니다." ,
                "9. 면이 익으면 그릇에 면을 셋팅하고 위에 짜장을 부어주고 오이 고명을 올려주면 완성!"};
        arrayList5.addAll(Arrays.asList(text4));
        String[] text5={"1. 돼지고기를 굵게 갈아서 2컵을 준비합니다." ,
                "2. 갈아 놓은 시판용 돼지고기를 구입하셔도 좋고요." ,
                "3. 다진 파도 2컵 준비합니다." ,
                "4. 설탕을 조금 줄여서 2/3 큰 술만 넣습니다." ,
                "5. 다진 생강 1/2 큰술, 간장 5 큰술, 굴 소스 1 큰술, 올리브유(또는 식용유) 2 큰술, 물 1/2 컵" ,
                "6. 물을 넣자마자 바로 같은 방향으로 계속 저어줍니다. 반드시 같은 방향으로만 저어주어야 물이 생기지 않아요." ,
                "7. 고기가 으깨져 죽처럼 보일 때까지 같은 방향으로만 저어주다가 참기름 1 큰 술, 후춧가루 톡톡" ,
                "8. 재료들이 서로 잘 안 떨어질 정도의 찰기가 될 때까지 저어줍니다." ,
                "9. 마지막으로 잘게 다진 부추 2컵을 준비" ,
                "10. 함께 넣어주고 같은 방향으로 잘 저어줍니다." ,
                "11. 돼지고기 부추 만두 속이 만들어집니다." ,
                "12. 만두피에 속을 넣고 한 김 오른 찜기에서 10분 정도 쪄내면 요렇게 맛진 모습으로!"};
        arrayList6.addAll(Arrays.asList(text5));
        String[] text6={"1. 물2L를 끓이고 재료를 손질해준다." ,
                "2. 물이 끓으면 소금1스푼, 올리브오일 1스푼을 넣고 파스타면을 넣고 7분간 끓인다" ,
                "3. 면이 삶아질 사이 우유200ml와 생크림200ml에 계란노른자 2개를 넣고 섞어 소스를 만들어놓는다." ,
                "4. 올리브오일을 2바퀴 두르고 센불에 베이컨 - 마늘을 넣고 볶는다." ,
                "5. 마늘이 살짝 노릇해지면 양파와 버섯을 넣고 베이컨이 익거나 양파가 투명해질 때 까지 볶는다." ,
                "6. 양파가 투명해지면 만들어놓은 소스를 넣고 슬라이스 치즈 2장을 넣고 섞는다." ,
                "7. 치즈가 녹으면 삶아두었던 면을 넣고 볶는다." ,
                "소스는 꾸덕한 느낌을 원한다면 조금 오래 볶는다." ,
                "8. 접시에 면 - 소스 - 건더기순으로 얹은 후 위에 후추와 파슬리가루를 뿌려 마무리한다."};
        arrayList7.addAll(Arrays.asList(text6));
        String[] text7={"1. 그릇에 닭고기를 넣고 우유를 부어 2시간 또는 반나절정도 냉장고에서 둔다. (이 과정은 건너뛰어도 상관없음!)" ,
                "2. 그릇에 치킨튀김가루, 물을 섞어 반죽을 만들고 우유에 재워둔 닭을 넣어 잘 섞어줍니다." ,
                "3. 비닐에 치킨튀김가루를 넣고, 반죽을 묻힌 닭고기를 넣은 후 흔들어 가루를 골고루 묻혀준다." ,
                "4. 달궈진 기름에 닭고기를 넣어 170~180℃에서 8~10분정도 바삭하게 튀겨줍니다. "};
        arrayList8.addAll(Arrays.asList(text7));
        String[] text8={"1. 소스재료들을 모두 섞어 둔다." ,
                "2. 빵은 손으로 찢어 올리브오일과 후추에 버무린다. 약불에 바삭하게 구워준다." ,
                "3. 로메인위에 크루통과 소스를 부어 완성한다."};
        arrayList9.addAll(Arrays.asList(text8));
        String[] text9={"1. 어묵우동의 맛은 육수가 좌우한다 해도 좋을만큼 중요하지요. 디포리, 멸치, 무, 표고버섯, 다시마, 청양고추를 넣고 팔팔 끓으면 다시마는 건지고 중약불로 줄여 끓여 채에 받힙니다. 간은 국간장으로 약하게 해요" ,
                "2. 채에받힌 육수 다시 냄비에 넣고 어묵꼬지넣고 잠시 끓여요. 어묵꼬치는 따로 살짝끓여 기름기 뺐어요" ,
                "3. 다른냄비에 물 끓여 우동삶아 흐르는 물에 헹궈 놓습니다" ,
                "4. 뚝배기에 우동과 어묵꼬지를 담고 육수 부어주는데요, 육수낼때 사용한 무, 표고버섯은 다시 담아 줍니다"};
        arrayList10.addAll(Arrays.asList(text9));
        String[] text10={"1. 양배추는 가늘게 채썰어 찬 물에 담궈주세요." ,
                "2. 등심을 적당한 크기로 잘라주세요." ,
                "3. 고기망치로 두드려 넓게 펴주세요." ,
                "4. 소금, 후추로 밑간을 해주세요." ,
                "5. 튀김가루, 달걀물, 빵가루 순서로 튀김옷을 입혀주세요." ,
                "6. 간장 1 식초 0.5 설탕 2 케챱 1 물 3 비율로 소스를 준비해주세요." ,
                "7. 팬에 버터를 녹이고 밀가루를 섞어 루를 만들어주세요. (비율은 1:1이에요.)" ,
                "8. 만들어둔 루에 소스를 넣고 끓여주세요." ,
                "9. 약 180도의 온도에서 튀겨주세요" ,
                "10. 그릇에 양배추와 돈까스, 밥을 올려주면 완성!"};
        arrayList11.addAll(Arrays.asList(text10));
        String[] text11={"1. 쌀국수 면을 차가운 물에 담그고 30분 정도 불려줍니다. " ,
                "2. 양파를 최대한 얇게 채썰어 줍니다." ,
                "3. 썰어놓은 양파에 설탕 1큰술, 소금 약간, 식초 1큰술을 더해 초절임을 만들어 둡니다." ,
                "4. 냄비에 물 1.2L를 붓고 다시마 조각 2개와 치킨스톡 1큐브를 넣어 끓여줍니다." ,
                "5.물이 끓으면 중약불로 줄인후 설탕 1/2큰술, 소금 1큰술, 피쉬소스 2큰술, 간장 1/2큰술, 생강가루 약간을 더해 끓여줍니다. (※ 다시마는 10분정도 끓이고 꺼내줍니다)" ,
                "6.국물맛이 돌기 시작하면 소고기를 육수에 넣고 익혀줍니다. (※ 소고기를 오래 익히면 질겨질 수 있으니 먹기 직전에 데치듯 익혀줍니다)" ,
                "7. 불려놓은 쌀국수 면을 끓는 물에 살짝 데친후 찬물에 헹궈 물기를 빼줍니다." ,
                "8. 국수에 숙주, 양파, 데친 소고기를 올리고 육수를 부어 완성합니다."};
        arrayList12.addAll(Arrays.asList(text11));



        switch (position){
            case 0:
                for(int k=0; k<arrayList1.size(); k++){
                    detailText[k].setText(arrayList1.get(k));
                }
                break;
            case 1:
                for(int k=0; k<arrayList2.size(); k++){
                    detailText[k].setText(arrayList2.get(k));
                }
                break;
            case 2:
                for(int k=0; k<arrayList3.size(); k++){
                    detailText[k].setText(arrayList3.get(k));
                }
                break;
            case 3:
                for(int k=0; k<arrayList4.size(); k++){
                    detailText[k].setText(arrayList4.get(k));
                }
                break;
            case 4:
                for(int k=0; k<arrayList5.size(); k++){
                    detailText[k].setText(arrayList5.get(k));
                }
                break;
            case 5:
                for(int k=0; k<arrayList6.size(); k++){
                    detailText[k].setText(arrayList6.get(k));
                }
                break;
            case 6:
                for(int k=0; k<arrayList7.size(); k++){
                    detailText[k].setText(arrayList7.get(k));
                }
                break;
            case 7:
                for(int k=0; k<arrayList8.size(); k++){
                    detailText[k].setText(arrayList8.get(k));
                }
                break;
            case 8:
                for(int k=0; k<arrayList9.size(); k++){
                    detailText[k].setText(arrayList9.get(k));
                }
                break;
            case 9:
                for(int k=0; k<arrayList10.size(); k++){
                    detailText[k].setText(arrayList10.get(k));
                }
                break;
            case 10:
                for(int k=0; k<arrayList11.size(); k++){
                    detailText[k].setText(arrayList11.get(k));
                }
                break;
            case 11:
                for(int k=0; k<arrayList12.size(); k++){
                    detailText[k].setText(arrayList12.get(k));
                }
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + position);
        }
    }

    public void Position(){
        switch (position){
            case 0: // 김치찌개
                Id = "3-rX2Pwvsh8";
                break;
            case 1: // 된장찌개
                Id = "KUAv8VPnpFI";
                break;
            case 2: // 제육볶음
                Id = "9Qw7PNMpX5s";
                break;
            case 3: // 탕수육
                Id = "5CqRM8MmAN4";
                break;
            case 4: // 짜장면
                Id = "CgNxJUdLPxQ";
                break;
            case 5: // 만두
                Id = "ZAMCdV1MgIw";
                break;
            case 6: // 크림파스타
                Id = "vcVzrf6xlt4";
                break;
            case 7: // 프라이드 치킨
                Id = "o9FjzEC_ATI";
                break;
            case 8: // 시저샐러드
                Id = "GmB0jikMt_w";
                break;
            case 9: // 우동
                Id = "YMXMAtC1uAc";
                break;
            case 10: // 돈까스
                Id = "E4X4FFp5mqo";
                break;
            case 11: // 쌀국수
                Id = "UyOLdDG7Smg";
                break;
        }
    }
}
