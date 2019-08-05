package com.example.myapplication.controller.entityController;

import android.content.Context;

import com.example.myapplication.border.dao.TripDAO;
import com.example.myapplication.border.info.GetDistanceProbe;
import com.example.myapplication.entities.Car;
import com.example.myapplication.entities.Trip;
import com.example.myapplication.entities.User;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

public class TripController
{
    CarController cc;
    MapController mc;
    TripDAO td;

    public TripController(Context context) {
        cc = new CarController(context);
        mc = new MapController();
        td = new TripDAO(context);
    }

    public ArrayList<Trip> getAllTrips() {
        return td.getAllTrips();
    }
    public void addTrip(GoogleMap gmap, GetDistanceProbe.DistanceListener dlistener, Car c,User user, String ll)
    {
        String[] xy = ll.split(",");
        LatLng hold = new LatLng(Double.parseDouble(xy[0].trim()), Double.parseDouble(xy[1].trim()));
        addTrip(gmap, dlistener,c,user,hold);
    }
    public void addTrip(GoogleMap gmap, GetDistanceProbe.DistanceListener dlistener, Car c, User user, LatLng ll)
    {

        int num = getHighestTripNum(getUserTrip(user.getUserId()));

        cc.getTripDistance(dlistener, c, ll);

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

        cc.moveCar(gmap, c, ll.latitude, ll.longitude);
    }

    public ArrayList<Trip> getUserTrip(Integer userId)
    {
        ArrayList<Trip> trip = td.getAllTrips();
        ArrayList<Trip> userTrip = null;

        for (Trip t: trip)
        {
            if(t.getUserId() == td.getTripByUserId(userId).getUserId())
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