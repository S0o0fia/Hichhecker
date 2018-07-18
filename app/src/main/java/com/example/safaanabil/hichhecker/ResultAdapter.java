package com.example.safaanabil.hichhecker;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.TextView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;


/**
 * Created by Safaa Nabil on 6/21/2018.
 */

public class ResultAdapter extends  RecyclerView.Adapter<ResultAdapter.holder>  {
    ArrayList<Resultinfo> resultinfos;
    Context con ;

    public ResultAdapter (ArrayList<Resultinfo> resultinfos , Context con) {
        this.resultinfos = resultinfos;
        this.con = con;
    }

    @Override
    public holder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.track , parent , false );

        holder holder = new holder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(holder holder, int position) {

        holder.username.setText(resultinfos.get(position).getName());
        holder.fctiy.setText(resultinfos.get(position).getFcity());
        holder.tcity.setText(resultinfos.get(position).getTcity());
        holder.price.setText(resultinfos.get(position).getPrice());
    }

    @Override
    public int getItemCount() {
        return resultinfos.size();
    }

    public class holder extends RecyclerView.ViewHolder {

       TextView username , price , tcity , fctiy ;



        public holder(View itemView) {
            super(itemView);
           username = (TextView) itemView.findViewById(R.id.valuedate);
            fctiy = (TextView) itemView.findViewById(R.id.f_city);
            tcity = (TextView) itemView.findViewById(R.id.t_city);
            price = (TextView) itemView.findViewById(R.id.price_num);

        }
    }



}
