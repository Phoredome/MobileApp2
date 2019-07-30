package com.example.myapplication.border;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.controller.LoginManager;
import com.google.android.material.navigation.NavigationView;

public class myAccount  extends AppCompatActivity {


    public LoginManager lm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.activity_myaccount);

        final NavigationView navigationView = findViewById(R.id.nav_myaccount);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int id = menuItem.getItemId();

                switch (id){
                    case R.id.nav_home:
                        startActivity(new Intent(myAccount.this, MainActivity.class));
                        break;
                    case R.id.nav_myaccount:
                        startActivity(new Intent(myAccount.this, myAccount.class));
                        break;
                    case R.id.nav_history:
                        startActivity(new Intent(myAccount.this, TripHistory.class));
                        break;
                    case R.id.nav_car_controller:
                        startActivity(new Intent(myAccount.this, AdminMap.class));
                        break;
                    case R.id.nav_car_info:
                        startActivity(new Intent(myAccount.this, AdminCarInfo.class));
                        break;
                }
                return false;
            }
        });
    }

}