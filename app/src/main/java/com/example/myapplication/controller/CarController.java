package com.example.myapplication.controller;

import android.content.Context;
import android.util.Log;

import com.example.myapplication.border.dao.CarDAO;
import com.example.myapplication.border.dao.StationDAO;
import com.example.myapplication.border.pages.CreateCar;
import com.example.myapplication.border.info.GetDistanceProbe;
import com.example.myapplication.entities.Car;
import com.example.myapplication.entities.Station;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class CarController{

    public CarDAO cd;
    public MapController mc;
    public CreateCar cc;
    public StationDAO sd;
    public Car car;

    double depotx = 0.00;
    double depoty = 0.00;
    int NUMBEROFCARS = 10;

    Context context;

    /*GetDistanceProbe.DistanceListener context;
    GetDistanceProbe asyncTask =new GetDistanceProbe(context);*/

    public CarController(Context context) {
        cd = new CarDAO(context);
        mc = new MapController();
        sd = new StationDAO(context);

        this.context = context;
    }

    public Car getCarById(int carId) {
        return cd.getCarById(carId);
    }


    public ArrayList<String> getCarTypes() {
        return cd.getCarTypes();
    }


    public ArrayList<Car> getAllCars() {
        return cd.getAllCars();}

    public Boolean check(String license, String costOfRun)
    {
        Car car = null;
        try {
            car = cd.getCarByLicense(license);
            if (car != null && costOfRun != null)
                return true;
            else
                return false;
        } catch(Exception e) {
            Log.e("Add car error", e.getMessage(), e);
        }
        return false;
    }
    public Boolean checkToAdd() {
        return true;
    }


    public Boolean addCar(double costOfRunning,
                                 int seats,
                                 int doors,
                                 int serviceTime,
                                 double kmsRun,
                                 double kmSinceLastService,
                                 String vehicleType,
                                 String licensePlate,
                                 boolean inUse,
                                 boolean inService,
                                 double coordX,
                                 double coordY)
    {

        return cd.addCar(costOfRunning, seats, doors, serviceTime, kmsRun, kmSinceLastService, vehicleType, licensePlate, inUse, inService, coordX, coordY);
    }
    public Boolean updateCar(int carNum,
                             double costOfRunning,
                             int seats,
                             int doors,
                             int serviceTime,
                             double kmsRun,
                             double kmSinceLastService,
                             String vehicleType,
                             String licensePlate,
                             boolean inUse,
                             boolean inService,
                             double coordX,
                             double coordY) {
        return cd.updateCarInfo(carNum,costOfRunning,seats,doors,serviceTime,kmsRun,kmSinceLastService,vehicleType,licensePlate,inUse,inService,coordX,coordY);
    }

    public int validateCars(String license, Double costOfRun)
    {
        int valid = 0;

        if (license.isEmpty() || license.length() < 6)
            valid = 1;

        else if(costOfRun.equals(0))
            valid = 2;

        return valid;

    }
    // will use other methods to aid in redistributing unused car locations
    public Boolean countOfCarsInStation()
    {
        //TODO
        ArrayList<Car> carList = cd.getAllCars();
        ArrayList<Station> stationList = sd.getAllStations();
        int[][] stationCars = new int[sd.getStationCount()][cd.getCarCount()];
        int count = 0;
        for(Station s : stationList)
            if(s.isStationActive()) {
                for (int i = 0; i < carList.size(); i++) {
                    Car c = carList.get(i);
                        if(c.isInActiveService()&&!c.isInUse()&&c.isInStation())
                            if (s.getLocationX() == c.getCoordX() && s.getLocationY() == c.getCoordY()) {
                            count++;

                        }
                        s.setCarsAtStation(count);
                    }

        }
        // if cars are too close to each other, move the furthest one from base away
        return false;
    }

    //will randomly disperse unused cars to random locations across the map
    public void randomizeCarLocations()
    {
        //TODO Create ASync function to move cars around with RNG decision making at intersections


    }

    /**
     *
     * @param car car id of car intended for move
     * @param xCoord xCoord of target location
     * @param yCoord yoord of target location
     */
    public boolean moveCar(GoogleMap map, Car car, double xCoord, double yCoord)
    {
        //Figure out what data type to use for getting location, X and Y or a concatenation?
        if(car.isInUse() || !car.isInActiveService())
            return false;
        else {
            car.setCoordX(xCoord);
            car.setCoordY(yCoord);
            cd.updateCar(car.getCarID(),xCoord,yCoord);
        }

        mc.updateCarMarker(map, car, xCoord, yCoord);
        return true;
    }

    /**
     * @param car Id of car intended to be sent to depot for repairs/servicing
     */
    public boolean serviceCar(Car car)
    {
        if(!car.isInUse())
        {
            car.setInActiveService(false);
            car.setKmsSinceLastService(0);

            return true;
        }
        return false;
    }

    public void getCarDistance(GetDistanceProbe.DistanceListener dlistener)
    {
        ArrayList<Car> carList = cd.getAllCars();

        for (Car c : carList)
        {
            double x = c.getCoordX();
            double y = c.getCoordY();

            getCarDistances(dlistener, c, x, y);
        }
    }

    public void getNearCarsFromLocation(GetDistanceProbe.DistanceListener dlistener, double x, double y )
    {
        Car c = null;
        getCarDistances(dlistener, c, x, y);}

    public void getDistanceFromBase(GetDistanceProbe.DistanceListener dlistener)
    {
        Car c = null;
        getCarDistances(dlistener, c,49.232000, -123.023000);
    }


    public void getCarDistances(GetDistanceProbe.DistanceListener dlistener, Car c, double x, double y)
    {
        GetDistanceProbe gdp = new GetDistanceProbe(dlistener);
        gdp.setOrigin(x, y);
        Log.d("Car controller - get car distances", "origin set");
        gdp.execute(cd.getAllCars());

    }

    public void getTripDistance(Car c, LatLng ll)
    {
        //TODO Change to callable --- startSearch( c.getCoordX(), c.getCoordY(), ll.latitude, ll.longitude);
    }

    public void initializeCars(GoogleMap map)
    {
        ArrayList<Car> nearByCars;

        boolean check;


        nearByCars = cd.getAllCars();
        Log.d("inside Initialize", nearByCars.toString());
        for (Car c: nearByCars) {
            check = moveCar(map, c, c.getCoordX(), c.getCoordY());

            if(!check)
            {
                Log.d("CarController initCar", "Cannot initialize car: " + c.getCarID());
            }
        }
    }

    public void setAllCars()
    {
        ArrayList<Car> carList = cd.getAllCars();
        double max = 0.000999;
        double min = -0.000999;
        double range = max - min;

        for(Car c: carList) {

            double random1 = Math.random() * range;
            double random2 = Math.random() * range;

            double x = 49.232000 + random1;

            double y = -123.023000 + random2;

            cd.updateCar(c.getCarID(), x, y);
        }
    }
    /*@Override
    public void processFinish(String output) {
    }*/
//==================================================================================================
}

