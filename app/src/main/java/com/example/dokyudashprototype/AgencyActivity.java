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

            Recycling csc = new Recycling("2", "CSC", R.drawable.csc, "Paper", 16.4019, 120.6039);
            recyclingArrayList.add(csc);

            Recycling philhealth = new Recycling("3", "PhilHealth", R.drawable.philhealth, "Paper", 16.412118830329092, 120.6052512635245);
            recyclingArrayList.add(philhealth);

            Recycling lto = new Recycling("4", "LTO", R.drawable.lto, "Plastic", 16.40706795066215, 120.6016630307616);
            recyclingArrayList.add(lto);

            Recycling prc = new Recycling("5", "PRC", R.drawable.prc, "Gl   ass", 16.412881800762634, 120.59226746655595);
            recyclingArrayList.add(prc);

            Recycling psa = new Recycling("6", "PSA", R.drawable.psa, "Glass", 16.40850176521624   , 120.5919845297909);
            recyclingArrayList.add(psa);
            Recycling bir = new Recycling("6", "BIR", R.drawable.bir, "Glass", 16.408678253824103, 120.60102334234585);
            recyclingArrayList.add(bir);
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