package com.example.myapplication.border.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.myapplication.controller.DBController;
import com.example.myapplication.entities.Trip;

import java.util.ArrayList;
import java.util.Date;

public class TripDAO {

    SQLiteDatabase rdb;
    SQLiteDatabase wdb;

    public TripDAO (Context context) {
        rdb = DBController.getReadable(context);
        wdb = DBController.getWritable(context);
    }

    public boolean addTrip(int carId, int userId, double amount,
                           double kmsRunForTrip, String timeOfTrip,
                           String dateOfTrip, double startingX,
                           double startingY, double endingX, double endingY) {
        ContentValues values = new ContentValues();
        values.put("carId", carId);
        values.put("userId", userId);
        values.put("amount",amount);
        values.put("kmsRunForTrip", kmsRunForTrip);
        //TODO CHANGE date and time to date object
        values.put("timeOfTrip", timeOfTrip);
        values.put("dateOfTrip", dateOfTrip);
        values.put("startingX", startingX);
        values.put("startingY", startingY);
        values.put("endingX", endingX);
        values.put("endingY", endingY);

        wdb.insert("Trip", null, values);

        return true;
    }

    public ArrayList<Trip> getAllTrips() {
        ArrayList<Trip> trips = new ArrayList<>();

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

                trips.add(new Trip(tripId, carId, userId, kmsRunForTrip, timeOfTrip,dateOfTrip, amount, startingX, startingY, endingX, endingY));

            }
            cursor.close();
        } catch (SQLException ex) {
            return null;
        }
        return trips;
    }

    public int getTripCount()
    {
        String selectQuery = "SELECT * FROM Trips;";
        Cursor cursor = rdb.rawQuery(selectQuery, null);
        int count = cursor.getCount();
        cursor.close();
        return count;

    }

    public Trip getTripByUserId(Integer userId) {
        ArrayList<Trip> trips = getAllTrips();
        Trip trip = null;
        for(Trip t:trips) {
            if(userId == t.getUserId()){
                trip = t;
            }
        }
        return trip;
    }

    public Trip getTripById(Integer id) {
        ArrayList<Trip> trip = getAllTrips();
        for (Trip t:trip) {
            if(t.getTripId() == id) {
                return t;
            }
        }
        return null;
    }


}
