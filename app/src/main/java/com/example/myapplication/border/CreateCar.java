package com.example.myapplication.border;
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
import com.example.myapplication.controller.CarController;
import com.google.android.material.navigation.NavigationView;

public class CreateCar extends AppCompatActivity {

    public CarController cc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.activity_create_car);

        final NavigationView navigationView = findViewById(R.id.nav_view);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int id = menuItem.getItemId();

                switch (id){
                    case R.id.nav_home:
                        startActivity(new Intent(CreateCar.this, MainActivity.class));
                        break;
                    case R.id.nav_account:
                        startActivity(new Intent(CreateCar.this, CreateAccount.class));
                        break;
                    case R.id.nav_history:
                        startActivity(new Intent(CreateCar.this, TripHistory.class));
                        break;
                    case R.id.nav_car_controller:
                        startActivity(new Intent(CreateCar.this, AdminMap.class));
                        break;
                    case R.id.nav_car_info:
                        startActivity(new Intent(CreateCar.this, AdminCarInfo.class));
                        break;
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


                CarController cc = new CarController(getApplicationContext());

                switch (cc.validateCars(license.getText().toString(), Double.parseDouble(costRunTxt.getText().toString()))) {
                    case 0:
                        if (cc.check(license.getText().toString(), costRunTxt.getText().toString())) {
                            addingCar();
                        } else
                            Toast.makeText(getBaseContext(), "Adding a car failed", Toast.LENGTH_LONG).show();
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
    }

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

            boolean inUse = useYesBtn.isSelected();
            boolean inServ = servYesBtn.isSelected();

            String licP = license.getText().toString();
            String vehicleType = vehicleTypeSpin.getSelectedItem().toString();
            Double kmRun = Double.parseDouble(kmRunTxt.getText().toString());
            Double kmSinceLastService = Double.parseDouble(kiloServTxt.getText().toString());
            Integer servTime = Integer.parseInt(servTimeTxt.getText().toString());
            Double coordX = Double.parseDouble(coordXTxt.getText().toString());
            Double coordY = Double.parseDouble(coordYTxt.getText().toString());

            Double costR = Double.parseDouble(costRunTxt.getText().toString());

            Integer seats = Integer.parseInt(seatsSpin.getSelectedItem().toString());
            Integer door = Integer.parseInt(doorsSpin.getSelectedItem().toString());

            if (!(cc.addCar(costR, seats, door, servTime, kmRun, kmSinceLastService,
                    vehicleType, licP, inUse, inServ, coordX, coordY))) {
                this.finish();

            }
        }

}