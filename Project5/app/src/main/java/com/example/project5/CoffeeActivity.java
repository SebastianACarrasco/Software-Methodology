package com.example.project5;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.ActionBar;
import android.view.View;
import android.widget.CheckBox;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.content.Intent;
import android.widget.TextView;
import android.widget.Button;
import java.util.ArrayList;

import java.io.Serializable;


public class CoffeeActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    private String[] sizes = {"short", "tall", "grande", "venti"};
    private String size = "";
    private Coffee coffee;
    private static final double ADDINPRICE = 0.3;
    private double coffeeTotal = 0;
    TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coffee);
        text = (TextView)findViewById(R.id.coffeeTotalOrder);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Coffee");

        coffee = new Coffee();
        Intent intent = getIntent();
        //Order order = intent.getParcelableExtra("order");

        Spinner spino = findViewById(R.id.coffeeSize);
        spino.setOnItemSelectedListener(this);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, sizes);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spino.setAdapter(adapter);

        CheckBox creamBox = (CheckBox) findViewById(R.id.cream);
        CheckBox syrupBox = (CheckBox) findViewById(R.id.syrup);
        CheckBox milkBox = (CheckBox) findViewById(R.id.milk);
        CheckBox caramelBox = (CheckBox) findViewById(R.id.caramel);
        CheckBox whippedCreamBox = (CheckBox) findViewById(R.id.whippedcream);

        creamBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cream(v);
            }
        });

        syrupBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                syrup(v);
            }
        });

        milkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                milk(v);
            }
        });

        caramelBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                caramel(v);
            }
        });

        whippedCreamBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                whippedCream(v);
            }
        });



        Button submit = findViewById(R.id.addCoffeeButton);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String size = coffee.getSize();
                String cost = coffeeTotal + "";
                ArrayList<String> toppings = coffee.getAddIns();

                Intent intent = new Intent(CoffeeActivity.this, OrderBasketActivity.class);
                intent.putExtra("size", size);
                intent.putExtra("cost", cost);
                intent.putExtra("toppings", toppings);
                startActivity(intent);

                Toast toast = Toast.makeText(getApplicationContext(), "Order submitted", Toast.LENGTH_LONG);
                toast.show();
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView arg0, View arg1, int position, long id) {
        //Toast.makeText(getApplicationContext(), sizes[position], Toast.LENGTH_LONG).show();
        size = sizes[position];
        coffee.setSize(size);
        coffeeTotal = coffee.itemPrice();
        printSubTotal();
    }

    @Override
    public void onNothingSelected(AdapterView arg0) {}

    public void cream(View view) {
        if (view.getId() == R.id.cream && ((CheckBox) view).isChecked()) {
            coffee.add("Cream");
            coffeeTotal += ADDINPRICE;
        } else {
            coffee.remove("Cream");
            coffeeTotal -= ADDINPRICE;
        }
        printSubTotal();
    }

    public void syrup(View view) {
        if (view.getId() == R.id.syrup && ((CheckBox) view).isChecked()) {
            coffee.add("Syrup");
            coffeeTotal += ADDINPRICE;
        } else {
            coffee.remove("Syrup");
            coffeeTotal -= ADDINPRICE;
        }
        printSubTotal();
    }

    public void milk(View view) {
        if (view.getId() == R.id.milk && ((CheckBox) view).isChecked()) {
            coffee.add("Milk");
            coffeeTotal += ADDINPRICE;
        } else {
            coffee.remove("Milk");
            coffeeTotal -= ADDINPRICE;
        }
        printSubTotal();
    }

    public void caramel(View view) {
        if (view.getId() == R.id.caramel && ((CheckBox) view).isChecked()) {
            coffee.add("Caramel");
            coffeeTotal += ADDINPRICE;
        } else {
            coffee.remove("Caramel");
            coffeeTotal -= ADDINPRICE;
        }
        printSubTotal();
    }

    public void whippedCream(View view) {
        if (view.getId() == R.id.whippedcream && ((CheckBox) view).isChecked()) {
            coffee.add("WhippedCream");
            coffeeTotal += ADDINPRICE;
        } else {
            coffee.remove("WhippedCream");
            coffeeTotal -= ADDINPRICE;
        }
        printSubTotal();
    }

    public void printSubTotal() {
        text.setText(String.format("%.2f", this.coffeeTotal));
    }
}
