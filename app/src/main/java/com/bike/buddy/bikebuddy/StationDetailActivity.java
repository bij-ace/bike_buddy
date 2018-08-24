package com.bike.buddy.bikebuddy;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

public class StationDetailActivity extends AppCompatActivity{

    Button hazard, pickup;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_station_detail);

        init();

        hazard.setOnClickListener(v -> {

        });

        pickup.setOnClickListener(v -> {

        });
    }

    private void init() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Station Detail");

        hazard = findViewById(R.id.btnHazard);
        pickup = findViewById(R.id.btnPickup);
    }
}
