package com.example.myapplication.border.pages;

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

    Bundle b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.activity_admin_info);

        Intent i = getIntent();
        b = i.getExtras();

        Boolean status = b.getBoolean("status");

        final NavigationView navigationView = findViewById(R.id.nav_admin_info);

        if(status){
            navigationView.inflateMenu(R.menu.activity_admin_drawer);

        } else {
            navigationView.inflateMenu(R.menu.activity_main_drawer);
        }

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                int id = menuItem.getItemId();
                Intent i = null;

                switch (id){
                    case R.id.nav_home:
                        i = new Intent(getApplicationContext(), MainActivity.class);
                        break;
                    case R.id.nav_account:
                        i = new Intent(getApplicationContext(), myAccount.class);
                        break;
                    case R.id.nav_history:
                        i = new Intent(getApplicationContext(), TripHistory.class);
                        break;
                    case R.id.nav_car_controller:
                        i = new Intent(getApplicationContext(), AdminMap.class);
                        break;
                    case R.id.nav_car_info:
                        i = new Intent(getApplicationContext(), AdminCarInfo.class);
                        break;
                }

                if(i!=null) {

                    i.putExtras(b);
                    startActivity(i);

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
