package com.bike.buddy.bikebuddy;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
