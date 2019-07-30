package com.example.myapplication.border;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.google.android.material.navigation.NavigationView;

public class TripHistory extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.activity_history);

        final NavigationView navigationView = findViewById(R.id.nav_view);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int id = menuItem.getItemId();

                switch (id){
                    case R.id.nav_home:
                        startActivity(new Intent(TripHistory.this, MainActivity.class));
                        break;
                    case R.id.nav_account:
                        startActivity(new Intent(TripHistory.this, CreateAccount.class));
                        break;
                    case R.id.nav_history:
                        startActivity(new Intent(TripHistory.this, TripHistory.class));
                        break;
                    case R.id.nav_car_controller:
                        startActivity(new Intent(TripHistory.this, AdminMap.class));
                        break;
                    case R.id.nav_car_info:
                        startActivity(new Intent(TripHistory.this, AdminCarInfo.class));
                        break;
                }
                return false;
            }
        });





    }
}