package com.bike.buddy.bikebuddy;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.bike.buddy.bikebuddy.retrofit.api.BikeBuddyApi;
import com.bike.buddy.bikebuddy.retrofit.model.Network;
import com.bike.buddy.bikebuddy.retrofit.model.NetworksResponse;
import com.bike.buddy.bikebuddy.retrofit.service.BikeBuddyService;
import com.bike.buddy.bikebuddy.util.NetworkUtils;
import com.google.gson.Gson;

import java.util.List;
import java.util.stream.Collectors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SplashScreenActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        if (NetworkUtils.isNetworkAvailable(getApplicationContext())){
            Call call;
            BikeBuddyApi api = BikeBuddyService.createService();
            call = api.getNetworks();
            call.enqueue(new Callback<NetworksResponse>() {

                @Override
                public void onResponse(Call<NetworksResponse> call, Response<NetworksResponse> response) {
                    try {
                        NetworksResponse allNetworks = response.body();

                        Log.e("networks response", new Gson().toJson(allNetworks).toString());

                        List<Network> filteredNetworks = allNetworks.getNetworks().stream().
                                filter(network->network.getLocation().getCity().contains("Melbourne"))
                                .collect(Collectors.toList());

                        Log.e("filtered networks response", new Gson().toJson(filteredNetworks).toString());
                    } catch (Exception e) {

                    }
                }

                @Override
                public void onFailure(Call<NetworksResponse> call, Throwable t) {

                }
            });
        }

    }
}
