package com.example.graduationproject;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class SingleVenue extends AppCompatActivity {
    TextView price , textVendor , venueName , clickToMap;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String userID;
    String venueID;
    Button button1 , itIsBooked;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_venue);
        button1 = findViewById(R.id.bookNow);
        itIsBooked = findViewById(R.id.itIsBooked);
        price = findViewById(R.id.textpriiice);
        textVendor = findViewById(R.id.allll);
        venueName = findViewById(R.id.venueeeName);
        clickToMap = findViewById(R.id.clickhereToviewMap);
        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        Intent data = getIntent();
        String vendorName = data.getStringExtra("name");
        String venuePrice = data.getStringExtra("price");
        String venueLocation = data.getStringExtra("location");
        String id = data.getStringExtra("id");
        String venueDoc = data.getStringExtra("venueDoc");
        Boolean isBooked = data.getExtras().getBoolean("isBooked");
        System.out.println("vendorName" + vendorName);
        System.out.println("venueName" + venueName);
        System.out.println("venuePrice" + venuePrice);
        System.out.println("venueLocation" + venueLocation);
        System.out.println("id" + id);

        if (isBooked==true){

            button1.setVisibility(View.GONE);
            itIsBooked.setVisibility(View.VISIBLE);

        } else if (isBooked==false){
            button1.setVisibility(View.VISIBLE);
            itIsBooked.setVisibility(View.GONE);
        }



        DocumentReference documentReference = fStore.document(venueDoc);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                if(documentSnapshot.exists()){
                    textVendor.setText(documentSnapshot.getString("vendorName"));
                    venueName.setText(documentSnapshot.getString("name"));

                }
                else {

                    Log.d("tag", "onEvent: Document do not exists");
                }
            }
        });

        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent i = new Intent(SingleVenue.this,bookingdetails.class);
                i.putExtra("path",venueDoc);
                i.putExtra("id",id);
                i.putExtra("venuePath",venueDoc);
                i.putExtra("isBooked",isBooked);
                startActivity(i);

            }
        });

        clickToMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mapsIntent =new Intent();
                mapsIntent.setAction(Intent.ACTION_VIEW);
                mapsIntent.setData(Uri.parse("geo:31.888939,35.2005626"));
                startActivity(mapsIntent);
            }
        });



    }
}



