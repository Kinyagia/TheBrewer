package com.example.morris.thebrewer.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.morris.thebrewer.Constants;
import com.example.morris.thebrewer.R;
import com.example.morris.thebrewer.models.Brewery;
import com.example.morris.thebrewer.ui.BreweryDetailActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.ArrayList;

/**
 * Created by morris on 6/20/17.
 */

public class FirebaseBreweryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private static final int MAX_WIDTH = 200;
    private static final int MAX_HEIGHT =200;

    View mView;
    Context mContext;

    public FirebaseBreweryViewHolder(View itemView) {
        super(itemView);
        mView = itemView;
        mContext = itemView.getContext();
        itemView.setOnClickListener(this);
    }

    public void bindBrewery(Brewery brewery) {
        ImageView wineImageView = (ImageView) mView.findViewById(R.id.wineImageView);
        TextView nameTextView = (TextView) mView.findViewById(R.id.wineNameTextView);
        TextView varietalTextView = (TextView) mView.findViewById(R.id.varietalTextView);
        Picasso.with(mContext)
                .load(brewery.getImage())
                .resize(MAX_WIDTH, MAX_HEIGHT)
                .centerCrop()
                .into(wineImageView);

        nameTextView.setText(brewery.getName());
        varietalTextView.setText(brewery.getVarietal());

    }

    @Override
    public void onClick(View view) {
        final ArrayList<Brewery> breweries = new ArrayList<>();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_BREWERIES);
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    breweries.add(snapshot.getValue(Brewery.class));
                }

                int itemPosition = getLayoutPosition();

                Intent intent = new Intent(mContext, BreweryDetailActivity.class);
                intent.putExtra("positon", itemPosition + "");
                intent.putExtra("breweries", Parcels.wrap(breweries));

                mContext.startActivity(intent);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
}
