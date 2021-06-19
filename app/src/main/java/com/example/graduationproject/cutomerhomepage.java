package com.example.graduationproject;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class cutomerhomepage extends AppCompatActivity {
    TextView myName;
    RecyclerView recyclerView;
    ArrayList<FeaturedVenue> venueArrayList;
    featuredAdapter fAdapter;
    FirebaseFirestore db;
    FirebaseAuth fAuth;
    String userID;
    String venueID;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cutomerhomepage);
        myName = findViewById(R.id.myCNAME2);
        recyclerView = findViewById(R.id.searchResults);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        fAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        venueArrayList = new ArrayList<FeaturedVenue>();
        fAdapter = new featuredAdapter(cutomerhomepage.this, venueArrayList);
        recyclerView.setAdapter(fAdapter);
        userID = fAuth.getCurrentUser().getUid();
        EventChangeListener();




        DocumentReference documentReference = db.collection("users").document(userID);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                if(documentSnapshot.exists()){
                    myName.setText(documentSnapshot.getString("name"));


                }
                else {

                    Log.d("tag", "onEvent: Document do not exists");
                }
            }
        });





    }

    private void EventChangeListener() {

        db.collection("featuredVenues").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if (error != null){
                    Log.e("FireStore Error",error.getMessage());
                    return;
                }

                for (DocumentChange dc : value.getDocumentChanges()){
                    if (dc.getType() == DocumentChange.Type.ADDED){
                        venueID = dc.getDocument().getId();
                        FeaturedVenue venue =  dc.getDocument().toObject(FeaturedVenue.class);
                        venue.setIdV(venueID);
                        venueArrayList.add(venue);
                        System.out.println("This works" + venue.getIdV());
                        System.out.println("Test test" + venueID);
                    }

                    fAdapter.notifyDataSetChanged();

                }



            }
        });
    }


}

