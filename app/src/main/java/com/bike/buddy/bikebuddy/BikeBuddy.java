package com.bike.buddy.bikebuddy;

import android.app.Application;

import com.bike.buddy.bikebuddy.retrofit.model.Network;
import com.bike.buddy.bikebuddy.retrofit.model.NetworksResponse;

import java.util.List;

public class BikeBuddy extends Application{

    private static BikeBuddy mInstance;

    public NetworksResponse networks;
    public List<Network> filteredNetworks;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }

    public static synchronized BikeBuddy getInstance() {
        return mInstance;
    }
}
