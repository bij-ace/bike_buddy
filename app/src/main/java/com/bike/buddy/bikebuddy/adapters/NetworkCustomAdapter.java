package com.bike.buddy.bikebuddy.adapters;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bike.buddy.bikebuddy.R;
import com.bike.buddy.bikebuddy.retrofit.model.Network;

import java.util.List;

public class NetworkCustomAdapter extends RecyclerView.Adapter<NetworkCustomAdapter.MyViewHolder> {

    private List<Network> networkList;
    View itemView;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name, company, location;
        public CardView cv;


        public MyViewHolder(View view) {
            super(view);
            cv = (CardView)itemView.findViewById(R.id.cv);
            name = (TextView) view.findViewById(R.id.name);
            company = (TextView) view.findViewById(R.id.company);
            location = (TextView) view.findViewById(R.id.location);
        }
    }

    public NetworkCustomAdapter(List<Network> networkList) {
        this.networkList = networkList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
         itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.network_item_list, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Network network = networkList.get(position);
        holder.name.setText(network.getName());
        holder.company.setText(network.getCompanyName());
        holder.location.setText(network.getLocation().getAddress());
        holder.cv.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {

            }
        });
    }


    @Override
    public int getItemCount() {
        return networkList.size();
    }
}