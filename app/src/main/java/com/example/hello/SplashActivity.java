package com.example.hello;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class SplashActivity extends Activity {

    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);

        try {
            Thread.sleep(2000);
            startActivity(new Intent(SplashActivity.this, MainActivity.class));
            finish();
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }

    }
}
