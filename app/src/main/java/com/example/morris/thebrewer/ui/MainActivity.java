package com.example.morris.thebrewer.ui;

import android.content.Intent;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.morris.thebrewer.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @Bind(R.id.findBreweryButton) Button mFindBreweryButton;
    @Bind(R.id.nameEditText) EditText mNameEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mFindBreweryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = mNameEditText.getText().toString();
                Intent intent = new Intent(MainActivity.this, BreweriesListActivity.class);
                intent.putExtra("name", name);
                startActivity(intent);


            }
        });




    }


}
