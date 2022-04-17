package com.example.project5;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.ImageButton;

import java.io.Serializable;

/**
 * Home screen for the app that allows the user to navigate to the other activities.
 *
 * @author Sebastian Carrasco, Rachael Chin
 */
public class MainActivity extends AppCompatActivity {
    private Order order;

    /**
     * Creates the activity on the screen
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setTitle("Main Menu");
        this.order = new Order();

        //donuts
        ImageButton donuts = findViewById(R.id.homeDonuts);

        //coffee
        ImageButton coffee = findViewById(R.id.homeCoffee);
        coffee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CoffeeActivity.class);
                //intent.putExtra("order", order);
                startActivity(intent);
            }
        });

        //basket
        ImageButton orderBasket = findViewById(R.id.imageButton4);
        orderBasket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, OrderBasketActivity.class);
                //intent.putExtra("order", order);
                startActivity(intent);
            }
        });

        //store order
        ImageButton storeOrder = findViewById(R.id.imageButton5);
        storeOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, StoreOrderActivity.class);
                //intent.putExtra("order", order);
                startActivity(intent);
            }
        });
    }
}