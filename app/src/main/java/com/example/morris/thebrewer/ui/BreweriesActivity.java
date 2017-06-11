package com.example.morris.thebrewer.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.morris.thebrewer.adapters.BreweryListAdapter;
import com.example.morris.thebrewer.services.BrewerService;
import com.example.morris.thebrewer.R;
import com.example.morris.thebrewer.models.Brewery;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;


public class BreweriesActivity extends AppCompatActivity {

    public static final String TAG = BreweriesActivity.class.getSimpleName() ;

    @Bind(R.id.recyclerView) RecyclerView mRecyclerView;
    private BreweryListAdapter mAdapter;

    public ArrayList<Brewery> mBreweries = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_breweries);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");

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
            public void onResponse(Call call, Response response) {
                mBreweries = BrewerService.processResults(response);

                BreweriesActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mAdapter = new BreweryListAdapter(getApplicationContext(), mBreweries);
                        mRecyclerView.setAdapter(mAdapter);
                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(BreweriesActivity.this);
                        mRecyclerView.setLayoutManager(layoutManager);
                        mRecyclerView.setHasFixedSize(true);

                    }
                });


            }
        });
    }
}
