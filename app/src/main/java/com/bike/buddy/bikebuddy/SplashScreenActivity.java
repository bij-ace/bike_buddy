package com.bike.buddy.bikebuddy;


import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.bike.buddy.bikebuddy.retrofit.api.BikeBuddyApi;
import com.bike.buddy.bikebuddy.retrofit.model.Network;
import com.bike.buddy.bikebuddy.retrofit.model.NetworksResponse;
import com.bike.buddy.bikebuddy.retrofit.service.BikeBuddyService;
import com.bike.buddy.bikebuddy.util.NetworkUtils;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SplashScreenActivity extends AppCompatActivity{
    private static int SPLASH_TIME_OUT = 3000;
    private static final String TAG = SplashScreenActivity.class.getSimpleName();
    private static final int REQUEST_PERMISSIONS_REQUEST_CODE = 34;
    private FusedLocationProviderClient mFusedLocationClient;
    protected Location mLastLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

//        cityName();
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Start your app main activity
                cityName();
                /*Intent i = new Intent(SplashScreenActivity.this, MainActivity.class);
                startActivity(i);
                // close this activity
                finish();*/
            }
        }, SPLASH_TIME_OUT);
    }

    @Override
    public void onStart() {
        super.onStart();
        if (!checkPermissions()) {
            requestPermissions();
        } else {
            getLastLocation();
        }
    }
    @SuppressLint("MissingPermission")
    private void getLastLocation() {
        mFusedLocationClient.getLastLocation().addOnCompleteListener(this, new OnCompleteListener<Location>() {
            @Override
            public void onComplete(@NonNull Task<Location> task) {
                if (task.isSuccessful() && task.getResult() != null) {
                    mLastLocation = task.getResult();
                    Log.e("TEST", String.valueOf(mLastLocation.getLatitude()));
//                    mZSP.setLat_val((float) mLastLocation.getLatitude());
//                    mZSP.setLong_val((float) mLastLocation.getLongitude());
                } else {
                    Log.w(TAG, "getLastLocation:exception", task.getException());
                }
            }
        });
    }
    private boolean checkPermissions() {
        int permissionState = ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION);
        return permissionState == PackageManager.PERMISSION_GRANTED;
    }
    private void requestPermissions() {
        boolean shouldProvideRationale = ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION);
        if (shouldProvideRationale) {

            Log.i(TAG, "Displaying permission rationale to provide additional context.");
        } else {
            Log.i(TAG, "Requesting permission");
            startLocationPermissionRequest();
        }
    }
    private void startLocationPermissionRequest() {
        ActivityCompat.requestPermissions(SplashScreenActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_PERMISSIONS_REQUEST_CODE);
    }
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        Log.i(TAG, "onRequestPermissionResult");
        if (requestCode == REQUEST_PERMISSIONS_REQUEST_CODE) {
            if (grantResults.length <= 0) {
                Log.i(TAG, "User interaction was cancelled.");
            } else if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getLastLocation();
            } else {
                Log.e("ERROR", "PERMISSION NOT GIVEN");
            }
        }
    }
    public void cityName()
    {
        Geocoder geocoder;
        List<Address> addresses = null;
        geocoder = new Geocoder(this, Locale.getDefault());
        try {
            addresses = geocoder.getFromLocation(mLastLocation.getLatitude(), mLastLocation.getLongitude(), 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5
        } catch (IOException e) {
            e.printStackTrace();
        }
        String address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
        String city = addresses.get(0).getLocality();
        String state = addresses.get(0).getAdminArea();
        String country = addresses.get(0).getCountryName();
        String postalCode = addresses.get(0).getPostalCode();
        String knownName = addresses.get(0).getFeatureName();
        Log.e("CITY", city);

        loadNetworks(city);
    }

    public void loadNetworks(String city){
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
                                filter(network->network.getLocation().getCity().contains("Denver"))
                                // filter(network->network.getLocation().getCity().contains(city)) // there is no data for this location 'Southfield' so it is hardcoded for now
                                .collect(Collectors.toList());

                        Log.e("filtered networks response", new Gson().toJson(filteredNetworks).toString());

                        Intent i = new Intent(SplashScreenActivity.this, MainActivity.class);
                        i.putExtra("network", (Serializable) filteredNetworks);
                        startActivity(i);
                        // close this activity
                        finish();

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
