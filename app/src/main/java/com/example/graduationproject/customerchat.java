package com.example.graduationproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;


import android.widget.ArrayAdapter;
//import android.widget.Filter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;

public class customerchat extends AppCompatActivity {
    SearchView searchView;
    ListView listView;
    ArrayList<String> list;
    ArrayAdapter<String > adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customerchat);

        searchView = (SearchView) findViewById(R.id.searchViewVenues);
        listView = (ListView) findViewById(R.id.lv1);

        list = new ArrayList<>();
        list.add("Al-shumoua");
        list.add("Ceaser");
        list.add("Flowers");
        list.add("Gloria");
        list.add("Badran");
        list.add("Malouma");


        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,list);
        listView.setAdapter(adapter);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                if(list.contains(query)){
                    adapter.getFilter().filter(query);
                }else{
                    Toast.makeText(customerchat.this, "No Match found",Toast.LENGTH_LONG).show();
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                   adapter.getFilter().filter(newText);
                return false;
            }
        });
    }
}