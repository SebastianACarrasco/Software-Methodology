package com.example.project5;

import android.annotation.SuppressLint;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.ActionBar;
import android.view.View;
import android.widget.CheckBox;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;
import android.content.Intent;
import android.widget.TextView;
import android.widget.Button;

/**
 * This activity displays the coffee order form that the user fills out to customize order.
 * It includes selecting the size and adding toppings.
 *
 * @author Sebastian Carrasco, Rachael Chin
 */
public class CoffeeActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    private String[] sizes = {"short", "tall", "grande", "venti"};
    private String size = "";
    private Coffee coffee;
    private static final double ADDINPRICE = 0.3;
    private double coffeeTotal = 0;
    private TextView text;

    /**
     * This method is called when the user clicks the "Coffee" button from the homepage and opens up the
     * coffee activity.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coffee);
        text = (TextView)findViewById(R.id.coffeeTotalOrder);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Coffee");

        coffee = new Coffee();

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
                Intent intent = new Intent(CoffeeActivity.this, OrderBasketActivity.class);
                OrderBasketActivity.addToOrder(coffee);

                Toast toast = Toast.makeText(getApplicationContext(), "Order added", Toast.LENGTH_SHORT);
                toast.show();
                resetOrder();
                startActivity(intent);
            }
        });
    }

    /**
     * Sets the data for the coffee order object.
     * @param arg0
     * @param arg1
     * @param position
     * @param id
     */
    @Override
    public void onItemSelected(AdapterView arg0, View arg1, int position, long id) {
        size = sizes[position];
        coffee.setSize(size);
        coffeeTotal = coffee.itemPrice();
        printSubTotal();
    }

    /**
     * If nothing is selected, the default size is small.
     * @param arg0
     */
    @Override
    public void onNothingSelected(AdapterView arg0) {}

    /**
     * When the users clicks the back button, it goes back to the home screen.
     */
    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, MainActivity.class));
    }

    /**
     * Resets order upon submission.
     */
    private void resetOrder() {
        coffee = new Coffee();
        coffeeTotal = 0;
        printSubTotal();
    }

    /**
     * Helper method that adds cream to the coffee order and price.
     * @param view
     */
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

    /**
     * Helper method that adds syrup to the coffee order and price.
     * @param view
     */
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

    /**
     * Helper method that adds milk to the coffee order and price.
     * @param view
     */
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

    /**
     * Helper method that adds caramel to the coffee order and price.
     * @param view
     */
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

    /**
     * Helper method that adds whipped cream to the coffee order and price.
     * @param view
     */
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

    /**
     * Prints the subtotal of the coffee order.
     */
    @SuppressLint("DefaultLocale")
    public void printSubTotal() {
        text.setText(String.format("%.2f", this.coffeeTotal));
    }
}
