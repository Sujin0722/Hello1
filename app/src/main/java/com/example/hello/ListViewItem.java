package com.example.hello;

import android.graphics.drawable.Drawable;

public class ListViewItem {

    private Drawable iconDrawble;
    private String titleStr;

    public void setIconDrawble(Drawable icon){
        iconDrawble = icon;
    }

    public void setTitleStr(String title){
        titleStr = title;
    }

    public Drawable getIconDrawble(){
        return this.iconDrawble;
    }

    public String getTitleStr() {
        return this.titleStr;
    }


}