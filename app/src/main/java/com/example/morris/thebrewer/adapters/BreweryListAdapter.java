package com.example.morris.thebrewer.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.morris.thebrewer.R;
import com.example.morris.thebrewer.models.Brewery;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by morris on 6/11/17.
 */

public class BreweryListAdapter extends RecyclerView.Adapter<BreweryListAdapter.BreweryViewHolder> {
    private ArrayList<Brewery> mBreweries = new ArrayList<>();
    private Context mContext;

    public BreweryListAdapter(Context context, ArrayList<Brewery> breweries) {
        mContext = context;
        mBreweries = breweries;
    }

    @Override
    public BreweryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.brewery_list_item, parent, false);
        BreweryViewHolder viewHolder = new BreweryViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(BreweryListAdapter.BreweryViewHolder holder, int position) {
        holder.bindBrewery(mBreweries.get(position));
    }

    @Override
    public int getItemCount() {
        return mBreweries.size();
    }

    public class BreweryViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.beerNameTextView) TextView mBeerNameTextView;
        @Bind(R.id.organicTextView) TextView mOrganicTextView;

        private Context mContext;

        public BreweryViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();
        }

        public void bindBrewery(Brewery brewery) {
            mBeerNameTextView.setText(brewery.getName());
            mOrganicTextView.setText(brewery.getIsOrganic());
        }

    }
}
