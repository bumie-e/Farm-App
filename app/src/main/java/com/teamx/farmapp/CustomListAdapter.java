package com.teamx.farmapp;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomListAdapter extends RecyclerView.Adapter<CustomListAdapter.CustomViewHolder> {

    String[] items;
    int flags[];
    public FragmentManager fragmentManager;
    LayoutInflater layoutInflater;
    FragmentCommunication fragmentCommunication;
    Context context;
    EditDetails editDetails;
    public CustomListAdapter(Context context,  int[] flags, String[] items, FragmentCommunication fragmentCommunication){
        layoutInflater = LayoutInflater.from(context);
        this.flags = flags;
        this.items = items;
        this.fragmentCommunication = fragmentCommunication;
    }
    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CustomViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.img_adapter, parent, false), fragmentCommunication);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        holder.icon.setImageResource(flags[position]);
        holder.name.setText(items[position]);
        EditDetails editDetails = new EditDetails();
        Bundle bundle = new Bundle();
        bundle.putString("itemName", items[position]);
        editDetails.setArguments(bundle);

    }

    @Override
    public int getItemCount() {
        return items.length;
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        CardView cardView;
        TextView name;
        ImageView icon;
        FragmentCommunication communication1;
        public CustomViewHolder(@NonNull View itemView, FragmentCommunication communication) {
            super(itemView);
            cardView = itemView.findViewById(R.id.cardView);
            icon = (ImageView) itemView.findViewById(R.id.imageView2);
            name = (TextView) itemView.findViewById(R.id.textView2);
            communication1 = communication;
            cardView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            communication1.respond(getAdapterPosition(), items[getAdapterPosition()]);
        }

    }
}
