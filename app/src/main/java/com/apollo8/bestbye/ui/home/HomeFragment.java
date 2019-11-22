package com.apollo8.bestbye.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.apollo8.bestbye.MainActivity;
import com.apollo8.bestbye.R;
import com.apollo8.bestbye.RecyclerViewAdapter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.TimeZone;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private ArrayList<String> mNames = new ArrayList<>();
    private ArrayList<String> mImageUrls = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        final RecyclerView textView = root.findViewById(R.id.recycler_view);
        homeViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                //textView.setText(s);
            }
        });

        //initImageBitmaps();

        Log.d(TAG, "initImageBitmaps: preparing bitmaps.");

        String[] names = MainActivity.getNames();
        String[] imgs = MainActivity.getImgs();
        Double[] epochDates = MainActivity.getScnDate();



        for (int i = 0; i < names.length; i++) {

            Date date = new Date(epochDates[i].longValue() * 1000L);
            DateFormat format = new SimpleDateFormat("MM-dd-yyy");
            format.setTimeZone(TimeZone.getTimeZone("Etc/UTC"));
            String formatted = format.format(date);
            imgs[i] = imgs[i].replace("\"","");
            mImageUrls.add(imgs[i]);
            Log.e("TEST", imgs[i]);
            Log.e("TEST", "second test");

            mNames.add(names[i]+ "\n " + "Expiration date: " + formatted);

        }











        Log.d(TAG, "initRecyclerView: init recyclerview.");
        RecyclerView recyclerView = root.findViewById(R.id.recycler_view);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(getContext(), mNames, mImageUrls);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


        return root;
    }



}