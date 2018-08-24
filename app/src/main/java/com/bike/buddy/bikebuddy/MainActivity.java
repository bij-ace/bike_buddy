package com.bike.buddy.bikebuddy;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.bike.buddy.bikebuddy.retrofit.model.Network;
import com.google.gson.Gson;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Network> filtered;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.e("check", new Gson().toJson(getIntent().getSerializableExtra("network")));

        filtered = (List<Network>) getIntent().getSerializableExtra("network");
        fragmentLoader();
    }

    @Override

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.list_view) {
            fragmentReLoader();
        }
        return true;
    }

    private void fragmentReLoader() {
    }

    private void fragmentLoader() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentById(R.id.main_container);
        if (fragment == null) {
            fragment = MapFragment.newInstance(filtered);
            fragmentManager.beginTransaction().add(R.id.main_container, fragment).commit();
        }
    }
}
