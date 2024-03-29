package com.example.myapplication.controller.entityController;

import android.content.Context;
import android.util.Log;

import com.example.myapplication.border.dao.StationDAO;
import com.example.myapplication.border.dao.TripDAO;
import com.example.myapplication.entities.Car;
import com.example.myapplication.entities.Station;

import java.util.ArrayList;

public class StationController
{
    MapController mc;
    TripDAO td;
    StationDAO sd;
    Station s;

    public StationController(Context context) {
        mc = new MapController();
        td = new TripDAO(context);
        sd = new StationDAO(context);
    }

    public Station getStationByID(int Id)
    {return sd.getStationByID(Id);}

    public ArrayList<Station> getAllStations()
    {return sd.getAllStations();}

    public int getNumberOfStations()
    {return sd.getNumberOfStations();}

    public int countCarsInStation(ArrayList<Station> stationList, ArrayList<Car> carList)
    {

        int count = 0;
        int lowest = 0;
        int highest = 0;
        int hold = 0;
        int average = 0;
        int i = 0;
        int k = 0;
        for(i = 0; i < stationList.size(); i++) {
            s = sd.getStationByID(i+1);
            if (s.isStationActive()) {
                k++;
                for (int j = 0; j < carList.size(); j++) {
                    Car c = carList.get(j);
                    if (c.isInActiveService() && !c.isInUse() && c.isInStation())
                        if (s.getLocationX() == c.getCoordX() && s.getLocationY() == c.getCoordY()) {
                            Log.d("StationController-CountCars", "" +count);
                            count++;
                        }

                }
                s.setCarsAtStation(count);
                hold+=count+1;
                Log.d("StationController-CountCars", "" +hold);
            }
        }
        average = hold / k;
        // if cars are too close to each other, move the furthest one from base away
        Log.d("StationController", "Average: " + average + " Hold: " + hold + " K: " + k);
        return average;
    }

    public Station findFullestStation(ArrayList<Station> stationList)
    {
        Station fullest = null;
        int num = 0;
        for (Station s : stationList)
        {
            if(s.isStationActive())
            {
                int numOfCars = s.getCarsAtStation();
                if( numOfCars > num) {
                    num = numOfCars;
                    fullest = s;
                }
            }
        }
        return fullest;
    }

}
