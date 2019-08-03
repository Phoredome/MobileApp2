package com.example.myapplication.border.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.myapplication.controller.DBController;
import com.example.myapplication.entities.Car;
import com.example.myapplication.entities.Station;

import java.util.ArrayList;

public class StationDAO
{
    SQLiteDatabase wdb;
    SQLiteDatabase rdb;

    public StationDAO(Context context) {
        wdb = DBController.getWritable(context);
        rdb = DBController.getReadable(context);
    }

    public ArrayList<Station> getAllStations()
    {
        ArrayList<Station> stations = new ArrayList<>();
        try {

            String selectQuery = "SELECT * FROM Station;";
            Cursor cursor = rdb.rawQuery(selectQuery, null);
            Integer size = cursor.getCount();
            while(cursor.moveToNext()) {
                int index;
                index = cursor.getColumnIndexOrThrow("stationId");
                int stationId = cursor.getInt(index);

                index = cursor.getColumnIndexOrThrow("carsAtStation");
                int carsAtStation = cursor.getInt(index);

                index = cursor.getColumnIndexOrThrow("locationX");
                double locationX = cursor.getDouble(index);

                index = cursor.getColumnIndexOrThrow("locationY");
                double locationY = cursor.getDouble(index);

                index = cursor.getColumnIndexOrThrow("stationActive");
                Boolean stationActive = Boolean.parseBoolean(cursor.getString(index));
            }cursor.close();
        } catch (SQLException ex) {
            return null;
        }
        return stations;
    }
    public int getStationCount() {
        String selectQuery = "SELECT * FROM Station;";
        Cursor cursor = rdb.rawQuery(selectQuery, null);
        int count = cursor.getCount();
        cursor.close();
        return count;
    }
    public Station getStationByID(int stationId)
    {
        ArrayList<Station> stations = getAllStations();
        Station station = null;
        for (Station s : stations) {
            if (stationId == s.getStationId()) {
                station = s;
            }
        }
        return station;
    }
}
