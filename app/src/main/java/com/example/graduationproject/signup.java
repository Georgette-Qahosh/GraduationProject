package com.example.graduationproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firestore.v1.WriteResult;

import java.security.Key;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class signup extends AppCompatActivity {

    private EditText inputEmail, inputPassword, inputFullName;
    private Button  btnSignUp;
    private RadioButton vendorChoice, customerChoice;
    private FirebaseAuth auth;
    private static final String Key_FullName = "name";
    private static final String Key_Email = "email";
    private static final String Key_Password = "password";
    private static final String Key_Type = "type";
    private static final String Tag= "signup";
    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        //Get Firebase auth instance

        auth = FirebaseAuth.getInstance();
        btnSignUp = (Button) findViewById(R.id.SignUpB);
        inputEmail = (EditText) findViewById(R.id.editSEmail);
        inputPassword = (EditText) findViewById(R.id.editSpassword);
        inputFullName = (EditText) findViewById(R.id.editfullname);
        vendorChoice = (RadioButton) findViewById(R.id.vendorChoice);
        customerChoice = (RadioButton) findViewById(R.id.costumerChoice);
        btnSignUp.setOnClickListener(new View.OnClickListener() {
           @Override
            public void onClick(View v) {
                String email = inputEmail.getText().toString().trim();
                String password = inputPassword.getText().toString().trim();
                String fullName = inputFullName.getText().toString().trim();
                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (password.length() < 6) {
                    Toast.makeText(getApplicationContext(), "Password too short, enter minimum 6 characters!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(fullName)) {
                    Toast.makeText(getApplicationContext(), "Enter your Full Name!", Toast.LENGTH_SHORT).show();
                    return;
                }

                //create user
                auth.createUserWithEmailAndPassword(email, password )
                        .addOnCompleteListener(signup.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                Toast.makeText(signup.this, "You've Joined Us Successfully" + task.isSuccessful(), Toast.LENGTH_SHORT).show();
                                // If sign in fails, display a message to the user. If sign in succeeds
                                // the auth state listener will be notified and logic to handle the
                                // signed in user can be handled in the listener.
                                if (!task.isSuccessful()) {
                                    Toast.makeText(signup.this, "Authentication failed." + task.getException(),
                                            Toast.LENGTH_SHORT).show();
                                } else {
                                    startActivity(new Intent(signup.this, MainActivity.class));
                                    finish();
                                }
                            }
                        });
            }
        });
    }



    public void saveUser (View v) {
        String email = inputEmail.getText().toString().trim();
        String password = inputPassword.getText().toString().trim();
        String fullName = inputFullName.getText().toString().trim();
        Map<String,Object> user = new HashMap<>();
        user.put(Key_FullName,fullName);
        user.put(Key_Email,email);
        user.put(Key_Password,password);
        db.collection("Users").document("First User").set(user)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(signup.this,"UserSaved",Toast.LENGTH_SHORT).show();

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(signup.this,"Error!",Toast.LENGTH_SHORT).show();
                        Log.d(Tag,e.toString());
                    }
                });

    }



}