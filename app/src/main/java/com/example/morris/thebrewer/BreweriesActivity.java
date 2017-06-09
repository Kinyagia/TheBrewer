package com.example.morris.thebrewer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;


public class BreweriesActivity extends AppCompatActivity {

    public static final String TAG = BreweriesActivity.class.getSimpleName() ;

    @Bind (R.id.nameTextView)TextView mNameTextView;
    @Bind(R.id.listView) ListView mListView;

    public ArrayList<String> mBreweries = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_breweries);
        ButterKnife.bind(this);


        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, mBreweries );
        mListView.setAdapter(adapter);

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        mNameTextView.setText("Here are all the beers: " + name);


        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String brewery = ((TextView)view).getText().toString();
                Toast.makeText(BreweriesActivity.this, brewery, Toast.LENGTH_LONG).show();
            }
        });

        getBreweries(name);
    }

    private void getBreweries(String name) {
        final BrewerService brewerService = new BrewerService();
        brewerService.findBreweries(name, new Callback() {


            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try {
                    String jsonData = response.body().string();
                    Log.v(TAG, jsonData);
                } catch (IOException e){
                    e.printStackTrace();
                }

            }
        });
    }
}
