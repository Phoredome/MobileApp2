package com.example.myapplication.border.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.myapplication.controller.controller.DBController;
import com.example.myapplication.db.MyDB;
import com.example.myapplication.entities.Trips;

import java.util.ArrayList;

public class TripDAO {

    SQLiteDatabase rdb;
    SQLiteDatabase wdb;

    public TripDAO (Context context) {
        rdb = DBController.getReadable(context);
        wdb = DBController.getWritable(context);
    }

    public boolean addTrip(int carId, int userId, double amount,
                           int kmsRunForTrip, String timeOfTrip,
                           String dateOfTrip, double startingX,
                           double startingY, double endingX, double endingY) {
        ContentValues values = new ContentValues();
        values.put("carId", carId);
        values.put("userId", userId);
        values.put("amount",amount);
        values.put("kmsRunForTrip", kmsRunForTrip);
        values.put("timeOfTrip", timeOfTrip);
        values.put("dateOfTrip", dateOfTrip);
        values.put("startingX", startingX);
        values.put("startingY", startingY);
        values.put("endingX", endingX);
        values.put("endingY", endingY);

        wdb.insert("Trip", null, values);

        return true;
    }

    public ArrayList<Trips> getAllTrips() {
        ArrayList<Trips> trips = new ArrayList<>();

        try {
            String selectQuery = "SELECT * FROM Trip;";
            Cursor cursor = rdb.rawQuery(selectQuery, null);
            Integer size = cursor.getCount();
            while(cursor.moveToNext()) {
                int index;
                index = cursor.getColumnIndexOrThrow("tripId");
                int tripId = cursor.getInt(index);

                index = cursor.getColumnIndexOrThrow("carId");
                int carId = cursor.getInt(index);

                index = cursor.getColumnIndexOrThrow("userId");
                int userId = cursor.getInt(index);

                index = cursor.getColumnIndexOrThrow("kmsRunForTrip");
                int kmsRunForTrip = cursor.getInt(index);

                index = cursor.getColumnIndexOrThrow("dateOfTrip");
                String dateOfTrip = cursor.getString(index);

                index = cursor.getColumnIndexOrThrow("timeOfTrip");
                String timeOfTrip = cursor.getString(index);

                index = cursor.getColumnIndexOrThrow("amount");
                double amount = cursor.getDouble(index);

                index = cursor.getColumnIndexOrThrow("startingX");
                double startingX = cursor.getDouble(index);

                index = cursor.getColumnIndexOrThrow("startingY");
                double startingY = cursor.getDouble(index);

                index = cursor.getColumnIndexOrThrow("endingX");
                double endingX = cursor.getDouble(index);

                index = cursor.getColumnIndexOrThrow("endingY");
                double endingY = cursor.getDouble(index);

                trips.add(new Trips(tripId, carId, userId, kmsRunForTrip, timeOfTrip,dateOfTrip, amount, startingX, startingY, endingX, endingY));

            }
            cursor.close();
        } catch (SQLException ex) {
            return null;
        }
        return trips;
    }


}
