package com.example.graduationproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

import static android.content.ContentValues.TAG;

public class vendorcreatevenue extends AppCompatActivity {
    TextInputLayout name , city , location , price;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    Button addVenue;
    String userID;
    AutoCompleteTextView autoCompleteTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendorcreatevenue);


        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        addVenue=findViewById(R.id.bookNow);
        name = findViewById(R.id.datePicker);
        city = findViewById(R.id.cName);
        location = findViewById(R.id.cNum);
        price = findViewById(R.id.cXDate);
        userID=fAuth.getCurrentUser().getUid();
        autoCompleteTextView = findViewById(R.id.autoCompleteTextView);
        String [] cities ={"Ramallah","Nablus","Hebron","Jenin","Jericho","Gaza" ,"Qalqilyah","Tulkarm","Khan Yunis","Tubas"};
        ArrayAdapter arrayAdapter = new ArrayAdapter(this,R.layout.cities,cities);
        autoCompleteTextView.setText(arrayAdapter.getItem(0).toString(),false);
        autoCompleteTextView.setAdapter(arrayAdapter);


      /*  addVenue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(name.getEditText().getText().toString().isEmpty() && city.getEditText().getText().toString().isEmpty() &&
                        location.getEditText().getText().toString().isEmpty() && price.getEditText().getText().toString().isEmpty()){
                    Toast.makeText(vendorcreatevenue.this,"One or Many Empty Fields are Empty.",Toast.LENGTH_SHORT);
                    return;

                }

                final String n = name.getEditText().getText().toString();
                final String c = city.getEditText().getText().toString();
                final String l = location.getEditText().getText().toString();
                final String p = price.getEditText().getText().toString();
                DocumentReference documentReference = fStore.collection("users").document(userID).collection("venues").document("venue");
                Map<String,Object> user = new HashMap<>();
                user.put("name",n);
                user.put("city",c);
                user.put("location",l);
                user.put("price",p);
                documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(TAG, "onSuccess: Venue is created for "+ userID);
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d(TAG, "onFailure: " + e.toString());
                    }
                });
            }
        });
        // Store email into the text edit box
*/
    }
}