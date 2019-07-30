package com.example.myapplication.controller;

import android.content.Context;
import android.util.Log;

import com.example.myapplication.border.CarDAO;
import com.example.myapplication.border.CreateCar;
import com.example.myapplication.border.GetDistanceProbe;
import com.example.myapplication.entities.Car;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

public class CarController {

    CarDAO cd;
    MapController mc;
    CreateCar cc;

    double depotx = 0.00;
    double depoty = 0.00;

    public CarController(Context context) {
        cd = new CarDAO(context);
        mc = new MapController();
    }

    public Car getCarById(int carId) {
        return cd.getCar(carId);
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
    public Boolean equalize()
    {
        //TODO
        ArrayList<Car> carList = cd.getAllCars();
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
        if(car.isInUse() || !car.isInService())
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
    public boolean carToDepot(Car car)
    {
        if(!car.isInUse())
        {
            car.setInService(false);
            car.setKmsSinceLastService(0);

            return true;
        }
        return false;
    }

    public ArrayList<Car> getNearByCars (GetDistanceProbe.DistanceListener context, LatLng latLng)
    {
        ArrayList<Car> nearByCars;

        nearByCars = cd.getAllCars();

        for (Car c: nearByCars)
        {
            double x = c.getCoordX();
            double y = c.getCoordY();

            DistanceCalculatorManager dcm = new DistanceCalculatorManager();
            dcm.startSearch(context, latLng.latitude, latLng.longitude, x, y);

            //TODO compare cars list to search location, return top 10-15 closest cars using googleMatrix api (or any way that may be easier)
        }
        return nearByCars;
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


}
