package com.teamx.farmapp.ui.gallery;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.teamx.farmapp.CustomAdapter;
import com.teamx.farmapp.CustomListAdapter;
import com.teamx.farmapp.EditDetails;
import com.teamx.farmapp.FragmentCommunication;
import com.teamx.farmapp.R;

public class GalleryFragment extends Fragment {

    private GalleryViewModel galleryViewModel;
    RecyclerView recyclerView;
    CustomListAdapter adapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        galleryViewModel =
                ViewModelProviders.of(this).get(GalleryViewModel.class);
        View root = inflater.inflate(R.layout.fragment_gallery, container, false);
        recyclerView = root.findViewById(R.id.recyclerView);

        String[] items = {"Beans","Rice", "Potato", "Cassava"};
        int flags[] = {R.drawable.plant, R.drawable.plant, R.drawable.plant, R.drawable.plant};

        adapter = new CustomListAdapter(getContext(), flags, items, communication);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        recyclerView.setAdapter(adapter);
       /* adapter = new CustomAdapter(getContext(), flags, items);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        recyclerView.setAdapter(adapter);*/

        return root;
    }


    FragmentCommunication communication = new FragmentCommunication() {
        @Override
        public void respond(int position, String itemname) {
            EditDetails details = new EditDetails();
            Bundle bundle = new Bundle();
            bundle.putString("itemName", itemname);
            details.setArguments(bundle);
            FragmentManager manager = getFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.replace(R.id.nav_host_fragment, details).commit();
            transaction.remove(details);
        }
    };
}