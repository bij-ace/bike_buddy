package com.bike.buddy.bikebuddy.retrofit.api;

import com.bike.buddy.bikebuddy.retrofit.model.NetworksResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface BikeBuddyApi {

    @GET("v2/networks")
    Call<NetworksResponse> getNetworks();

}
