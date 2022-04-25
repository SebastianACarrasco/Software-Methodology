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

public class DonutsActivity extends AppCompatActivity{

    private RecyclerView recyclerView;
    private String flavors[];
    private Donuts donut;
    private static final double YEAST = 1.59;
    private static final double CAKE = 1.79;
    private static final double HOLES = 0.39;
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

        type = (TextView) findViewById(R.id.myText);
        setDonutType(type);
        price = (TextView) findViewById(R.id.myPrice);
        setDonutPrice(price);
        OrderBasketActivity.addToOrder(donut);

        Button submit = findViewById(R.id.addDonutsButton);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DonutsActivity.this, OrderBasketActivity.class);

                Toast toast = Toast.makeText(getApplicationContext(), "Order added", Toast.LENGTH_SHORT);
                toast.show();
                resetOrder();
                startActivity(intent);
            }
        });

    }

    private void resetOrder() {
        donut = new Donuts();
        donutsTotal = 0;
    }

    /**
     * Setter the donut type.
     * @param type
     */
    public void setDonutType(TextView type) {
        this.donutType= type.getText().toString();
    }

    /**
     * Getter for donut type.
     * @return donut type
     */
    public String getDonutType() {
        return donutType;
    }

    /**
     * Setter the donut price.
     * @param price
     */
    public void setDonutPrice(TextView price) {
        this.donutPrice = price.getText().toString();
        String[] result = donutPrice.split("$");
        this.donutsTotal = Double.parseDouble(Arrays.toString(result));
    }

    /**
     * Getter for donut price.
     * @return donut price
     */
    public String getDonutPrice() {
        return donutPrice;
    }


    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, MainActivity.class));
    }


}
