package com.example.myapplication.controller;

import android.content.Context;

import com.example.myapplication.border.dao.CarDAO;
import com.example.myapplication.border.dao.TripDAO;
import com.example.myapplication.border.dao.UserDAO;
import com.example.myapplication.entities.Car;
import com.example.myapplication.entities.Trip;
import com.example.myapplication.entities.User;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.Calendar;

public class TripController
{
    CarController cc;
    CarDAO cd;
    MapController mc;
    TripDAO td;
    UserDAO ud;

    public TripController(Context context) {
        cc = new CarController(context);
        cd = new CarDAO(context);
        mc = new MapController();
        td = new TripDAO(context);
        ud = new UserDAO(context);

    }


    public void addTrip(Context context, Car c,String user, String address)
    {
        LatLng ll = mc.getLocationFromAddress(context, address);

        int num = getHighestTripNum(getUserTrip(user));

        cc.getTripDistance(c,ll);

        c.setKmsRun(c.getKmsRun()+c.getDistance());
        c.getCostOfRunning();

        //TODO FIX TripDAO
         /*td.addTrip(c.getCarID(),
         ud.getUser(user).getUserId(),
         c.getCostOfRunning()*c.getDistance(),
         c.getDistance(),
         Calendar.getInstance().getTime(),
         c.getCoordX(),c.getCoordY(),
         ll.latitude, ll.longitude);*/
    }

    public ArrayList<Trip> getUserTrip(String user)
    {
        ArrayList<Trip> trip = td.getAllTrips();
        ArrayList<Trip> userTrip = null;

        for (Trip t: trip)
        {
            if(t.getUserId() == ud.getUser(user).getUserId())
                userTrip.add(t);
        }
        return userTrip;
    }

    public int getHighestTripNum(ArrayList<Trip> trip)
    {
        int i = 0;
        for (Trip t: trip)
        {
            if(t.getTripId() > i )
                i = t.getTripId();
        }
        return i;
    }
}