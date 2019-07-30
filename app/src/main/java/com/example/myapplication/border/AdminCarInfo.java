package com.example.myapplication.border;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.myapplication.R;
import com.google.android.material.navigation.NavigationView;

public class AdminCarInfo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.activity_admin_info);


        final NavigationView navigationView = findViewById(R.id.nav_admin_info);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int id = menuItem.getItemId();

                switch (id){
                    case R.id.nav_home:
                        startActivity(new Intent(AdminCarInfo.this, MainActivity.class));
                        break;
                    case R.id.nav_account:
                        startActivity(new Intent(AdminCarInfo.this, myAccount.class));
                        break;
                    case R.id.nav_history:
                        startActivity(new Intent(AdminCarInfo.this, TripHistory.class));
                        break;
                    case R.id.nav_car_controller:
                        startActivity(new Intent(AdminCarInfo.this, AdminMap.class));
                        break;
                    case R.id.nav_car_info:
                        startActivity(new Intent(AdminCarInfo.this, AdminCarInfo.class));
                        break;
                }
                return false;
            }
        });

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
