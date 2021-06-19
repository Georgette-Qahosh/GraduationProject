package com.example.graduationproject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ProgressBar;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class viewMyVenues extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<Venue> venueArrayList;
    MyAdapter myAdapter;
    FirebaseFirestore db;
    FirebaseAuth fAuth;
    String userID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_my_venues);
        recyclerView = findViewById(R.id.vendorVenuesList);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        fAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        venueArrayList = new ArrayList<Venue>();
        myAdapter = new MyAdapter(viewMyVenues.this,venueArrayList);
        recyclerView.setAdapter(myAdapter);
        userID = fAuth.getCurrentUser().getUid();
        EventChangeListener();


    }

    private void EventChangeListener() {
        db.collection("users").document(userID).collection("venues").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if (error != null){
                    Log.e("FireStore Error",error.getMessage());
                    return;
                }

                for (DocumentChange dc : value.getDocumentChanges()){
                    if (dc.getType() == DocumentChange.Type.ADDED){
                        venueArrayList.add(dc.getDocument().toObject(Venue.class));
                    }

                    myAdapter.notifyDataSetChanged();

                }



            }
        });
    }




}