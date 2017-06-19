package com.example.morris.thebrewer.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.morris.thebrewer.R;
import com.example.morris.thebrewer.models.Brewery;
import com.example.morris.thebrewer.ui.BreweryDetailActivity;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by morris on 6/11/17.
 */

public class BreweryListAdapter extends RecyclerView.Adapter<BreweryListAdapter.BreweryViewHolder> {
    private static final int MAX_WIDTH = 200;
    private static final int MAX_HEIGHT = 200;
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

    public class BreweryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @Bind(R.id.wineImageView)    ImageView mWineImageView;
        @Bind(R.id.beerNameTextView) TextView mBeerNameTextView;
        @Bind(R.id.varietalTextView) TextView mVarietalTextView;

        private Context mContext;

        public BreweryViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int itemPosition = getLayoutPosition();
            Intent intent = new Intent(mContext, BreweryDetailActivity.class);
            intent.putExtra("position", itemPosition);
            intent.putExtra("breweries", Parcels.wrap(mBreweries));
            mContext.startActivity(intent);
        }


        public void bindBrewery(Brewery brewery) {
            Picasso.with(mContext)
                    .load(brewery.getImage())
                    .resize(MAX_WIDTH, MAX_HEIGHT)
                    .centerCrop()
                    .into(mWineImageView);
            mBeerNameTextView.setText(brewery.getName());
            mVarietalTextView.setText(brewery.getVarietal());
        }

    }
}
