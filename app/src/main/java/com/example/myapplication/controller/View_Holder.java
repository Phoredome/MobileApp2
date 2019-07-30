package com.example.myapplication.controller;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;

public class View_Holder extends RecyclerView.ViewHolder {
    TextView title, description;
    ImageView imageView;

    public View_Holder(View itemView) {
        super(itemView);
        title = (TextView) itemView.findViewById(R.id.recyclerTextView3);
        description = (TextView) itemView.findViewById(R.id.recyclerTextView4);
        //imageView = (ImageView) itemView.findViewById(R.id.recyclerImageView);
    }
}
