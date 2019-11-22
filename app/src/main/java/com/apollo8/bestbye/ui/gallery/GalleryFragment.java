package com.apollo8.bestbye.ui.gallery;

import android.app.DatePickerDialog;
import android.media.Image;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
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

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class GalleryFragment extends Fragment {

    DatePickerDialog picker;

    private GalleryViewModel galleryViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        galleryViewModel =
                ViewModelProviders.of(this).get(GalleryViewModel.class);
        final View root = inflater.inflate(R.layout.fragment_gallery, container, false);
        final TextView textView = root.findViewById(R.id.deleteMsg);
        galleryViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText("Add an expiration date");
            }
        });

        final AppCompatButton btnYes2 = root.findViewById(R.id.buttonApply);
        final AppCompatButton btnCancel3 = root.findViewById(R.id.buttonCancel3);

        final EditText eText;

        eText=(EditText) root.findViewById(R.id.editText);
        eText.setInputType(InputType.TYPE_NULL);
        eText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                picker = new DatePickerDialog(getContext(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                eText.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        }, year, month, day);
                picker.show();
            }
        });


        btnYes2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                //Toast.makeText(root.getContext(), "test2" , Toast.LENGTH_SHORT).show();
                Snackbar.make(v, "Expiration date successfully updated", Snackbar.LENGTH_LONG).setAction("Action", null).show();

            }
        });

        btnCancel3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Snackbar.make(v, "Expiration date reset", Snackbar.LENGTH_LONG).setAction("Action", null).show();

            }
        });




        return root;


    }


}