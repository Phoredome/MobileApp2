package com.example.myapplication.border;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.myapplication.R;

public class AdminCarInfo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.activity_admin_car_info);


        Button createCar = findViewById(R.id.createCarBtn);

        createCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Create a car button", "add car button");
                Intent i = new Intent(getApplicationContext(), CreateCar.class);
                startActivity(i);
                finish();
            }
        });
    }
}
