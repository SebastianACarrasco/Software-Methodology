package com.example.project5;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

/**
 * class to have the store order activities and deleting an order or viewing current orders
 * @author Rachael Chin, Sebastian Carrasco
 */
public class StoreOrderActivity extends AppCompatActivity{
    private static ArrayList<String> list = new ArrayList<>();

    /**
     * OnCreate method creates the page on the backend and gets all the information and IDs from the front end to
     * make the list view from order basket
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storeorders);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setTitle("Store Orders");
        ListView listView = findViewById(R.id.storeOrderView);

        Intent intent = getIntent();
        list = intent.getStringArrayListExtra("db");

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);
        listView.setAdapter(arrayAdapter);

        //alert dialog for removing items from the order
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            /**
             * Once an item is clicked, it will prompt the user to choose whether or not we want to remove it from the list
             * @param parent
             * @param view
             * @param position
             * @param id
             */
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(StoreOrderActivity.this);
                builder.setMessage("Do you want to remove this order?");
                builder.setTitle("Alert!");

                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    /**
                     * If the user chooses to remove it, we update the dcatabase and let them know it was removed
                     * @param dialog
                     * @param which
                     */
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        list.remove(position);
                        arrayAdapter.notifyDataSetChanged();
                    }
                });

                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    /**
                     * if they did not want to remove it, it will cancel it
                     * @param dialog
                     * @param which
                     */
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

    /**
     * start the activity and set the intent from the main menu
     */
    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, MainActivity.class));
    }
}
