package com.example.myapplication.controller;

import android.content.Context;

import com.example.myapplication.border.dao.TripDAO;
import com.example.myapplication.entities.Station;

public class StationController
{
    CarController cc;
    MapController mc;
    TripDAO td;
    StationDAO sd;

    public StationController(Context context) {
        cc = new CarController(context);
        mc = new MapController();
        td = new TripDAO(context);
        sd = new StationDAO(context);
    }

    public Station getStationByID(int Id)
    {return sd.getStationByID(Id);}


}
