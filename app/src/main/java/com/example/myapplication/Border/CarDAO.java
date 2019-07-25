package com.example.myapplication.Border;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.myapplication.Controller.DBController;
import com.example.myapplication.DB.MyDB;
import com.example.myapplication.Entities.Car;

import java.util.ArrayList;

public class CarDAO {
    SQLiteDatabase wdb;
    SQLiteDatabase rdb;

    public CarDAO(Context context) {
        wdb = DBController.getWritable(context);
        rdb = DBController.getReadable(context);
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

                index = cursor.getColumnIndexOrThrow("inService");
                Boolean inService = Boolean.parseBoolean(cursor.getString(index));

                index = cursor.getColumnIndexOrThrow("coordX");
                double coordX = cursor.getDouble(index);

                index = cursor.getColumnIndexOrThrow("coordY");
                double coordY = cursor.getDouble(index);
                Car car = new Car(carId, costOfRunning, seats, doors, serviceTime, kmsRun, kmsSinceLastService, vehicleType, licensePlate, inUse, inService, coordX, coordY);
                cars.add(car);

                Log.d("Inside getting all cars", car.toString());

            }
            cursor.close();
        } catch (SQLException ex) {
            return null;
        }
        Log.d("Inside CarDAO", cars.toString());
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
        values.put("inService", inService);
        values.put("coordX", coordX);
        values.put("coordY", coordY);

        wdb.insert("Car", null, values);

        return true;
    }

    




}
