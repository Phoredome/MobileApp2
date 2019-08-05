package com.example.myapplication.border.pages;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.controller.entityController.CarController;
import com.google.android.material.navigation.NavigationView;

public class CreateCar extends AppCompatActivity {

    public CarController cc;
    Bundle a, b;
    Integer seats, door;
    boolean inUse, inServ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.activity_create_car);

        Intent i = getIntent();

        b = i.getExtras();
        a = i.getExtras();

        cc = new CarController(getApplicationContext());
        Integer userId = a.getInt("userId");
        Boolean status = b.getBoolean("status");

        final NavigationView navigationView = findViewById(R.id.nav_create_car);

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
                        i = new Intent(CreateCar.this, LoginPage.class);
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

        Button addCar = findViewById(R.id.addCarBtn);

        addCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText license = findViewById(R.id.licenseP);
                EditText costRunTxt = findViewById(R.id.costRun);

                switch (cc.validateCars(license.getText().toString(), Double.parseDouble(costRunTxt.getText().toString()))) {
                    case 0:
                            addingCar();
                        break;
                    case 1:
                        license.setError("Enter a valid license plate number longer than 6 characters");
                        break;
                    case 2:
                        costRunTxt.setError("Please enter an amount greater than 0.");
                        break;
                }

            }
        });

        Button cancelBtn = findViewById(R.id.cancelBtn);

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
//====================================================================================================
    public void addingCar() {

            Button add = findViewById(R.id.addCarBtn);
            add.setEnabled(false);

            EditText license = findViewById(R.id.licenseP);
            EditText costRunTxt = findViewById(R.id.costRun);
            EditText kmRunTxt = findViewById(R.id.kmRun);
            EditText kiloServTxt = findViewById(R.id.kiloServ);
            EditText servTimeTxt = findViewById(R.id.servTime);
            EditText coordXTxt = findViewById(R.id.coordX);
            EditText coordYTxt = findViewById(R.id.coordY);

            Spinner doorsSpin = findViewById(R.id.doors);
            Spinner seatsSpin = findViewById(R.id.seatSpin);
            Spinner vehicleTypeSpin = findViewById(R.id.vehicleSpin);

            RadioButton useYesBtn = findViewById(R.id.radYes);
            RadioButton servYesBtn = findViewById(R.id.radYes2);

            if (useYesBtn.isChecked()){
                inUse = true;
            }
            else {
                inUse = false;
            }
            if (servYesBtn.isChecked()){
                inServ = true;
            }
            else {
                inServ = false;
            }
            String licP = license.getText().toString();
            String vehicleType = vehicleTypeSpin.getSelectedItem().toString();
            Double kmRun = Double.parseDouble(kmRunTxt.getText().toString());
            Double kmSinceLastService = Double.parseDouble(kiloServTxt.getText().toString());
            Integer servTime = Integer.parseInt(servTimeTxt.getText().toString());
            Double coordX = Double.parseDouble(coordXTxt.getText().toString());
            Double coordY = Double.parseDouble(coordYTxt.getText().toString());

            Double costR = Double.parseDouble(costRunTxt.getText().toString());

            if (seatsSpin.getSelectedItemPosition() != 0) {
                seats = Integer.parseInt(seatsSpin.getSelectedItem().toString().substring(0,1));
            }
            if (doorsSpin.getSelectedItemPosition() != 0) {
                door = Integer.parseInt(doorsSpin.getSelectedItem().toString().substring(0,1));
            }
            if ((cc.addCar(costR, seats, door, servTime, kmRun, kmSinceLastService,
                    vehicleType, licP, inUse, inServ, coordX, coordY))) {
                Toast.makeText(this,"Car created", Toast.LENGTH_SHORT).show();
                this.finish();

            }
        }

}