package com.example.myapplication.Controller;

import com.example.myapplication.Border.CarDAO;
import com.example.myapplication.Entities.Car;

import java.sql.SQLException;

public class CarController {

    CarDAO cd;
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
    public void moveCar(Car car, double xCoord, double yCoord)
    {
        //Figure out what data type to use for getting location, X and Y or a concatenation?
        car.setCoordX(xCoord);
        car.setCoordY(yCoord);

        //TODO Refresh map
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
