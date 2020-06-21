package com.example.hello;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.viewpager.widget.PagerAdapter;

public class Pager_Adapter extends PagerAdapter {


    LayoutInflater Inflater;
    int page;
    String ID;
    int amount;
    String category;
    int id;
    String sort = "sort";
    String ingredients;
    String name;
    Activity activity;

    private Context mContext;

    public String[] menu = {
            "장바구니",
            "오늘의 요리는?",
            "레시피 리스트"
    };

    public String[] buttton = {
            "Click",
            "재료관리",
            "Click"
    };

    public Pager_Adapter(Context context) {
        this.mContext = context;
    }

    public int getCount(){
        return 3;
    }

    public boolean isViewFromObject(View view, Object object){
        return (view == (View)object);
    }

    public Object instantiateItem(ViewGroup container, int position){

        Inflater = (LayoutInflater) mContext.getSystemService(mContext.LAYOUT_INFLATER_SERVICE);
        View view = Inflater.inflate(R.layout.slide_layout, container, false);

        TextView textView = (TextView) view.findViewById(R.id.title);
        Button btn_A = (Button) view.findViewById(R.id.button);
        Button btn_B = (Button) view.findViewById(R.id.button722);

        page = position;

        switch (page){
            case 0:
                btn_B.setVisibility(View.INVISIBLE);
                btn_A.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(v.getContext(), Shopping_list.class);
                        v.getContext().startActivity(intent);
                    }
                });
                break;

            case 1:
                btn_B.setVisibility(View.VISIBLE);
                btn_A.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(v.getContext(), ChoiceActivity.class);
                        v.getContext().startActivity(intent);
                    }
                });
                btn_B.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(v.getContext(), Choice_New_Activity.class);
                        v.getContext().startActivity(intent);
                    }
                });
                break;

            case 2:
                btn_B.setVisibility(View.INVISIBLE);
                btn_A.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(v.getContext(), ListActivity.class);
                        v.getContext().startActivity(intent);
                    }
                });
                break;

            default:
                break;
        }

        textView.setText(menu[position]);
        btn_A.setText(buttton[position]);
        container.addView(view);
        return view;
    }
    
    public void destroyItem(ViewGroup container, int position, Object object){
        container.removeView((RelativeLayout)object);
    }
}
