package com.example.dokyudashprototype;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

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
        Toast.makeText(this, "Agency Page", Toast.LENGTH_SHORT).show();

    }

    private void initialiseList() {
        if (recyclingArrayList.isEmpty()) {
            Recycling sss = new Recycling("0", "SSS", R.drawable.sss, "Aluminium", 16.4109188193224, 120.59767716519849 );
            recyclingArrayList.add(sss);

            Recycling dict = new Recycling("1", "DICT", R.drawable.dict, "Cardboard", 16.41681332085707, 120.6136197558936);
            recyclingArrayList.add(dict);

            Recycling csc = new Recycling("2", "CSC", R.drawable.csc, "Paper", 16.402355632725335, 120.60385691280936);
            recyclingArrayList.add(csc);

            Recycling philhealth = new Recycling("3", "PhilHealth", R.drawable.philhealth, "Paper", 16.41218581798061, 120.60524210809584);
            recyclingArrayList.add(philhealth);

            Recycling lto = new Recycling("4", "LTO", R.drawable.lto, "Plastic", 16.4080, 120.5990);
            recyclingArrayList.add(lto);

            Recycling prc = new Recycling("5", "PRC", R.drawable.prc, "Glass", 16.4120, 120.6005);
            recyclingArrayList.add(prc);

            Recycling psa = new Recycling("6", "PSA", R.drawable.psa, "Glass", 16.4115, 120.5955);
            recyclingArrayList.add(psa);

            Recycling random = new Recycling("7", "Random Agency 1", R.drawable.psa, "Glass", 16.4090, 120.5940);
            recyclingArrayList.add(random);

            Recycling fd = new Recycling("8", "Random Agency 2", R.drawable.psa, "Glass", 16.4105, 120.5935);
            recyclingArrayList.add(fd);

            Recycling sd = new Recycling("9", "Random Agency 3", R.drawable.psa, "Glass", 16.4130, 120.5945);
            recyclingArrayList.add(sd);

            Recycling rew = new Recycling("10", "Random Agency 4", R.drawable.psa, "Glass", 16.4075, 120.5950);
            recyclingArrayList.add(rew);

            Recycling as = new Recycling("11", "Random Agency 5", R.drawable.psa, "Glass", 16.4060, 120.5975);
            recyclingArrayList.add(as);

            Recycling yt = new Recycling("12", "Random Agency 6", R.drawable.psa, "Glass", 16.4085, 120.5980);
            recyclingArrayList.add(yt);

            Recycling hg = new Recycling("13", "Random Agency 7", R.drawable.psa, "Glass", 16.4100, 120.5965);
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
    public void onItemClick(String name) {
        // Find the clicked agency
        Recycling selectedAgency = null;
        for (Recycling agency : recyclingArrayList) {
            if (agency.getName().equals(name)) {
                selectedAgency = agency;
                break;
            }
        }

        if (selectedAgency != null) {
            Intent intent = new Intent(this, MapsActivity.class);
            intent.putExtra("name", selectedAgency.getName());
            intent.putExtra("latitude", selectedAgency.getLatitude());
            intent.putExtra("longitude", selectedAgency.getLongitude());
            intent.putExtra("logo", selectedAgency.getImage());
            startActivity(intent);
        }
    }



}