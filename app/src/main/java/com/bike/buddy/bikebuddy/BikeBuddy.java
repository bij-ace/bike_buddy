package com.bike.buddy.bikebuddy;

import android.app.Application;

public class BikeBuddy extends Application{

    private static BikeBuddy mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }

    public static synchronized BikeBuddy getInstance() {
        return mInstance;
    }
}
