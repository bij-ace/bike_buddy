package com.bike.buddy.bikebuddy;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.bike.buddy.bikebuddy.retrofit.model.Network;
import com.google.gson.Gson;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private  List<Network> filtered;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.e("check", new Gson().toJson(getIntent().getSerializableExtra("network")));

       filtered = (List<Network>) getIntent().getSerializableExtra("network");
        fragmentLoader();
    }

    private  void fragmentLoader()
    {
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentById(R.id.main_container);
        if(fragment == null)
        {fragment = MapFragment.newInstance(filtered);
            fragmentManager.beginTransaction().add(R.id.main_container, fragment).commit();
        }
    }
}
