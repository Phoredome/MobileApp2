package com.example.myapplication.entities;

import androidx.annotation.Nullable;

public class Car {
    private int carID;
    private int seats;
    private int doors;
    private int serviceTime;
    private double kmsRun;
    private double kmsSinceLastService;

    private double distance;

    private double costOfRunning;
    private double coordX;
    private double coordY;

    private String vehicleType;
    private String licensePlate;

    private boolean inUse;
    private boolean inActiveService;
    private boolean inStation;

    public Car(int carID, double costOfRunning, int seats, int doors, int serviceTime, int kmsRun, int kmsSinceLastService, String vehicleType, String licensePlate, boolean inUse, boolean inActiveService, double coordX, double coordY, boolean inStation) {
        this.carID = carID;
        this.costOfRunning = costOfRunning;
        this.seats = seats;
        this.doors = doors;
        this.serviceTime = serviceTime;
        this.kmsRun = kmsRun;
        this.kmsSinceLastService = kmsSinceLastService;
        this.vehicleType = vehicleType;
        this.licensePlate = licensePlate;
        this.inUse = inUse;
        this.inActiveService = inActiveService;
        this.coordX = coordX;
        this.coordY = coordY;
        this.inStation = inStation;
    }

    public int getCarID() {
        return carID;
    }

    public void setCarID(int carID) {
        this.carID = carID;
    }

    public double getCostOfRunning() {
        return costOfRunning;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public int getDoors() {
        return doors;
    }

    public void setDoors(int doors) {
        this.doors = doors;
    }

    public int getServiceTime() {
        return serviceTime;
    }

    public void setServiceTime(int serviceTime) {
        this.serviceTime = serviceTime;
    }

    public double getKmsRun() {
        return kmsRun;
    }

    public void setKmsRun(double kmsRun) {
        this.kmsRun = kmsRun;
    }

    public double getKmsSinceLastService() {
        return kmsSinceLastService;
    }

    public void setKmsSinceLastService(double kmsSinceLastService) {
        this.kmsSinceLastService = kmsSinceLastService;
    }
    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public void setCostOfRunning(double costOfRunning) {
        this.costOfRunning = costOfRunning;
    }

    public double getCoordX() {
        return coordX;
    }

    public void setCoordX(double coordX) {
        this.coordX = coordX;
    }

    public double getCoordY() {
        return coordY;
    }

    public void setCoordY(double coordY) {
        this.coordY = coordY;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public boolean isInUse() {
        return inUse;
    }

    public void setInUse(boolean inUse) {
        this.inUse = inUse;
    }

    public boolean isInActiveService() {
        return inActiveService;
    }

    public void setInActiveService(boolean inActiveService) {
        this.inActiveService = inActiveService;
    }

    public boolean isInStation() {
        return inStation;
    }

    public void setInStation(boolean inStation) {
        this.inStation = inStation;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if (obj instanceof Car)
            return carID == ((Car) obj).carID;
        else
            return super.equals(obj);
    }
}
