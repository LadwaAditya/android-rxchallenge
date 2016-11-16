package com.example.aditya.nyuyu;

import android.app.Application;
import android.util.Log;

import com.example.aditya.nyuyu.data.component.DaggerNetComponent;
import com.example.aditya.nyuyu.data.component.NetComponent;
import com.example.aditya.nyuyu.data.module.AppModule;
import com.example.aditya.nyuyu.data.module.NetModule;


public class Nyuyu extends Application {
    private NetComponent mNetComponent;
    public static final String TAG = Nyuyu.class.getSimpleName();
    @Override
    public void onCreate() {
        Log.d(TAG, "OnCreate");

        super.onCreate();
        mNetComponent = DaggerNetComponent.builder()
                .appModule(new AppModule(this))
                .netModule(new NetModule("http://swapi.co/"))
                .build();
    }

    public NetComponent getNetComponent() {
        return mNetComponent;
    }
}
