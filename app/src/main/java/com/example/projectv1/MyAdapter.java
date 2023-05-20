package com.example.projectv1;
//maha

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private Context context;
    private ArrayList item_id, price_id, phone_id, category_id;

    public MyAdapter(Context context, ArrayList item_id, ArrayList price_id, ArrayList phone_id, ArrayList category_id) {
        this.context = context;
        this.item_id = item_id;
        this.price_id = price_id;
        this.phone_id = phone_id;
        this.category_id = category_id;

    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.userentry, parent, false); //userentrey = it's the card view that will show entries

        return new MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.item_id.setText(String.valueOf(item_id.get(position)));
        holder.price_id.setText(String.valueOf(price_id.get(position)));
        holder.phone_id.setText(String.valueOf(phone_id.get(position)));
        holder.category_id.setText(String.valueOf(category_id.get(position)));



    }

    @Override
    public int getItemCount() {
        return item_id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView item_id , price_id , phone_id , category_id ;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            item_id = itemView.findViewById(R.id.itemname);
            price_id = itemView.findViewById(R.id.itemprice);
            phone_id = itemView.findViewById(R.id.phone);
            category_id = itemView.findViewById(R.id.itemcategory);



        }
    }
}


