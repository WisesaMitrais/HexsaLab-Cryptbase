package com.mitrais.innovation.cryptbase.activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.TransitionManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.mitrais.innovation.cryptbase.R;
import com.mitrais.innovation.cryptbase.utility.font.FontQuicksandBold;

public class SplashScreenActivity extends AppCompatActivity {

    /*Declare global variables.*/
    Handler handler;
    Intent intent;
    ViewGroup cryptbaseGroup;
    ImageView cryptbaseImage;
    FontQuicksandBold cryptbaseText;
    Runnable runnable1, runnable2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        initializeComponents();
        handler.postDelayed(runnable1, 500);
        handler.postDelayed(runnable2, 1500);
    }

    /**
     * Initialized all components in this activity.
     */
    private void initializeComponents(){
        cryptbaseGroup = findViewById(R.id.asc_cryptbase_group);
        cryptbaseImage = findViewById(R.id.asc_cryptbase_image);
        cryptbaseText = findViewById(R.id.asc_cryptbase_text);
        handler = new Handler();
        runnable1 = new Runnable() {
            @Override
            public void run() {
                TransitionManager.beginDelayedTransition(cryptbaseGroup);
                cryptbaseText.setVisibility(View.VISIBLE);
            }
        };
        runnable2 = new Runnable() {
            @Override
            public void run() {
                intent = new Intent(SplashScreenActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        };
    }
}
