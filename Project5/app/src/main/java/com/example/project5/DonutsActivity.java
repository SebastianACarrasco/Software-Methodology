package com.example.project5;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class DonutsActivity extends AppCompatActivity{

    RecyclerView recyclerView;
    String flavors[];
    int images[] = { R.drawable.strawberry, R.drawable.plain, R.drawable.raspberry,
            R.drawable.chocolate, R.drawable.boston, R.drawable.powdered, R.drawable.glazed,
            R.drawable.jelly, R.drawable.butternut, R.drawable.maple,
            R.drawable.rainbow, R.drawable.toasted};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donuts);

        recyclerView = findViewById(R.id.recyclerview);

        flavors = getResources().getStringArray(R.array.donutFlavors);

        MyAdapter myAdapter = new MyAdapter(this, flavors, images);
        recyclerView.setAdapter(myAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }
}
