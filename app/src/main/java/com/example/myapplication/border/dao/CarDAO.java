package com.example.myapplication.border.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.myapplication.controller.entityController.DBController;
import com.example.myapplication.entities.Car;
import com.example.myapplication.entities.Station;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

public class CarDAO {
    SQLiteDatabase wdb;
    SQLiteDatabase rdb;

    public CarDAO(Context context) {
        wdb = DBController.getWritable(context);
        rdb = DBController.getReadable(context);
    }

    public Car getCarById(int carId)
    {
        ArrayList<Car> cars = getAllCars();
        for(Car c : cars)
            if(carId == c.getCarID())
                return c;
        return null;
    }

    public Car getCarByLicense(String license)
    {
        ArrayList<Car> cars = getAllCars();
        for(Car c : cars)
            if(license.equals(c.getLicensePlate()))
                return c;
        return null;
    }

    public ArrayList<Car> getAllCarsByStation(Station s)
    {
        ArrayList<Car> carsAtStation = new ArrayList<>();

        for(Car c : getAllCars())
        {
            Log.d("CarDAO-getAllCarsByStation", "" + c.getCarID() + c.isInStation() + c.getCoordX() + c.getCoordY());
                if (c.isInStation() && c.getCoordX() == s.getLocationX() && c.getCoordY() == s.getLocationY()) {
                    Log.d("get all Cars by Station", "inside if loop");
                    carsAtStation.add(c);
                }
        }

        return carsAtStation;
    }

    public int getCarCount() {
        String selectQuery = "SELECT * FROM Car;";
        Cursor cursor = rdb.rawQuery(selectQuery, null);
        int count = cursor.getCount();
        cursor.close();
        return count;
    }

    public Car getCarByLocation(LatLng ll)
    {
        Double x = ll.latitude;
        Double y = ll.longitude;

        ArrayList<Car> cars = getAllCars();

        for(Car c : cars)
            if(c.getCoordX() == x && c.getCoordY() == y)
                return c;

            return null;
    }

    public ArrayList<String> getCarTypes() {
        ArrayList<Car> cars = getAllCars();
        ArrayList<String> carTypes = new ArrayList<>();

        for (int i=0; i < cars.size(); i++) {
            carTypes.add(cars.get(i).getVehicleType());
        }
        return carTypes;
    }

    public ArrayList<Car> getAllCars() {
        ArrayList<Car> cars = new ArrayList<>();
        try {

            String selectQuery = "SELECT * FROM Car;";
            Cursor cursor = rdb.rawQuery(selectQuery, null);
            Integer size = cursor.getCount();
            while(cursor.moveToNext()) {
                int index;
                index = cursor.getColumnIndexOrThrow("carId");
                int carId = cursor.getInt(index);

                index = cursor.getColumnIndexOrThrow("costOfRunning");
                double costOfRunning = cursor.getDouble(index);

                index = cursor.getColumnIndexOrThrow("seats");
                int seats = cursor.getInt(index);

                index = cursor.getColumnIndexOrThrow("doors");
                int doors = cursor.getInt(index);

                index = cursor.getColumnIndexOrThrow("serviceTime");
                int serviceTime = cursor.getInt(index);

                index = cursor.getColumnIndexOrThrow("kmsRun");
                int kmsRun = cursor.getInt(index);

                index = cursor.getColumnIndexOrThrow("kmsSinceLastService");
                int kmsSinceLastService = cursor.getInt(index);

                index = cursor.getColumnIndexOrThrow("vehicleType");
                String vehicleType = cursor.getString(index);

                index = cursor.getColumnIndexOrThrow("licensePlate");
                String licensePlate = cursor.getString(index);

                index = cursor.getColumnIndexOrThrow("inUse");
                Boolean inUse = Boolean.parseBoolean(cursor.getString(index));

                index = cursor.getColumnIndexOrThrow("inActiveService");
                Boolean inService = Boolean.parseBoolean(cursor.getString(index));

                index = cursor.getColumnIndexOrThrow("coordX");
                double coordX = cursor.getDouble(index);

                index = cursor.getColumnIndexOrThrow("coordY");
                double coordY = cursor.getDouble(index);
                index = cursor.getColumnIndexOrThrow("inStation");
                Boolean inStation = Boolean.parseBoolean(cursor.getString(index));
                Car car = new Car(carId, costOfRunning, seats, doors, serviceTime, kmsRun, kmsSinceLastService, vehicleType, licensePlate, inUse, inService, coordX, coordY, inStation);
                cars.add(car);


            }
            cursor.close();
        } catch (SQLException ex) {
            return null;
        }
        return cars;
    }

    public boolean addCar(double costOfRunning,
                          int seats,
                          int doors,
                          int serviceTime,
                          double kmsRun,
                          double kmSinceLastService,
                          String vehicleType,
                          String licensePlate,
                          boolean inUse,
                          boolean inService,
                          double coordX,
                          double coordY)
    {
        ContentValues values = new ContentValues();
        values.put("costOfRunning", costOfRunning);
        values.put("seats", seats);
        values.put("doors", doors);
        values.put("serviceTime", serviceTime);
        values.put("kmsRun", kmsRun);
        values.put("kmsSinceLastService",kmSinceLastService);
        values.put("vehicleType", vehicleType);
        values.put("licensePlate", licensePlate);
        values.put("inUse", inUse);
        values.put("inActiveService", inService);
        values.put("coordX", coordX);
        values.put("coordY", coordY);

        wdb.insert("Car", null, values);

        return true;
    }

    
    public void updateCarLocation(int carNum, double coordX, double coordY) {
        //Car car = getCarById(carNum);

//        String updateQuery = "UPDATE CAR SET coordX='" + coordX + ", coordY='" + coordY + "' WHERE carId='" + car.getCarID() + "';";

        ContentValues values = new ContentValues();
        values.put("coordX", coordX);
        values.put("coordY", coordY);
        wdb.update("Car", values,  "carId="+carNum, null);
        Log.d("Updated car", "Car Updated");
    }

    public void updateCarRate(int carNum, double scRate, double crvRate, double vanRate)
    {
        ContentValues values = new ContentValues();

        Car c = getCarById(carNum);
        if(c.getVehicleType() == "smallCar")
            values.put("costOfRunning", scRate);
        else if(c.getVehicleType() == "crv")
            values.put("costOfRunning", crvRate);
        else if(c.getVehicleType() == "van")
            values.put("costOfRunning", vanRate);

        wdb.update("Car", values,  "carId="+carNum, null);
        Log.d("Updated car", "Car Updated");
    }
    public void updateCarInfo(int carNum, double costOfRunning, int seats, int doors, int serviceTime,
                              double kmsRun, double kmsSinceLastService, String vehicleType, String licensePlate,
                              boolean inUse, boolean inService, double coordX, double coordY){

        ContentValues values = new ContentValues();
        values.put("costOfRunning", costOfRunning);
        values.put("seats", seats);
        values.put("doors", doors);
        values.put("serviceTime", serviceTime);
        values.put("kmsRun", kmsRun);
        values.put("kmsSinceLastService", kmsSinceLastService);
        values.put("vehicleType", vehicleType);
        values.put("licensePlate", licensePlate);
        values.put("inUse", inUse);
        values.put("inActiveService", inService);
        values.put("coordX", coordX);
        values.put("coordY", coordY);

        wdb.update("Car",values, "carId="+carNum, null);

    }

}
