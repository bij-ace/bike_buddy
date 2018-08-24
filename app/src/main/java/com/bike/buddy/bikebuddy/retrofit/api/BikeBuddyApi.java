package com.bike.buddy.bikebuddy.retrofit.api;

import com.bike.buddy.bikebuddy.retrofit.model.NetworksResponse;
import com.bike.buddy.bikebuddy.retrofit.model.StationResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface BikeBuddyApi {

    @GET("v2/networks")
    Call<NetworksResponse> getNetworks();

    @GET("v2/networks/{id}")
    Call<StationResponse> getStations(@Path("id") String id);
}
