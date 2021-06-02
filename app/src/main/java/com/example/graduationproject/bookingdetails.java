package com.example.graduationproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class bookingdetails extends AppCompatActivity {
    public static final String BookingDate_Key="date";
    public static final String CardName_Key="nameCard";
    public static final String CardNumber_Key="cardNumber";
    public static final String ExpireDate_Key="expireDate";
    public static final String cvc_Key="cvc";
    public static final String Tag="BookingDetail";

    private DocumentReference mDocRef = FirebaseFirestore.getInstance().document("bookings/bookingSample");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookingdetails);

    }
    public void saveBooking (View v) {
        TextInputLayout BookingDate = (TextInputLayout) findViewById(R.id.editedemail);
        TextInputLayout NameCard = (TextInputLayout) findViewById(R.id.editedPassword);
        TextInputLayout NumberOfCard = (TextInputLayout) findViewById(R.id.cardNumber);
        TextInputLayout ExpireDate = (TextInputLayout) findViewById(R.id.cardExpireDate);
        TextInputLayout CVC = (TextInputLayout) findViewById(R.id.cardCVC);
        String bookDate = BookingDate.getEditText().getText().toString().trim();
        String cardName = NameCard.getEditText().getText().toString().trim();
        String cardNum = NumberOfCard.getEditText().getText().toString().trim();
        String expDate = ExpireDate.getEditText().getText().toString().trim();
        String cvcNum = CVC.getEditText().getText().toString().trim();

        Map<String,Object> booking = new HashMap<>();
        booking.put(BookingDate_Key,bookDate);
        booking.put(CardName_Key,cardName);
        booking.put(CardNumber_Key, cardNum);
        booking.put(ExpireDate_Key, expDate);
        booking.put(cvc_Key, cvcNum);
        mDocRef.set(booking).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Log.d(Tag,"Document has been added!");

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.w(Tag,"Document was not saved!",e);

            }
        });


    }
}