package com.example.myapplication.border;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.controller.CarController;
import com.example.myapplication.controller.DBController;
import com.example.myapplication.db.MyDB;

public class CreateCar extends AppCompatActivity {

    public CarController cc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.activity_create_car);

        Button addCar = findViewById(R.id.addCarBtn);

        addCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                addingCar();
            }
        });
    }

    public void addingCar() {

        if (!validateCar()) {
            failedCar();
            return;
        }

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

        //Button addCar = findViewById(R.id.addCarBtn);
        //Button cancel = findViewById(R.id.cancelBtn);

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
            Intent intent = new Intent(getApplicationContext(), AdminCarInfo.class);
            startActivity(intent);
            finish();
        }
    }


    public boolean validateCar() {

        boolean valid = true;

        /*

        if(){
        }
        else {
        }
        if(){
        }
        else {
        }
        if(){
        }
        else {
        }
        if(){
        }
        else {
        }
        if(){
        }
        else {
        }




         */


        return valid;
    }

    public void showToast(Context mContext, String message) {
        Toast.makeText(mContext, message, Toast.LENGTH_SHORT);
    }

    public void failedCar() {

        showToast(getBaseContext(), "Error, invalid values to create a car field. Please try again.");
        Button addCar = findViewById(R.id.addCarBtn);
        addCar.setEnabled(true);
    }

}


