package com.apollo8.bestbye.ui.share;

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

public class ShareFragment extends Fragment {

    private ShareViewModel shareViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        shareViewModel =
                ViewModelProviders.of(this).get(ShareViewModel.class);
        final View root = inflater.inflate(R.layout.fragment_share, container, false);
        final TextView textView = root.findViewById(R.id.deleteMsg);
        shareViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText("Are you sure you want to sync the application and device?");
            }
        });

        final AppCompatButton btnYes2 = root.findViewById(R.id.buttonSync);
        final AppCompatButton btnCancel3 = root.findViewById(R.id.buttonCancel3);


        btnYes2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                //Toast.makeText(root.getContext(), "test2" , Toast.LENGTH_SHORT).show();
                Snackbar.make(v, "Items successfully retrieved from device", Snackbar.LENGTH_LONG).setAction("Action", null).show();

            }
        });

        btnCancel3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Snackbar.make(v, "Item sync canceled", Snackbar.LENGTH_LONG).setAction("Action", null).show();

            }
        });


        return root;


    }
}