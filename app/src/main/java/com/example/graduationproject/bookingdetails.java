package com.example.graduationproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

import static android.content.ContentValues.TAG;

public class bookingdetails extends AppCompatActivity {
    TextInputEditText picker;
    TextInputLayout date;

    TextInputLayout cName;
    TextInputLayout cNum;
    TextInputLayout cXDate;
    TextInputLayout cvc;
    Button bookButton;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String userID;
    AutoCompleteTextView autoCompleteTextView;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookingdetails);
        Intent data = getIntent();
        String venueId = data.getStringExtra("id");
        String isBooked = data.getStringExtra("isBooked");
        String path = data.getStringExtra("venuePath");

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        picker = findViewById(R.id.datePicker1);
        bookButton = findViewById(R.id.bookNow);
        cName = findViewById(R.id.cName);
        cNum = findViewById(R.id.cNum);
        cXDate = findViewById(R.id.cXDate);
        cvc = findViewById(R.id.cvc);
        date = findViewById(R.id.datePicker);



        userID=fAuth.getCurrentUser().getUid();

        MaterialDatePicker.Builder builder = MaterialDatePicker.Builder.datePicker();
        builder.setTitleText("Select the booking date");
        final MaterialDatePicker materialDatePicker = builder.build();



        picker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                materialDatePicker.show(getSupportFragmentManager(),"DATE_PICKER");

            }
        });

        materialDatePicker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener() {
            @Override
            public void onPositiveButtonClick(Object selection) {
                date.getEditText().setText(materialDatePicker.getHeaderText());

            }
        });

            bookButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    String n = date.getEditText().getText().toString();
                    String c = cName.getEditText().getText().toString();
                    String cN = cNum.getEditText().getText().toString();
                    String cX = cXDate.getEditText().getText().toString();
                    String cv = cvc.getEditText().getText().toString();
                    Map<String,Object> user = new HashMap<>();
                    user.put("cardNumber",cN);
                    user.put("cvc",cv);
                    user.put("date",n);
                    user.put("expireDate",cX);
                    user.put("nameCard",c);
                    user.put("venueBooked",venueId);
                    fStore.collection("users").document(userID).collection("bookings").document().set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Log.d(TAG, "isBooked "+ userID);
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.d(TAG, "onFailure: " + e.toString());
                        }
                    });

                    fStore.document(path).update("isBooked",true);


                }
            });


    }

}