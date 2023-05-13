package com.example.a20220603;

import android.app.Application;
import android.content.Context;

public class Init extends Application {

    public static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }
}
