package com.example.myapplication.border.pages;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.controller.CarController;
import com.example.myapplication.controller.RecyclerViewAdapter;
import com.example.myapplication.controller.RecyclerViewListAdapter;
import com.example.myapplication.entities.Car;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class AdminCarInfo extends AppCompatActivity {

    Bundle a, b;
    CarController cc;
    double total;
    int counter, counter1;
    RecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.activity_admin_info);
        cc = new CarController(getApplicationContext());

        Intent i = getIntent();

        b = i.getExtras();
        a = i.getExtras();

        String userId = a.getString("userId");
        Boolean status = b.getBoolean("status");


        TextView totalMade = findViewById(R.id.totalMade);
        TextView numActive = findViewById(R.id.numActive);
        TextView numRunning = findViewById(R.id.numRunning);

        ArrayList<Car> cars = cc.getAllCars();
        for (int y=0; y< cars.size(); y++) {
            total += cars.get(y).getCostOfRunning();
            totalMade.setText("Your total made today is " + total + ".");
        }

        for (int x=0; x< cars.size(); x++) {
            if (cars.get(x).isInActiveService()) {
                counter++;
                numActive.setText("You have " + counter + " cars active.");
            }
        }

        for (int x=0; x<cars.size(); x++) {
            if (cars.get(x).isInUse()) {
                counter1++;
                numRunning.setText("You have " + counter1 + " cars running.");
            }
        }

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
                        i = new Intent(getApplicationContext(), MyAccount.class);
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
                    i.putExtras(a);
                    startActivity(i);

                }
                return false;

            }
        });

        rv = findViewById(R.id.adminInfoRecyclerView);
        ArrayList<Car> allCars = cc.getAllCars();
        RecyclerViewListAdapter adapter = new RecyclerViewListAdapter(allCars, getApplication());
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(adapter);


        Button createCar = findViewById(R.id.createCarBtn);

        createCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Create a car button", "add car button");
                Intent i = new Intent(getApplicationContext(), CreateCar.class);
                i.putExtras(b);
                i.putExtras(a);
                startActivity(i);
                finish();
            }
        });
    }
}
