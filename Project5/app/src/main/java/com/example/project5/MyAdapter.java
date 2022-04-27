package com.example.project5;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

/**
 * My Adapter class will connect the back end and front end together to produce the recycler view and add a donut to the DB
 *
 * @author Rachael Chin, Sebastian Carrasco
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    String data[];
    String data2[];
    int images[];
    Context context;

    /**
     * constructor for MyAdapter class
     * @param ct
     * @param donutTypeAndFlavor
     * @param pricing
     * @param img
     */
    public MyAdapter(Context ct, String donutTypeAndFlavor[], String pricing[], int img[]) {
        this.context = ct;
        this.data = donutTypeAndFlavor;
        this.data2 = pricing;
        this.images = img;
    }

    /**
     * Card ViewHolder that extends the parent recylcer view class
     */
    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView myDonut;
        public TextView myPricing;
        public ImageView myImage;

        public Button addDonut;

        private ConstraintLayout parent;

        /**
         * constructor for My View Holder class
         * @param itemView
         */
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            this.myDonut = itemView.findViewById(R.id.myText);
            this.myImage = itemView.findViewById(R.id.myImageView);
            this.myPricing = itemView.findViewById(R.id.myPrice);
            this.addDonut = itemView.findViewById(R.id.addDonutsButton);

            this.parent = itemView.findViewById(R.id.rows);
            myImage.getLayoutParams().height = 400;
            myImage.getLayoutParams().width = 400;


            addDonutOnClick(itemView);
            parent.setOnClickListener(new View.OnClickListener() {

                /**
                 * will look for the intent to see what is clicked
                 * @param view
                 */
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(itemView.getContext(), DonutsActivity.class);
                    intent.putExtra("Item", myDonut.getText());
                    itemView.getContext().startActivity(intent);
                }
            });
        }


        /**
         * private method to add donuts to the order and alert the user accordingly
         * @param itemView
         */
        private void addDonutOnClick(View itemView) {
            addDonut.setOnClickListener(new View.OnClickListener() {
                /**
                 * Alerts the user and asks if they would like to add an order to the cart once it is clicked
                 * @param view
                 */
                @Override
                public void onClick(View view) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(itemView.getContext());
                    builder.setMessage("Do you want to add this to order?");
                    builder.setTitle("Alert!");

                    builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        /**
                         * if we want to add it and yes is clicked, it will make a new donut and do the work on the backend to add it to the DB
                         * @param dialog
                         * @param which
                         */
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(itemView.getContext(), myDonut.getText().toString() + " was added to basket. ",
                                    Toast.LENGTH_LONG).show();
                            String donutDetails[] = myDonut.getText().toString().split("\\s+");
                            String type = donutDetails[0];
                            String flavor = donutDetails[1];

                            int amount = 1;

                            Donuts donut = new Donuts(type, flavor, amount);
                            OrderBasketActivity.addToOrder(donut);
                        }
                    }).setNegativeButton("no", new DialogInterface.OnClickListener() {
                        /**
                         * if the user made a mistake and does not want to add it, it will let them know it was not added to the cart.
                         * @param dialogInterface
                         * @param i
                         */
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Toast.makeText(itemView.getContext(), myDonut.getText().toString()
                                    + " was not added. ", Toast.LENGTH_LONG).show();
                        }
                    });

                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
            });
        }
    }


    /**
     * creates the layout into the parent view
     * @param parent The ViewGroup into which the new View will be added after it is bound to
     *               an adapter position.
     * @param viewType The view type of the new View.
     *
     * @return
     */
    @NonNull
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.donut_flavors_list, parent, false);
        return new MyViewHolder(view);
    }

    /**
     * will set and get the current position of what is being sent to the backend for the donut type, image, and price from the array
     * @param holder The ViewHolder which should be updated to represent the contents of the
     *        item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(@NonNull MyAdapter.MyViewHolder holder, int position) {
        holder.myDonut.setText(data[position]);
        holder.myImage.setImageResource(images[position]);
        holder.myPricing.setText(data2[position]);

    }

    /**
     * @return how many items are there to choose from
     */
    @Override
    public int getItemCount() {
        return images.length;
    }
}
