package com.example.graduationproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class EditProfileInfo extends AppCompatActivity {
    TextInputLayout editEmail , editPass;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    Button editButton;
    FirebaseUser user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile_info);

        Intent data = getIntent();
        String email = data.getStringExtra("email");
        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        user = fAuth.getCurrentUser();
        editButton=findViewById(R.id.bookNow);
        editEmail= findViewById(R.id.datePicker);
        // Store email into the text edit box
        editEmail.getEditText().setText(email);
        editPass=findViewById(R.id.cName);

        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editEmail.getEditText().getText().toString().isEmpty() || editPass.getEditText().getText().toString().isEmpty() ){
                    Toast.makeText(EditProfileInfo.this,"One or Many Empty Fields are Empty.",Toast.LENGTH_SHORT);
                    return;
                }

                final String newEmail = editEmail.getEditText().getText().toString();
                final String newPass = editPass.getEditText().getText().toString();
                user.updateEmail(newEmail).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        DocumentReference documentReference = fStore.collection("users").document(user.getUid());
                        Map<String,Object> edited = new HashMap<>();
                        documentReference.update(edited).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(EditProfileInfo.this,"Data Updated", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(),MyProfilePage.class));
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(EditProfileInfo.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
                        Toast.makeText(EditProfileInfo.this,"Your Information  is Changed", Toast.LENGTH_SHORT).show();


                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(EditProfileInfo.this,e.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                });

                user.updatePassword(newPass).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(EditProfileInfo.this,"Password is Updated",Toast.LENGTH_SHORT);

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(EditProfileInfo.this, e.getMessage(),Toast.LENGTH_SHORT);

                    }
                });

            }
        });

    }
}