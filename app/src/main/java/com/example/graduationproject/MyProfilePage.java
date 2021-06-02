package com.example.graduationproject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class MyProfilePage extends AppCompatActivity {
    TextView fullName , email , subscriptionType;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile_page);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);
        fullName = findViewById(R.id.nameOnProfilePage);
        email = findViewById(R.id.emailOnProfilePage);
        subscriptionType = findViewById(R.id.subscriptionTextView);
        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        userID = fAuth.getCurrentUser().getUid();

        DocumentReference documentReference = fStore.collection("users").document(userID);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                if(documentSnapshot.exists()){
                    fullName.setText(documentSnapshot.getString("name"));
                    email.setText(documentSnapshot.getString("email"));
                    subscriptionType.setText(documentSnapshot.getString("subscription"));

                }
                else {

                    Log.d("tag", "onEvent: Document do not exists");
                }
            }
        });


    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.profile_menu :
                    startActivity(new Intent(MyProfilePage.this, MyProfilePage.class));

                    break;
                case R.id.home_menu :
                    startActivity(new Intent(MyProfilePage.this, cutomerhomepage.class));
                    break;
                case R.id.chat_menu :
                    startActivity(new Intent(MyProfilePage.this, customerchat.class));
                    break;
            }
            return false;
        }
    };


    public void goToEditPage (View view){
        Intent i = new Intent(view.getContext(),EditProfileInfo.class);
        i.putExtra("email",email.getText().toString());
        startActivity(i);

    }
    public void logout (View view){
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(getApplicationContext(),signin.class));
        finish();

    }
}