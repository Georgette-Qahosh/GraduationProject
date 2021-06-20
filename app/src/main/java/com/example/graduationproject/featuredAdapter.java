package com.example.graduationproject;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class featuredAdapter extends RecyclerView.Adapter<featuredAdapter.MyViewHolder> {

    Context context;
    ArrayList<FeaturedVenue> featuredVenueArrayList;
    FirebaseFirestore firebaseFirestore;

    public featuredAdapter(Context context, ArrayList<FeaturedVenue> featuredVenueArrayList) {
        this.context = context;
        this.featuredVenueArrayList = featuredVenueArrayList;
    }

    @NonNull
    @Override
    public featuredAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.venue,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull featuredAdapter.MyViewHolder holder, int position) {
        FeaturedVenue featuredVenue = featuredVenueArrayList.get(position);

        holder.name.setText(featuredVenue.name);
        holder.location.setText(featuredVenue.city);
        holder.vendorName.setText(featuredVenue.vendorName);
        holder.price.setText(String.valueOf(featuredVenue.price));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,SingleVenue.class);
                intent.putExtra("name",featuredVenue.vendorName);
                intent.putExtra("venue",featuredVenue.name);
                intent.putExtra("price",featuredVenue.price);
                intent.putExtra("location",featuredVenue.city);
                intent.putExtra("id",featuredVenue.getIdV());
                intent.putExtra("isBooked",featuredVenue.getBooked());
                intent.putExtra("venueDoc", featuredVenue.venueDoc.getPath());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return featuredVenueArrayList.size();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView name , price , location , vendorName;

        public MyViewHolder(@NonNull View itemView ) {
            super(itemView);
            name = itemView.findViewById(R.id.venueNameInMyVenue);
            price = itemView.findViewById(R.id.price1);
            location = itemView.findViewById(R.id.location21);
            vendorName = itemView.findViewById(R.id.vendorName);

        }
    }


}
