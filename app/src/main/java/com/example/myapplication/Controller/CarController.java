package com.example.myapplication.Controller;

import com.example.myapplication.Border.CarDAO;
import com.example.myapplication.Entities.Car;
import com.example.myapplication.R;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.sql.SQLException;
import java.util.ArrayList;

public class CarController {

    CarDAO cd;
    MapController mc;
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

    // will use other methods to aid in redistributing unused car locations
    public Boolean equalize()
    {
        //TODO
        ArrayList<Car> carList = cd.getCars();
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
    public void moveCar(GoogleMap map, Car car, double xCoord, double yCoord)
    {
        //Figure out what data type to use for getting location, X and Y or a concatenation?
        car.setCoordX(xCoord);
        car.setCoordY(yCoord);

        //TODO Refresh map
        mc.updateCarMarker(map, car, xCoord, yCoord);
    }

    /**
     * @param car Id of car intended to be sent to depot for repairs/servicing
     */
    public boolean carToDepot(Car car)
    {
        if(car.isInUse())
        {
            car.setInService(false);
            car.setKmsSinceLastService(0);

            return true;
        }
        return false;
    }


}
