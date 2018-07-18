package com.example.safaanabil.hichhecker;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Safaa Nabil on 6/25/2018.
 */

public class TripAdapter  extends  RecyclerView.Adapter<TripAdapter.holder> {

    ArrayList<Trip> trips;
    Context con;

    public TripAdapter(ArrayList<Trip> trips, Context con) {
        this.trips = trips;
        this.con = con;
    }

    @Override
    public TripAdapter.holder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.alltrip, parent, false);

        TripAdapter.holder holder = new TripAdapter.holder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(TripAdapter.holder holder, int position) {

        holder.time.setText(trips.get(position).getTime());
        holder.date.setText(trips.get(position).getDate());
        holder.phone.setText(trips.get(position).getPhone());
        holder.price.setText(trips.get(position).getPrice());
        holder.tcity.setText(trips.get(position).getTcity());
        holder.fcity.setText(trips.get(position).getFcity());

    }

    @Override
    public int getItemCount() {
        return trips.size();
    }


    public class holder extends RecyclerView.ViewHolder {

        TextView  time , date , phone , price , tcity ,fcity ;



        public holder(View itemView) {
            super(itemView);
             time = (TextView) itemView.findViewById(R.id.triptime);
             date = (TextView) itemView.findViewById(R.id.tripdate);
             phone = (TextView) itemView.findViewById(R.id.phone_number);
             price= (TextView) itemView.findViewById(R.id.price_value);
             tcity = (TextView) itemView.findViewById(R.id.to_city);
             fcity = (TextView) itemView.findViewById(R.id.from_city);


        }
    }



}

