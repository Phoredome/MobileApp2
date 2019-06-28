package com.example.myapplication.Entities;

public class Trips {

    private int tripId;
    private int carId;
    private int userId;
    private int kmsRunForTrip;

    private String dateTimeOfTrip;

    private double amount;

    public Trips(int tripId, int carId, int userId, double amount, int kmsRunForTrip, String dateTimeOfTrip) {
        this.tripId = tripId;
        this.carId = carId;
        this.userId = userId;
        this.amount = amount;
        this.kmsRunForTrip = kmsRunForTrip;
        this.dateTimeOfTrip = dateTimeOfTrip;
    }

    public int getTripId() {
        return tripId;
    }

    public void setTripId(int tripId) {
        this.tripId = tripId;
    }

    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getKmsRunForTrip() {
        return kmsRunForTrip;
    }

    public void setKmsRunForTrip(int kmsRunForTrip) {
        this.kmsRunForTrip = kmsRunForTrip;
    }

    public String getDateTimeOfTrip() {
        return dateTimeOfTrip;
    }

    public void setDateTimeOfTrip(String dateTimeOfTrip) {
        this.dateTimeOfTrip = dateTimeOfTrip;
    }


}
