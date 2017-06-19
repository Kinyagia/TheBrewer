package com.example.morris.thebrewer.ui;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.morris.thebrewer.R;
import com.example.morris.thebrewer.models.Brewery;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import butterknife.Bind;
import butterknife.ButterKnife;

public class BreweryDetailFragment extends Fragment implements  View.OnClickListener {
    private static final int MAX_WIDTH = 400;
    private static final int MAX_HEIGHT = 300;

    @Bind(R.id.wineImageView)
    ImageView mImageLabel;
    @Bind(R.id.wineNameTextView)
    TextView mNameLabel;
    @Bind(R.id.varietalTextView)
    TextView mVarietalLabel;
    @Bind(R.id.wineTypeTextView)
    TextView mTypeLabel;
    @Bind(R.id.linkTextView)
    TextView mLinkLabel;

    private Brewery mBrewery;


    public static BreweryDetailFragment newInstance(Brewery brewery) {
        BreweryDetailFragment breweryDetailFragment = new BreweryDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable("brewery", Parcels.wrap(brewery));
        breweryDetailFragment.setArguments(args);
        return breweryDetailFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBrewery = Parcels.unwrap(getArguments().getParcelable("brewery"));
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_brewery_detail, container, false);

        ButterKnife.bind(this, view);
        mLinkLabel.setOnClickListener(this);

        Picasso.with(view.getContext())
                .load(mBrewery.getImage())
                .resize(MAX_WIDTH, MAX_HEIGHT)
                .into(mImageLabel);
        mNameLabel.setText(mBrewery.getName());
        mVarietalLabel.setText(mBrewery.getVarietal());
        mTypeLabel.setText(mBrewery.getType());
        mLinkLabel.setText(mBrewery.getLink());
        return view;
    }

    @Override
    public void onClick(View v) {
        if (v == mLinkLabel) {
            Intent webIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(mBrewery.getLink()));
            startActivity(webIntent);
        }
    }

}
