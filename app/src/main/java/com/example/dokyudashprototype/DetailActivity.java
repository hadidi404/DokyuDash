package com.example.dokyudashprototype;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DetailActivity extends AppCompatActivity
{
    private ImageView recycleImage;
    private TextView recycleText;

    Recycling selectedRecycling;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        setUpViews();
        getRecycling();
    }

    private void setUpViews()
    {
        recycleImage = (ImageView) findViewById(R.id.recycleDetailImage);
        recycleText = (TextView) findViewById(R.id.recycleDetailText);
    }

    private void getRecycling()
    {
        Intent prevIntent = getIntent();
        int position = prevIntent.getIntExtra("position", 0);
        selectedRecycling = AgencyActivity.recyclingArrayList.get(position);
        recycleImage.setImageResource(selectedRecycling.getImage());
        recycleText.setText(selectedRecycling.getName());
    }
}