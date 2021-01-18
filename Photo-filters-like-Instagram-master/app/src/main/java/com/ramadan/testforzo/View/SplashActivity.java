package com.ramadan.testforzo.View;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.ramadan.testforzo.Constants.AppConfig;
import com.ramadan.testforzo.R;

/**
 * Created by Mahmoud Ramadan on 8/1/16.
 */
public class SplashActivity  extends ParentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        //switch to Main screen after 3 secs
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                displayMainActivity();

            }
        }, AppConfig.SPLASH_DURATION);
    }


    private void displayMainActivity(){
        startActivity(new Intent(getApplicationContext(),MainActivity.class).
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));
    }
}
