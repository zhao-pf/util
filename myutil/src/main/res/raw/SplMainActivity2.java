package com.example.a20220603;

import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

public class SplMainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_spl_main2);
        new Handler(getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                MainActivity.start(SplMainActivity2.this);
                finish();
            }
        }, 1000);


    }
}