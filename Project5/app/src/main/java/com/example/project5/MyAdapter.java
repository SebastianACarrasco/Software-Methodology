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

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    String data[];
    String data2[];
    int images[];
    Context context;

    public MyAdapter(Context ct, String donutTypeAndFlavor[], String pricing[], int img[]) {
        this.context = ct;
        this.data = donutTypeAndFlavor;
        this.data2 = pricing;
        this.images = img;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView myDonut;
        public TextView myPricing;
        public ImageView myImage;

        public Button addDonut;

        private ConstraintLayout parent;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            this.myDonut = itemView.findViewById(R.id.myText);
            this.myImage = itemView.findViewById(R.id.myImageView);
            this.myPricing = itemView.findViewById(R.id.myPrice);
            this.addDonut = itemView.findViewById(R.id.addDonutsButton);
            this.parent = itemView.findViewById(R.id.recyclerview);
            myImage.getLayoutParams().height = 300;
            myImage.getLayoutParams().width = 300;

            addDonutOnClick(itemView);
            parent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(itemView.getContext(), DonutsActivity.class);
                    intent.putExtra("Item", myDonut.getText());
                    itemView.getContext().startActivity(intent);
                }
            });
        }


        private void addDonutOnClick(View itemView) {
            addDonut.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(itemView.getContext());
                    builder.setMessage("Do you want to add this to order?");
                    builder.setTitle("Alert!");

                    builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(itemView.getContext(), myDonut.getText().toString() + "was wadded to basket.",
                                    Toast.LENGTH_LONG).show();
                            String donutDetails[] = myDonut.getText().toString().split("\\s+");
                            String type = donutDetails[0];
                            String flavor = donutDetails[1];

                            int amount = 1;

                            Donuts donut = new Donuts(type, flavor, amount);
                            OrderBasketActivity.addToOrder(donut);
                        }
                    }).setNegativeButton("no", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Toast.makeText(itemView.getContext(), myDonut.getText().toString()
                                    + "was not added.", Toast.LENGTH_LONG).show();
                        }
                    });

                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
            });
        }
    }


    @NonNull
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.donut_flavors_list, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.MyViewHolder holder, int position) {
        holder.myDonut.setText(data[position]);
        holder.myImage.setImageResource(images[position]);
        holder.myPricing.setText(data2[position]);

    }

    @Override
    public int getItemCount() {
        return images.length;
    }
}
