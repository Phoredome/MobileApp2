package com.example.myapplication.border;

import android.content.pm.ActivityInfo;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.controller.CarController;
import com.example.myapplication.controller.DBController;
import com.example.myapplication.db.MyDB;

public class CreateCar extends AppCompatActivity {

    public SQLiteDatabase wdb, rdb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.activity_create_car);

        Button addCar = findViewById(R.id.addCarBtn);

        addCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


    }
}
