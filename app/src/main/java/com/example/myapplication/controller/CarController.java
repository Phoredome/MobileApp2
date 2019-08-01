package com.example.myapplication.controller;

import android.content.Context;
import android.util.Log;

import com.example.myapplication.border.dao.CarDAO;
import com.example.myapplication.border.pages.CreateCar;
import com.example.myapplication.border.info.GetDistanceProbe;
import com.example.myapplication.manager.DistanceCalculatorManager;
import com.example.myapplication.entities.Car;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

public class CarController {

    CarDAO cd;
    MapController mc;
    CreateCar cc;
    GetDistanceProbe dl;

    double depotx = 0.00;
    double depoty = 0.00;
    int NUMBEROFCARS = 10;

    /*GetDistanceProbe.DistanceListener context;
    GetDistanceProbe asyncTask =new GetDistanceProbe(context);*/

    public CarController(Context context) {
        cd = new CarDAO(context);
        mc = new MapController();
    }

    public CarController(){

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
    public boolean carToDepot(Car car)
    {
        if(!car.isInUse())
        {
            car.setInActiveService(false);
            car.setKmsSinceLastService(0);

            return true;
        }
        return false;
    }

    public void getCarDistance(GetDistanceProbe.DistanceListener context)
    {
        ArrayList<Car> carList = cd.getAllCars();
        double[][] distanceList = new double[NUMBEROFCARS][2];

        for (Car c : carList)
        {
            double x = c.getCoordX();
            double y = c.getCoordY();

            getNearByLocation(context, c, x, y);
            compareDist(distanceList, carList);
        }
    }
    //TODO
    public ArrayList<Car> getNearByCar (GetDistanceProbe.DistanceListener context, Car c)
    { getNearByLocation(context,c,c.getCoordX(),c.getCoordY());
    return null;}

    public void getNearByLocation (GetDistanceProbe.DistanceListener context,Car c, double x, double y)
    {
        ArrayList<Car> carList = cd.getAllCars();

        for (Car d : carList) {
            if(c.getCarID() != d.getCarID()) {
                double x2 = d.getCoordX();
                double y2 = d.getCoordY();

                DistanceCalculatorManager dcm = new DistanceCalculatorManager(d);
                dcm.startSearch(context, x, y, x2, y2);
            }
            //give me distance of car from location
            //TODO*/
        }

    }
    
    public double[][] compareDist(double[][] distanceList, ArrayList<Car> carList)
    {
            for(Car c : carList)
            {
                double dist = c.getDistance();
                if(checkDist(distanceList, dist));
                orderedInsert(distanceList, 0, dist);
            }
            return distanceList;
    }

    public boolean checkDist(double[][] distanceList,double dist)
    {
        for(int j = 0; j < NUMBEROFCARS; j++)
            if(dist < distanceList[j][2])
                return true;

            return false;
    }

    public int orderedInsert (double[][] distanceList, int first, double target)
    {
        // insert target into arr such that arr[first..last] is sorted,
        //   given that arr[first..last-1] is already sorted.
        //   Return the position where inserted.
        int i = NUMBEROFCARS;
        while ((i > first) && (target < distanceList[i-1][2]))
        {
            distanceList[i][2] = distanceList[i-1][2];
            i = i - 1;
        }
        distanceList[i][2] = target;
        return i;
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
