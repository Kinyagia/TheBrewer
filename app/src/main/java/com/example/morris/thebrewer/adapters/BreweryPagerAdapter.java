package com.example.morris.thebrewer.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.morris.thebrewer.models.Brewery;
import com.example.morris.thebrewer.ui.BreweryDetailFragment;

import java.util.ArrayList;

/**
 * Created by morris on 6/18/17.
 */

public class BreweryPagerAdapter extends FragmentPagerAdapter {
    private ArrayList<Brewery> mBreweries;

    public BreweryPagerAdapter(FragmentManager fm, ArrayList<Brewery> breweries) {
        super(fm);
        mBreweries = breweries;
    }

    @Override
    public Fragment getItem(int position) {
        return BreweryDetailFragment.newInstance(mBreweries.get(position));
    }

    @Override
    public int getCount() {
        return mBreweries.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mBreweries.get(position).getName();
    }
}
