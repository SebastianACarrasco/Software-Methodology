package com.example.project5;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.ActionBar;
import android.content.Intent;
import android.view.View;
import androidx.appcompat.app.AlertDialog;
import android.content.DialogInterface;
import androidx.core.app.ActivityCompat;

import java.util.ArrayList;

public class OrderBasketActivity extends AppCompatActivity{
    private static Order order = new Order();
    private static ArrayList<String> orderList = new ArrayList<>();
    //private static ArrayList<String> storeOrders = new ArrayList<>();

    @SuppressLint("DefaultLocale")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orderbasket);

        ActionBar actionbar = getSupportActionBar();
        actionbar.setTitle("Order Basket");

        TextView subtotal = findViewById(R.id.subtotal);
        TextView tax = findViewById(R.id.tax);
        TextView totalWTax = findViewById(R.id.totalWithTax);
        Button submit = findViewById(R.id.submitOrder);
        ListView listView = findViewById(R.id.listViewOrder);

        subtotal.setText(String.format("%.2f", this.order.subTotal()));
        tax.setText(String.format("%.2f", this.order.getTaxes()));
        totalWTax.setText(String.format("%.2f", this.order.subTotalWithTax()));

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, orderList);
        listView.setAdapter(arrayAdapter);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(arrayAdapter.getCount() != 0) {
                    Intent intent = new Intent(OrderBasketActivity.this, StoreOrderActivity.class);
                    //storeOrders.add(order.toString());
                    //intent.putExtra("orderList", storeOrders);
                    MainActivity.addToDB(order.toString());
                    Toast toast = Toast.makeText(getApplicationContext(), "Order placed!", Toast.LENGTH_LONG);
                    for (int i = 0; i < orderList.size(); i++) {
                        orderList.remove(i);
                    }
                    for(int i = 0; i < listView.getChildCount(); i++) {
                        listView.getChildAt(i).setVisibility(View.GONE);
                    }
                    order = new Order();
                    arrayAdapter.notifyDataSetChanged();
                    subtotal.setText("0.00");
                    tax.setText("0.00");
                    totalWTax.setText("0.00");
                    toast.show();
                    //startActivity(intent);
                } else {
                    Toast toast = Toast.makeText(getApplicationContext(), "Please add an item to your order!", Toast.LENGTH_LONG);
                    toast.show();
                }
            }
            });

        //alert dialog for removing items from the order
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(OrderBasketActivity.this);
                builder.setMessage("Do you want to remove this item?");
                builder.setTitle("Alert!");

                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        orderList.remove(position);
                        order.removeItem(order.getItems());
                        arrayAdapter.notifyDataSetChanged();
                        subtotal.setText(String.format("%.2f", order.subTotal()));
                        tax.setText(String.format("%.2f", order.getTaxes()));
                        totalWTax.setText(String.format("%.2f", order.subTotalWithTax()));
                    }
                });

                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, MainActivity.class));
    }

    public static void addToOrder(MenuItem item) {
        order.add(item);
        orderList.add(item.toString());
    }

}
