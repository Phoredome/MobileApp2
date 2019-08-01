package com.example.myapplication.border.pages;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.controller.manager.LoginManager;
import com.google.android.material.navigation.NavigationView;

public class myAccount  extends AppCompatActivity {


    public LoginManager lm;
    Bundle b;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.activity_myaccount);

        final NavigationView navigationView = findViewById(R.id.nav_myaccount);

        Intent i = getIntent();
        b = i.getExtras();

        Boolean status = b.getBoolean("status");

        if(status){
            navigationView.inflateMenu(R.menu.activity_admin_drawer);

        } else {
            navigationView.inflateMenu(R.menu.activity_main_drawer);
        }


        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                int id = menuItem.getItemId();

                switch (id){
                    case R.id.nav_home:
                        Intent i = new Intent(getApplicationContext(), MainActivity.class);
                        i.putExtras(b);
                        startActivity(i);
                        break;
                    case R.id.nav_account:
                        Intent j = new Intent(getApplicationContext(), myAccount.class);
                        j.putExtras(b);
                        startActivity(j);
                        break;
                    case R.id.nav_history:
                        Intent k = new Intent(getApplicationContext(), TripHistory.class);
                        k.putExtras(b);
                        startActivity(k);
                        break;
                    case R.id.nav_car_controller:
                        Intent a = new Intent(getApplicationContext(), AdminMap.class);
                        a.putExtras(b);
                        startActivity(a);
                        break;
                    case R.id.nav_car_info:
                        Intent n = new Intent(getApplicationContext(), AdminCarInfo.class);
                        n.putExtras(b);
                        startActivity(n);
                        break;
                }
                return false;

            }
        });

    }

}