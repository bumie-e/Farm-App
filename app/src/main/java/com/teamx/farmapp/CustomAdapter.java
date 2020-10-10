package com.teamx.farmapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.RVViewHolderClass> {

    Context context;
    int flags[];
    String[] items;
    LayoutInflater layoutInflater;

    public CustomAdapter(Context applicationContext, int[] flags, String[] items){
        this.context = applicationContext;
        this.flags = flags;
        this.items = items;
        layoutInflater = (LayoutInflater.from(applicationContext));
    }


    @NonNull
    @Override
    public CustomAdapter.RVViewHolderClass onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CustomAdapter.RVViewHolderClass(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.img_adapter, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapter.RVViewHolderClass holder, int position) {
        holder.icon.setImageResource(flags[position]);
        holder.name.setText(items[position]);
    }

    @Override
    public int getItemCount() {
        return flags.length;
    }

    public class RVViewHolderClass extends RecyclerView.ViewHolder {
        TextView name;
        ImageView icon;
        public RVViewHolderClass(@NonNull View itemView) {
            super(itemView);
            icon = (ImageView) itemView.findViewById(R.id.imageView2);
            name = (TextView) itemView.findViewById(R.id.textView2);
        }
    }
}
