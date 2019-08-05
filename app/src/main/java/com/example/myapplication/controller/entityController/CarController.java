package com.example.myapplication.controller.entityController;

import android.content.Context;
import android.util.Log;

import com.example.myapplication.border.dao.CarDAO;
import com.example.myapplication.border.pages.CreateCar;
import com.example.myapplication.border.info.GetDistanceProbe;
import com.example.myapplication.entities.Car;
import com.example.myapplication.entities.Station;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

public class CarController{

    public CarDAO cd;
    public MapController mc;
    public CreateCar cc;
    public Car car;
    public StationController sc;

    double depotx = 0.00;
    double depoty = 0.00;
    int NUMBEROFCARS = 10;

    Context context;

    /*GetDistanceProbe.DistanceListener context;
    GetDistanceProbe asyncTask =new GetDistanceProbe(context);*/

    public CarController(Context context) {
        cd = new CarDAO(context);
        mc = new MapController();
        sc = new StationController(context);

        this.context = context;
    }

    public Car getCarById(int carId) {
        return cd.getCarById(carId);
    }


    public ArrayList<String> getCarTypes() {
        return cd.getCarTypes();
    }


    public ArrayList<Car> getAllCars()
    {
        return cd.getAllCars();
    }

    public int getCarCount()
    {return cd.getCarCount();}

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
    public void updateCar(int carNum,
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
        cd.updateCarInfo(carNum,costOfRunning,seats,doors,serviceTime,kmsRun,kmSinceLastService,vehicleType,licensePlate,inUse,inService,coordX,coordY);
    }
    public boolean editCar(int carNum,
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
        cd.updateCarInfo(carNum,costOfRunning,seats,doors,serviceTime,kmsRun,kmSinceLastService,vehicleType,licensePlate,inUse,inService,coordX,coordY);
        return true;
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
            cd.updateCarLocation(car.getCarID(),xCoord,yCoord);
        }

        mc.updateCarMarker(map, car, xCoord, yCoord);
        return true;
    }

    public boolean transferCar(Car c, Station destinationStation)
    {
        if(!car.isInUse() || car.isInActiveService()) {
            car.setCoordX(destinationStation.getLocationX());
            car.setCoordY(destinationStation.getLocationY());
            cd.updateCarLocation(car.getCarID(), destinationStation.getLocationX(), destinationStation.getLocationY());
        }

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
            Log.d("Sent to service", "Sent");
            return true;
        }
        return false;
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

    public void getTripDistance(GetDistanceProbe.DistanceListener dlistener, Car c, LatLng ll)
    {
        getCarDistances(dlistener, c, ll.latitude, ll.longitude);
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
        double max = 0.009999;
        double min = -0.009999;
        double range = max - min;

        for(Car c: carList) {

            double random1 = Math.random() * range;
            double random2 = Math.random() * range;

            double x = 49.232000 + random1;

            double y = -123.023000 + random2;

            cd.updateCarLocation(c.getCarID(), x, y);
        }
    }

    public void redistribute() {
        ArrayList<Station> stationList = sc.getAllStations();
        //calls to check number of cars in all stations
        int avg = sc.countCarsInStation(stationList, cd.getAllCars());
        // returns average in number of cars
        ArrayList<Car> usableCars = getUsableCars(stationList, avg);

        for(int i = 0; i < usableCars.size(); i++)
            for (Station s : stationList) {
                if (s.isStationActive()) {
                    int carCount = s.getCarsAtStation();
                    while(carCount < avg){
                        transferCar(usableCars.get(i),s);
                        carCount++;
                    }
                }
            }
        sc.countCarsInStation(stationList, cd.getAllCars());
        for (Station s : stationList) {
            if (s.isStationActive()) {
                int carCount = s.getCarsAtStation();
                while (carCount < avg) {
                    for (Car c: cd.getAllCars()) {
                        if(!c.isInUse()&&c.isInActiveService()&&!c.isInStation()) {
                            transferCar(c, s);
                            carCount++;
                        }

                    }
                }
            }
        }
        //===========================================================
        //if value is greater than number of cars at station avg
        //redistribute
    }

    public ArrayList<Car> getUsableCars(ArrayList<Station> stationList, int avg) {
        ArrayList<Station> crowded = null;
        ArrayList<Car> movableCars = null;
        for (Station s : stationList) {
            if (s.isStationActive()) {
                int carCount = s.getCarsAtStation();
                int usableCars;
                if (carCount > avg) {
                    ArrayList<Car> stationCars = null;
                    for(Car c : cd.getAllCarsByStation(s))
                        stationCars.add(c);

                    usableCars = carCount - avg;
                    for(int i = 0; i < usableCars; i++)
                        movableCars.add(stationCars.get(i));
                }
            }
        }
        return movableCars;
    }

    public void setCarRates(double scRate, double crvRate, double vanRate)
    {
        for (Car c : cd.getAllCars()) {
            cd.updateCarRate(c, scRate,crvRate,vanRate);

        }
    }

    public Double findCarRate(String carType) {
        for (Car c : cd.getAllCars()) {
            if(c.getVehicleType().equals(carType)) {
                Log.d("CarController - findCarRate" , "" + c.getCostOfRunning());
               return c.getCostOfRunning();
            }
        }
        return 0.0;

    }
}

