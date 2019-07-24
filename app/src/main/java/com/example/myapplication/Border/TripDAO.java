package com.example.myapplication.Border;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.myapplication.DB.MyDB;

public class TripDAO {

    SQLiteDatabase rdb;
    SQLiteDatabase wdb;

    public TripDAO (Context context) {
        MyDB db = new MyDB(context);
        rdb = db.getReadableDatabase();
        wdb = db.getWritableDatabase();
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
}
