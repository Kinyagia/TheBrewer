package com.example.morris.thebrewer.ui;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.morris.thebrewer.R;
import com.example.morris.thebrewer.adapters.BreweryPagerAdapter;
import com.example.morris.thebrewer.models.Brewery;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class BreweryDetailActivity extends AppCompatActivity {
    @Bind(R.id.viewPager)
    ViewPager mViewPager;
    private BreweryPagerAdapter adapterViewPager;
    ArrayList<Brewery> mBreweries = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brewery_detail);
        ButterKnife.bind(this);

        mBreweries = Parcels.unwrap(getIntent().getParcelableExtra("breweries"));
        int startingPosition = getIntent().getIntExtra("position", 0);
        adapterViewPager  = new BreweryPagerAdapter(getSupportFragmentManager(), mBreweries);
        mViewPager.setAdapter(adapterViewPager);
        mViewPager.setCurrentItem(startingPosition);
    }
}
