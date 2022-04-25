package com.example.project5;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    String data[];
    String data2[];
    int images[];
    Context context;

    public MyAdapter(Context ct, String donutTypeAndFlavor[], String pricing[], int img[]){
        context = ct;
        data = donutTypeAndFlavor;
        data2 = pricing;
        images = img;
    }
    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView myText;
        TextView myPricing;
        ImageView myImage;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            myText = itemView.findViewById(R.id.myText);
            myImage = itemView.findViewById(R.id.myImageView);
            myPricing = itemView.findViewById(R.id.myPrice);

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

        holder.myText.setText(data[position]);
        holder.myImage.setImageResource(images[position]);
        holder.myPricing.setText(data[position]);

    }

    @Override
    public int getItemCount() {
        return images.length;
    }
}
