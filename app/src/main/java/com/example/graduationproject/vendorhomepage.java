package com.example.graduationproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class vendorhomepage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendorhomepage);

        Button button = (Button) findViewById(R.id.button);
        ImageView imageView1= (ImageView) findViewById(R.id.imageView131);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent1 = new
                        Intent(vendorhomepage.this,vendorcreatevenue.class);
                vendorhomepage.this.startActivity(intent1);
                finish();

            }

        });
        imageView1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent1 = new
                        Intent(vendorhomepage.this,SingleVenue.class);
                vendorhomepage.this.startActivity(intent1);
                finish();

            }

        });
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);

    }
    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.profile_menu :
                    startActivity(new Intent(vendorhomepage.this, MyProfilePage.class));
                    break;
                case R.id.home_menu :
                    startActivity(new Intent(vendorhomepage.this, vendorhomepage.class));
                    break;
                case R.id.chat_menu :
                    startActivity(new Intent(vendorhomepage.this, customerchat.class));
                    break;
            }
            return false;
        }
    };
}

