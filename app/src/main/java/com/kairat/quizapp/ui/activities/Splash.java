package com.kairat.quizapp.ui.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.kairat.quizapp.R;
import com.kairat.quizapp.ui.activities.main.MainActivity;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(() -> {
            Intent i = new Intent(Splash.this, MainActivity.class);
            startActivity(i);
            finish();
        }, 1000);
    }
}