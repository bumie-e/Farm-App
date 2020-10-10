package com.teamx.farmapp.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.teamx.farmapp.CustomAdapter;
import com.teamx.farmapp.R;

public class HomeFragment extends Fragment {
    RecyclerView recyclerView;
    CustomAdapter adapter;
    ImageView image;
    int count=0;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView = root.findViewById(R.id.rv);
        image = (ImageView)root.findViewById(R.id.imageView8);
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation();
            }
        });

        String[] items = {"Profile","Find Customers", "Sales Summary", "Check Crop Disease"};
        int flags[] = {R.drawable.farmer, R.drawable.customers, R.drawable.money, R.drawable.ic_baseline_local_florist_24};

        adapter = new CustomAdapter(getContext(), flags, items);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        recyclerView.setAdapter(adapter);

        return root;
    }

    public void Animation(){
        if(count==0){
            Animation animation = AnimationUtils.loadAnimation(getContext(),
                    R.anim.clockwise_animation);
            image.startAnimation(animation);
        }
        if(count==1){
            Animation animation1 = AnimationUtils.loadAnimation(getContext(),
                    R.anim.fade_animation);
            image.startAnimation(animation1);
        }
        if(count==2){
            Animation animation1 =
                    AnimationUtils.loadAnimation(getContext(),
                            R.anim.blink_animation);
            image.startAnimation(animation1);
        }
        if(count==3){
            Animation animation1 =
                    AnimationUtils.loadAnimation(getContext(),
                            R.anim.slide_animation);
            image.startAnimation(animation1);
            count=0;
        }
        count++;
    }

}
