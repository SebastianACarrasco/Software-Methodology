package com.example.project5;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.ActionBar;
import android.widget.Button;
import android.content.Intent;

public class OrderBasketActivity extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orderbasket);

        ActionBar actionbar = getSupportActionBar();
        actionbar.setTitle("Order Basket");

        Intent intent = getIntent();

        TextView subtotal = findViewById(R.id.subtotal);
        TextView tax = findViewById(R.id.tax);
        TextView total = findViewById(R.id.totalWithTax);

        Button submit = findViewById(R.id.submitOrder);

        ListView listView = findViewById(R.id.listViewOrder);
    }
}
