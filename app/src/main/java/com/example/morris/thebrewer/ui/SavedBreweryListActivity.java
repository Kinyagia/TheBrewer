package com.example.morris.thebrewer.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.morris.thebrewer.Constants;
import com.example.morris.thebrewer.R;
import com.example.morris.thebrewer.adapters.FirebaseBreweryViewHolder;
import com.example.morris.thebrewer.models.Brewery;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SavedBreweryListActivity extends AppCompatActivity {
    private DatabaseReference mBreweryReference;
    private FirebaseRecyclerAdapter mFirebaseAdapter;

    @Bind(R.id.recyclerView) RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_breweries);
        ButterKnife.bind(this);

        mBreweryReference = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_BREWERIES);
        setUpFirebaseAdapter();
    }

    private void setUpFirebaseAdapter() {
        mFirebaseAdapter = new FirebaseRecyclerAdapter<Brewery, FirebaseBreweryViewHolder> (Brewery.class, R.layout.brewery_list_item, FirebaseBreweryViewHolder.class, mBreweryReference) {


            @Override
            protected void populateViewHolder(FirebaseBreweryViewHolder viewHolder, Brewery model, int position) {
                   viewHolder.bindBrewery(model);
            }
        };

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mFirebaseAdapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mFirebaseAdapter.cleanup();
    }
}
