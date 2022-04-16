package com.example.project5;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * Opens donut ordering when clicked
     */
    public void donutsButton(View view) {
        //Intent intent = new Intent(this, DonutsActivity.class);
        //startActivity(intent);
    }
    /**
     * Opens coffee ordering when clicked
     */
    public void coffeeButton(View view) {
        //Intent intent = new Intent(this, CoffeeActivity.class);
        //startActivity(intent);
    }
    /**
     * Opens order basket when clicked
     */
    public void orderBasket(View view) {
        Intent intent = new Intent(this, orderBasketActivity.class);
        startActivity(intent);
    }
    /**
     * Opens store orders when clicked
     */
    public void storeOrders(View view) {
        //Intent intent = new Intent(this, StoreordersActivity.class);
        //startActivity(intent);
    }
}