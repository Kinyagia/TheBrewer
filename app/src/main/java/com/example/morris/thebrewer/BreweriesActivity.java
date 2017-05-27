package com.example.morris.thebrewer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;



public class BreweriesActivity extends AppCompatActivity {
    private TextView mLocationTextView;
    private ListView mListView;
    private String[] breweries = new String[] {"Sirville", "Brew Bistro", "Draught", "Test House", "Bavaria", "BratWurst", "CurryWurst", "Kartoffel", "Wiener"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_breweries);

        mLocationTextView = (TextView) findViewById(R.id.locationTextView);
        mListView = (ListView) findViewById(R.id.listView);

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, breweries);
        mListView.setAdapter(adapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String brewery = ((TextView)view).getText().toString();
                Toast.makeText(BreweriesActivity.this, brewery, Toast.LENGTH_LONG).show();
            }
        });

        Intent intent = getIntent();
        String location = intent.getStringExtra("location");
        mLocationTextView.setText("Here are all the Breweries in " + location);
    }
}
