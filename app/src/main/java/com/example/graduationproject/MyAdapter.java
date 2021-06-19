package com.example.graduationproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    Context context;
    ArrayList<Venue> venueArrayList;

    public MyAdapter(Context context, ArrayList<Venue> venueArrayList) {
        this.context = context;
        this.venueArrayList = venueArrayList;
    }

    @NonNull
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.venue,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.MyViewHolder holder, int position) {

        Venue venue = venueArrayList.get(position);
        holder.name.setText(venue.name);
        holder.location.setText(venue.city);
        holder.price.setText(String.valueOf(venue.price));
        holder.vendorName.setText(venue.vendorName);



    }

    @Override
    public int getItemCount() {
        return venueArrayList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name , price , location , vendorName;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.venueNameInMyVenue);
            price = itemView.findViewById(R.id.price1);
            location = itemView.findViewById(R.id.location21);
            vendorName = itemView.findViewById(R.id.vendorName);

        }
    }
}
