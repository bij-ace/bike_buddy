package com.bike.buddy.bikebuddy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.bike.buddy.bikebuddy.adapters.StationsCustomAdapter;
import com.bike.buddy.bikebuddy.retrofit.model.Station;

import java.util.List;

public class StationResultScreenActivity extends AppCompatActivity {
    RecyclerView stationRecyclerView;
    List<Station> stationsList = null;
    TextView emptyView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_result_screen);
        setupViews();
        LinearLayoutManager llm = new LinearLayoutManager(getApplication());
        stationRecyclerView.setLayoutManager(llm);
        if (stationsList != null) {
            StationsCustomAdapter adapter = new StationsCustomAdapter(stationsList);
            stationRecyclerView.setAdapter(adapter);
        } else {
            emptyView.setVisibility(View.VISIBLE);
        }
    }

    private void setupViews() {
        stationRecyclerView = (RecyclerView) findViewById(R.id.recycler_view_list);
        emptyView = (TextView) findViewById(R.id.emty_view);
    }


}
