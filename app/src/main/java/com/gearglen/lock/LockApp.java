package com.gearglen.lock;

import android.app.Application;
import android.content.Intent;

import com.tencent.bugly.Bugly;


public class LockApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Bugly.init(getApplicationContext(), "2a6477915a", true);
    }
}
