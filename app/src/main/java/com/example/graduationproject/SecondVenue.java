package com.example.graduationproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class SecondVenue extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_venue);
        TextView maptext = (TextView) findViewById(R.id.textView15);
        maptext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mapsIntent =new Intent();
                mapsIntent.setAction(Intent.ACTION_VIEW);
                mapsIntent.setData(Uri.parse("geo:31.9028846,35.222873"));
                startActivity(mapsIntent);
            }
        });
    }
}