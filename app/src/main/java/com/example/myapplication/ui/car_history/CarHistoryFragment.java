package com.example.myapplication.ui.car_history;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.myapplication.R;

public class CarHistoryFragment extends Fragment {

    private CarHistoryViewModel carHistoryViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        carHistoryViewModel =
                ViewModelProviders.of(this).get(CarHistoryViewModel.class);
        View root = inflater.inflate(R.layout.fragment_car_history, container, false);
        final TextView textView = root.findViewById(R.id.text_car_history);
        carHistoryViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}