package com.apollo8.bestbye.ui.slideshow;

import android.media.Image;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.apollo8.bestbye.R;
import com.apollo8.bestbye.ui.home.HomeFragment;
import com.google.android.material.snackbar.Snackbar;

public class SlideshowFragment extends Fragment {

    private SlideshowViewModel slideshowViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        slideshowViewModel =
                ViewModelProviders.of(this).get(SlideshowViewModel.class);
        final View root = inflater.inflate(R.layout.fragment_slideshow, container, false);
        final TextView textView = root.findViewById(R.id.deleteMsg);
        slideshowViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText("Are you sure you want to delete all items?");
            }
        });

        final AppCompatButton btnYes = root.findViewById(R.id.buttonYes);
        final AppCompatButton btnCancel = root.findViewById(R.id.buttonCancel);


        btnYes.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                //Toast.makeText(root.getContext(), "test2" , Toast.LENGTH_SHORT).show();
                Snackbar.make(v, "Items successfully removed", Snackbar.LENGTH_LONG).setAction("Action", null).show();

            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Snackbar.make(v, "Item removal canceled", Snackbar.LENGTH_LONG).setAction("Action", null).show();

            }
        });


        return root;


    }
}