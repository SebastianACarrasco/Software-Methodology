package com.example.project5;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import org.w3c.dom.Text;

import java.util.Arrays;

/**
 *Class for creating donut orders. There will be donut type, flavor, and quantity.
 *
 *@author Sebastian Carrasco, Rachael Chin
 */
public class DonutsActivity extends AppCompatActivity{

    private RecyclerView recyclerView;
    private String flavors[];
    private Donuts donut;
    private double donutsTotal = 0;
    private String donutType;
    private TextView type;
    private TextView price;
    private String donutPrice;
    private String prices[];
    int images[] = { R.drawable.strawberry, R.drawable.plain,
            R.drawable.chocolate, R.drawable.raspberry, R.drawable.boston, R.drawable.powdered, R.drawable.glazed,
            R.drawable.jelly, R.drawable.butternut, R.drawable.maple,
            R.drawable.rainbow, R.drawable.toasted};

    /**
     * will create a recylcer view and get all the necessary information
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donuts);
        donut = new Donuts();

        recyclerView = findViewById(R.id.recyclerview);
        flavors = getResources().getStringArray(R.array.donutFlavors);
        prices = getResources().getStringArray(R.array.donutPrices);
        MyAdapter myAdapter = new MyAdapter(this, flavors, prices, images);
        recyclerView.setAdapter(myAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    /**
     * start the activity and intent based on the main menu
     */
    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, MainActivity.class));
    }


}
