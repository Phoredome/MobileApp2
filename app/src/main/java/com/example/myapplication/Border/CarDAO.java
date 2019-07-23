package com.example.myapplication.Border;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.myapplication.DB.MyDB;
import com.example.myapplication.Entities.Car;

import java.util.ArrayList;

public class CarDAO {
    SQLiteDatabase wdb;
    SQLiteDatabase rdb;

    public CarDAO(Context context) {
        MyDB db = new MyDB(context);
        wdb = db.getWritableDatabase();
        rdb = db.getReadableDatabase();
    }


    private ArrayList<Car> getCars() {
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

                index = cursor.getColumnIndexOrThrow("capacity");
                int capacity = cursor.getInt(index);

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

                cars.add(new Car(carId, costOfRunning, seats, doors, capacity, serviceTime, kmsRun, kmsSinceLastService, vehicleType, licensePlate, inUse, inService, coordX, coordY));

            }
            cursor.close();
        } catch (SQLException ex) {
            return null;
        }
        return cars;
    }
}
