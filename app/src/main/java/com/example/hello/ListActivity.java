package com.example.hello;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

public class ListActivity extends AppCompatActivity {
    public static final String TAG = "YOUR-TAG-NAME";

    ListView mListView;
    ListViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        Toolbar tb = (Toolbar) findViewById(R.id.app_toolbar) ;
        setSupportActionBar(tb) ;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        adapter = new ListViewAdapter() ;
        mListView = (ListView) findViewById(R.id.list_view);
        mListView.setAdapter(adapter);

        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.kimchi), "돼지고기 김치찌개") ;
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.doenjang), "된장찌개") ;
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.jaeyuk), "제육볶음") ;
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.tangsuyuk), "탕수육") ;
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.chinese_noodle), "짜장면") ;
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.mandu), "고기만두") ;
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.creampasta), "크림파스타") ;
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.fried_chicken), "프라이드 치킨") ;
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.salad), "시저 샐러드") ;
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.udon), "어묵우동") ;
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.donka), "일식 돈까스") ;
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.vietnam), "쌀국수") ;

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                ListViewItem item = (ListViewItem) parent.getItemAtPosition(position) ;
                String titleStr = item.getTitleStr();
                Drawable img = item.getIconDrawble();

                Intent intent = new Intent(ListActivity.this, DetailActivity.class);
                intent = intent.putExtra("position", position);
                intent = intent.putExtra("name", titleStr);
                v.getContext().startActivity(intent);
            }
        }) ;


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
