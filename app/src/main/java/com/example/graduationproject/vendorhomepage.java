package com.example.graduationproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

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


    }
}