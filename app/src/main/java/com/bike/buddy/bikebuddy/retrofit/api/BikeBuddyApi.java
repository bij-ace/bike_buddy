package com.bike.buddy.bikebuddy.retrofit.api;

import com.bike.buddy.bikebuddy.retrofit.model.IncidentsResponse;
import com.bike.buddy.bikebuddy.retrofit.model.NetworksResponse;
import com.bike.buddy.bikebuddy.retrofit.model.StationNetworkResponse;
import com.bike.buddy.bikebuddy.retrofit.model.StationResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface BikeBuddyApi {

    @GET("v2/networks")
    Call<NetworksResponse> getNetworks();

    @GET("v2/networks/{id}")
    Call<StationNetworkResponse> getStations(@Path("id") String id);

    @GET("incidents")
    Call<IncidentsResponse> getIncidents(@Query("incident_type") String incidentType,
                                         @Query("query") String query);

}
