package com.bike.buddy.bikebuddy;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.gson.Gson;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.e("check", new Gson().toJson(getIntent().getSerializableExtra("network")).toString());

        fragmentLoader();
    }

    private  void fragmentLoader()
    {
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentById(R.id.main_container);
        if(fragment == null)
        {fragment = new MapFragment();
            fragmentManager.beginTransaction().add(R.id.main_container, fragment).commit();
        }
    }
}
