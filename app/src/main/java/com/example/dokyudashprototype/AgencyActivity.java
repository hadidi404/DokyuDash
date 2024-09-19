package com.example.dokyudashprototype;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AgencyActivity extends AppCompatActivity implements RecycleAdapter.OnItemListener
{
    RecyclerView recyclerView;
    public static ArrayList<Recycling> recyclingArrayList = new ArrayList<Recycling>();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.agency_activity);
        initialiseList();
        setUpRecycler();
    }

    private void initialiseList() {
        // Initialize the list only if it is empty
        if (recyclingArrayList.isEmpty()) {
            Recycling sss = new Recycling("0", "SSS", R.drawable.sss, "Aluminium");
            recyclingArrayList.add(sss);

            Recycling dict = new Recycling("1", "DICT", R.drawable.dict, "Cardboard");
            recyclingArrayList.add(dict);

            Recycling csc = new Recycling("2", "CSC", R.drawable.csc, "Paper");
            recyclingArrayList.add(csc);

            Recycling philhealth = new Recycling("3", "PhilHealth", R.drawable.philhealth, "Paper");
            recyclingArrayList.add(philhealth);

            Recycling lto = new Recycling("4", "LTO", R.drawable.lto, "Plastic");
            recyclingArrayList.add(lto);

            Recycling prc = new Recycling("5", "PRC", R.drawable.prc, "Glass");
            recyclingArrayList.add(prc);

            Recycling psa = new Recycling("6", "PSA", R.drawable.psa, "Glass");
            recyclingArrayList.add(psa);

            Recycling random = new Recycling("6", "PSA", R.drawable.psa, "Glass");
            recyclingArrayList.add(random);

            Recycling fd = new Recycling("6", "PSA", R.drawable.psa, "Glass");
            recyclingArrayList.add(fd);

            Recycling sd = new Recycling("6", "PSA", R.drawable.psa, "Glass");
            recyclingArrayList.add(sd);

            Recycling rew = new Recycling("6", "PSA", R.drawable.psa, "Glass");
            recyclingArrayList.add(rew);

            Recycling as = new Recycling("6", "PSA", R.drawable.psa, "Glass");
            recyclingArrayList.add(as);

            Recycling yt = new Recycling("6", "PSA", R.drawable.psa, "Glass");
            recyclingArrayList.add(yt);

            Recycling hg = new Recycling("6", "PSA", R.drawable.psa, "Glass");
            recyclingArrayList.add(hg);


        }
    }


    private void setUpRecycler()
    {
        recyclerView = (RecyclerView) findViewById(R.id.recycleview);
        RecycleAdapter recycleAdapter = new RecycleAdapter(recyclingArrayList, this);

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(recycleAdapter);

    }

    @Override
    public void onItemClick(int position)
    {
        Intent detailIntent = new Intent(this, DetailActivity.class);
        detailIntent.putExtra("position", position);
        startActivity(detailIntent);
    }
}