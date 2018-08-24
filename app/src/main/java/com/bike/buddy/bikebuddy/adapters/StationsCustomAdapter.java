package com.bike.buddy.bikebuddy.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.bike.buddy.bikebuddy.R;
import com.bike.buddy.bikebuddy.retrofit.model.Station;

import java.util.List;

public class StationsCustomAdapter extends RecyclerView.Adapter<StationsCustomAdapter.MyViewHolder> {

    private List<Station> stationsList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name, availibilty, address;
        public CardView cv;
        public Button pndButton;

        public MyViewHolder(View view) {
            super(view);
            cv = (CardView)itemView.findViewById(R.id.cv);
            name = (TextView) view.findViewById(R.id.name);
            availibilty = (TextView) view.findViewById(R.id.availibilty);
            address = (TextView) view.findViewById(R.id.address);
            pndButton = (Button) view.findViewById(R.id.pnd_button);
        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public StationsCustomAdapter(List<Station> stationsList) {
        this.stationsList = stationsList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.station_item_list, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Station station = stationsList.get(position);
        holder.name.setText("Address : " + station.getName());
        holder.availibilty.setText("Number of Available Bikes : " + station.getFree_bikes());
        holder.address.setText(station.getAddress());
    }

    @Override
    public int getItemCount() {
        return stationsList.size();
    }
}
