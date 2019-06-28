package com.example.myapplication.Entities;

public class Car {
    private int carID;
    private int costOfRunning;
    private int seats;
    private int doors;
    private int capacity;
    private int serviceTime;
    private int kmsRun;
    private int kmsSinceLastService;

    private String vehicleType;
    private String licensePlate;

    private boolean inUse;
    private boolean inService;

    public Car(int carID, int costOfRunning, int seats, int doors, int capacity, int serviceTime, int kmsRun, int kmsSinceLastService, String vehicleType, String licensePlate, boolean inUse, boolean inService) {
        this.carID = carID;
        this.costOfRunning = costOfRunning;
        this.seats = seats;
        this.doors = doors;
        this.capacity = capacity;
        this.serviceTime = serviceTime;
        this.kmsRun = kmsRun;
        this.kmsSinceLastService = kmsSinceLastService;
        this.vehicleType = vehicleType;
        this.licensePlate = licensePlate;
        this.inUse = inUse;
        this.inService = inService;
    }

    public int getCarID() {
        return carID;
    }

    public void setCarID(int carID) {
        this.carID = carID;
    }

    public int getCostOfRunning() {
        return costOfRunning;
    }

    public void setCostOfRunning(int costOfRunning) {
        this.costOfRunning = costOfRunning;
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

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getServiceTime() {
        return serviceTime;
    }

    public void setServiceTime(int serviceTime) {
        this.serviceTime = serviceTime;
    }

    public int getKmsRun() {
        return kmsRun;
    }

    public void setKmsRun(int kmsRun) {
        this.kmsRun = kmsRun;
    }

    public int getKmsSinceLastService() {
        return kmsSinceLastService;
    }

    public void setKmsSinceLastService(int kmsSinceLastService) {
        this.kmsSinceLastService = kmsSinceLastService;
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

    public boolean isInService() {
        return inService;
    }

    public void setInService(boolean inService) {
        this.inService = inService;
    }
}
