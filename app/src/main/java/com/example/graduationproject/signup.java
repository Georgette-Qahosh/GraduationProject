package com.example.graduationproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firestore.v1.WriteResult;

import java.security.Key;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static android.content.ContentValues.TAG;

public class signup extends AppCompatActivity {

    TextInputLayout  inputEmail, inputPassword, inputFullName;
    Button  btnSignUp;
    RadioButton vendorChoice, customerChoice;
    FirebaseAuth fAuth;
    ProgressBar regProgressBar;
    FirebaseFirestore fStore;
    TextView alreadySignIn;
    RadioGroup choices;
    String userID;


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

        fAuth = FirebaseAuth.getInstance();
        btnSignUp =  findViewById(R.id.SignUpB);
        inputEmail = findViewById(R.id.editSEmail);
        inputPassword = findViewById(R.id.Spassword);
        inputFullName = findViewById(R.id.editfullname);
        vendorChoice = findViewById(R.id.vendorChoice);
        customerChoice = findViewById(R.id.costumerChoice);
        alreadySignIn = findViewById(R.id.textView23);
        choices=findViewById(R.id.radioGroup);

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        regProgressBar = findViewById(R.id.progressBar2);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
           @Override
            public void onClick(View v) {
                final String email = inputEmail.getEditText().getText().toString().trim();
                String password = inputPassword.getEditText().getText().toString().trim();
                final String fullName = inputFullName.getEditText().getText().toString().trim();
                RadioButton selectedRadioButton  = findViewById(choices.getCheckedRadioButtonId());
                final String yourType = selectedRadioButton.getText().toString().trim();
                System.out.print("first statement. " + yourType);

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


                regProgressBar.setVisibility(View.VISIBLE);

               fAuth.createUserWithEmailAndPassword(email, password )
                        .addOnCompleteListener(signup.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()){
                                    Toast.makeText(signup.this, "You've Joined Us Successfully" + task.isSuccessful(), Toast.LENGTH_SHORT).show();
                                    userID = fAuth.getCurrentUser().getUid();
                                    DocumentReference documentReference = fStore.collection("users").document(userID);
                                            //يحفظ المعلومات لل فاير ستور
                                    Map<String,Object> user = new HashMap<>();
                                    user.put("name",fullName);
                                    user.put("email",email);
                                    user.put("subscription",yourType);
                                    documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                                @Override
                                                public void onSuccess(Void aVoid) {
                                                    Log.d(TAG, "onSuccess: user Profile is created for "+ userID);
                                                }
                                            }).addOnFailureListener(new OnFailureListener() {
                                                @Override
                                                public void onFailure(@NonNull Exception e) {
                                                    Log.d(TAG, "onFailure: " + e.toString());
                                                }
                                            });
                                    // بدء واجهة جديدة بناء على نوع المشترك
                                    if(yourType.equals("Vendor")){
                                        startActivity(new Intent(signup.this, vendorhomepage.class));

                                    }

                                    else if (yourType.equals("Customer")){
                                        startActivity(new Intent(signup.this, cutomerhomepage.class));

                                    }






                                   // startActivity(new Intent(getApplicationContext(),MainActivity.class));
                                }

                                // If sign in fails, display a message to the user. If sign in succeeds
                                // the auth state listener will be notified and logic to handle the
                                // signed in user can be handled in the listener.
                                else if (!task.isSuccessful()) {
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

        alreadySignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),signin.class));
            }
        });
    }



    }



