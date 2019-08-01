package com.example.myapplication.controller;

import android.content.Context;

import com.example.myapplication.border.dao.CarDAO;
import com.example.myapplication.entities.Car;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

public class TripController
{
    CarController cc;
    CarDAO cd;
    MapController mc;

    public TripController(Context context) {
        cc = new CarController(context);
        cd = new CarDAO(context);
        mc = new MapController();
    }


    public void addTrip(Context context, Car c, String address)
    {
        LatLng ll = mc.getLocationFromAddress(context, address);


        cc.getTripDistance(c,ll);
        c.setKmsRun(c.getKmsRun()+c.getDistance());
        c.getCostOfRunning();


    }
}
