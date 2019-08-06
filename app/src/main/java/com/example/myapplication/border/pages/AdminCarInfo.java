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
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.controller.entityController.CarController;
import com.example.myapplication.controller.adapters.RecyclerViewListAdapter;
import com.example.myapplication.entities.Car;
import com.google.android.material.navigation.NavigationView;

import java.text.DecimalFormat;
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
        final NavigationView navigationView = findViewById(R.id.nav_admin_info);

        Intent i = getIntent();

        b = i.getExtras();
        a = i.getExtras();

        String uName = a.getString("userN");

        View headerView = navigationView.getHeaderView(0);
        TextView userNameTitle =  headerView.findViewById(R.id.nav_name);
        userNameTitle.setText("Hi, " + uName);

        Integer userId = a.getInt("userId");
        Boolean status = b.getBoolean("status");


        TextView totalMade = findViewById(R.id.totalMade);
        TextView numActive = findViewById(R.id.numActive);
        TextView numRunning = findViewById(R.id.numRunning);

        DecimalFormat df = new DecimalFormat("#0.00");

        ArrayList<Car> cars = cc.getAllCars();
        for (int y=0; y< cars.size(); y++) {
            total += cars.get(y).getCostOfRunning();
            totalMade.setText("Your total made today is $" + df.format(total) + ".");
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

                switch (id) {
                    case R.id.nav_home:
                        i = new Intent(getApplicationContext(), MainActivity.class);
                        break;
                    case R.id.nav_account:
                        i = new Intent(getApplicationContext(), MyAccount.class);
                        break;
                    case R.id.nav_history:
                        i = new Intent(getApplicationContext(), TripHistory.class);
                        break;
                    case R.id.nav_logout:
                        i = new Intent(AdminCarInfo.this, LoginPage.class);
                        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(i);
                        finish();
                        break;
                    case R.id.nav_car_controller:
                        i = new Intent(getApplicationContext(), AdminCarController.class);
                        break;
                    case R.id.nav_car_info:
                        i = new Intent(getApplicationContext(), AdminCarInfo.class);
                        break;
                }

                if (i != null) {

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




        Button updateRates = findViewById(R.id.updateRateBtn);
        updateRates.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText smallC = findViewById(R.id.rate1Txt);
                EditText crv = findViewById(R.id.rate3Txt);
                EditText vanC = findViewById(R.id.rate2Txt);


                Double sCar = Double.parseDouble(smallC.getText().toString());
                Double vCar = Double.parseDouble(vanC.getText().toString());
                Double crvCar = Double.parseDouble(crv.getText().toString());


                cc.setCarRates(sCar, crvCar, vCar);

                smallC.setText(sCar.toString());
                crv.setText(crvCar.toString());
                vanC.setText(vCar.toString());

                Toast.makeText(getApplicationContext(),"Car updated" , Toast.LENGTH_LONG).show();
            }
        });

        EditText smallC = findViewById(R.id.rate1Txt);
        EditText crv = findViewById(R.id.rate3Txt);
        EditText vanC = findViewById(R.id.rate2Txt);

        Log.d("AdminCarInfo-OnCreate", "loading hints");
        smallC.setHint(cc.findCarRate("Small Car").toString());
        crv.setHint(cc.findCarRate("CRV Car").toString());
        vanC.setHint(cc.findCarRate("Van").toString());
    }
}
