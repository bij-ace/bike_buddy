package com.bike.buddy.bikebuddy.retrofit.service;

import com.bike.buddy.bikebuddy.AppConstants;
import com.bike.buddy.bikebuddy.retrofit.api.BikeBuddyApi;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BikeBuddyService {

    public static BikeBuddyApi createService() {

        final OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(60, TimeUnit.SECONDS)
                .build();

        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(AppConstants.APPLICATION_BASE_URL)
                .client(okHttpClient)
                .build()
                .create(BikeBuddyApi.class);
    }

}
