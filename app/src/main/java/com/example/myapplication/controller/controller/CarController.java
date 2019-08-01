package com.example.myapplication.controller.controller;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.example.myapplication.border.dao.CarDAO;
import com.example.myapplication.border.pages.CreateCar;
import com.example.myapplication.border.info.GetDistanceProbe;
import com.example.myapplication.controller.manager.DistanceCalculatorManager;
import com.example.myapplication.entities.Car;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

public class CarController{

    CarDAO cd;
    MapController mc;
    CreateCar cc;

    double depotx = 0.00;
    double depoty = 0.00;

    /*GetDistanceProbe.DistanceListener context;
    GetDistanceProbe asyncTask =new GetDistanceProbe(context);*/

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
        for (int i = 0; i < carList.size(); i++)
        {
            Car c = carList.get(i);

            double x1 = c.getCoordX();
            double y1 = c.getCoordY();

            for(int j = i+1; j < carList.size(); j++)
            {
                Car d = carList.get(j);

                double x2 = d.getCoordX();
                double y2 = d.getCoordY();


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

    //TODO
    public void getNearByLocation (GetDistanceProbe.DistanceListener context, LatLng latLng) {
        //ArrayList<AsyncTask<String, Void, String>> dcm = new ArrayList<AsyncTask<String, Void, String>>();
        ArrayList<Car> carList = cd.getAllCars();

        for (Car c : carList) {
            double x = c.getCoordX();
            double y = c.getCoordY();

            DistanceCalculatorManager dcm = new DistanceCalculatorManager(c);
            dcm.startSearch(context,c, latLng.latitude, latLng.longitude, x, y);
            //give me distance of car from location
            //TODO*/

            double[][] list = new double[10][2];

            for (int i = 0; i < 10; i++) {
                double dist = list[i][2];

                if (c.getDistance() < dist && i < 10) {
                    while (i < 10) {
                        updateList(i, list, c, c.getDistance());
                        i++;
                    }

                    list[i][1] = c.getCarID();
                    list[i][2] = c.getDistance();
                }
            }
        }
    }

    public double[][] updateList(int i, double[][] list, Car c, double distance)
    {
        if(i == 9)
        {
            return list;
        }
        list[i + 1] = list[i];
        i++;

        updateList(i,list,c,distance);
        return list;

            //TODO compare cars list to search location, return top 10-15 closest cars using googleMatrix api (or any way that may be easier)

    }
    
    //TODO
    public ArrayList<Car> getNearByCars (GetDistanceProbe.DistanceListener context, LatLng latLng)
    {
        ArrayList<Car> carList = cd.getAllCars();
        ArrayList<Car> nearByCars = cd.getAllCars();
        Car c = null;
        double x = 0;
        double y = 0;

        for (int i = 0; i < carList.size(); i++)
        {
            c = carList.get(i);

            double x1 = c.getCoordX();
            double y1 = c.getCoordY();

            for(int j = i+1; j < carList.size(); j++)
            {
                Car d = carList.get(j);

                double x2 = d.getCoordX();
                double y2 = d.getCoordY();


            }

        }
        //asyncTask.delegate = this;

        DistanceCalculatorManager dcm = new DistanceCalculatorManager(c);
        dcm.startSearch(context,c, latLng.latitude, latLng.longitude, x, y);

        //TODO compare cars list to search location, return top 10-15 closest cars using googleMatrix api (or any way that may be easier)
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


    /*@Override
    public void processFinish(String output) {

    }*/
}
