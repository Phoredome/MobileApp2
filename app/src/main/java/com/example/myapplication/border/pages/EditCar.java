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
import com.example.myapplication.controller.CarController;
import com.example.myapplication.entities.Car;
import com.google.android.material.navigation.NavigationView;

public class EditCar extends AppCompatActivity {

    public CarController cc;
    Bundle a, b;
    Integer carId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.activity_edit_car);

        Intent i = getIntent();

        b = i.getExtras();
        a = i.getExtras();

        cc = new CarController(getApplicationContext());
        carId = a.getInt("carId");
        Boolean status = b.getBoolean("status");
        Car car = cc.getCarById(carId);

        final NavigationView navigationView = findViewById(R.id.nav_edit_car);

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
                    case R.id.nav_car_controller:
                        i = new Intent(getApplicationContext(), AdminMap.class);
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
        EditText license = findViewById(R.id.licenseP_ed);
        license.setText(car.getLicensePlate());
        EditText costRunTxt = findViewById(R.id.costRun_ed);
        costRunTxt.setText(String.valueOf(car.getCostOfRunning()));
        EditText kmRunTxt = findViewById(R.id.kmRun_ed);
        kmRunTxt.setText(String.valueOf(car.getKmsRun()));
        EditText kiloServTxt = findViewById(R.id.kiloServ_ed);
        kiloServTxt.setText(String.valueOf(car.getKmsSinceLastService()));
        EditText servTimeTxt = findViewById(R.id.servTime_ed);
        servTimeTxt.setText(String.valueOf(car.getServiceTime()));
        EditText coordXTxt = findViewById(R.id.coordX_ed);
        coordXTxt.setText(String.valueOf(car.getCoordX()));
        EditText coordYTxt = findViewById(R.id.coordY_ed);
        coordYTxt.setText(String.valueOf(car.getCoordY()));

        Spinner doorsSpin = findViewById(R.id.doors_ed);
        if (car.getDoors()== 2)
            doorsSpin.setSelection(1);
        if (car.getDoors() == 4)
            doorsSpin.setSelection(2);
        Spinner seatsSpin = findViewById(R.id.seatSpin_ed);
        if(car.getSeats() == 2)
            seatsSpin.setSelection(1);
        if(car.getSeats() == 5)
            seatsSpin.setSelection(2);
        if (car.getSeats() == 8)
            seatsSpin.setSelection(3);
        Spinner vehicleTypeSpin = findViewById(R.id.vehicleSpin_ed);
        if(car.getVehicleType().equals("Small car"))
            vehicleTypeSpin.setSelection(1);
        if(car.getVehicleType().equals("CRV car"))
            vehicleTypeSpin.setSelection(2);
        if(car.getVehicleType().equals("Van"))
            vehicleTypeSpin.setSelection(3);
        RadioButton useYesBtn = findViewById(R.id.radYes_ed);
        RadioButton useNoBtn = findViewById(R.id.radNo_ed);
        if(car.isInUse())
            useYesBtn.setChecked(true);
        else
            useNoBtn.setChecked(true);
        RadioButton servYesBtn = findViewById(R.id.radYes2_ed);
        RadioButton servNoBtn = findViewById(R.id.radNo2_ed);
        if(car.isInActiveService())
            servYesBtn.setChecked(true);
        else
            servNoBtn.setChecked(true);

        Button editCar = findViewById(R.id.editCarBtn);

        editCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText license = findViewById(R.id.licenseP_ed);
                EditText costRunTxt = findViewById(R.id.costRun_ed);


                CarController cc = new CarController(getApplicationContext());

                switch (cc.validateCars(license.getText().toString(), Double.parseDouble(costRunTxt.getText().toString()))) {
                    case 0:
                        if (cc.check(license.getText().toString(), costRunTxt.getText().toString())) {
                            editingCar();
                        } else
                            Toast.makeText(getBaseContext(), "Editing a car failed", Toast.LENGTH_LONG).show();
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

    public void editingCar() {

            Button edit = findViewById(R.id.editCarBtn);
            edit.setEnabled(false);

            EditText license = findViewById(R.id.licenseP_ed);
            EditText costRunTxt = findViewById(R.id.costRun_ed);
            EditText kmRunTxt = findViewById(R.id.kmRun_ed);
            EditText kiloServTxt = findViewById(R.id.kiloServ_ed);
            EditText servTimeTxt = findViewById(R.id.servTime_ed);
            EditText coordXTxt = findViewById(R.id.coordX_ed);
            EditText coordYTxt = findViewById(R.id.coordY_ed);

            Spinner doorsSpin = findViewById(R.id.doors_ed);
            Spinner seatsSpin = findViewById(R.id.seatSpin_ed);
            Spinner vehicleTypeSpin = findViewById(R.id.vehicleSpin_ed);

            RadioButton useYesBtn = findViewById(R.id.radYes_ed);
            RadioButton servYesBtn = findViewById(R.id.radYes2_ed);

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

            if ((cc.updateCar(carId, costR, seats, door, servTime, kmRun, kmSinceLastService,
                    vehicleType, licP, inUse, inServ, coordX, coordY))) {
                this.finish();

            }
        }

}