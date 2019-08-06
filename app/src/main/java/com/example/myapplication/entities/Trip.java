package com.example.myapplication.entities;

public class Trip {

    private int tripId;
    private int carId;
    private int userId;
    private int kmsRunForTrip;

    private String timeOfTrip;
    private String dateOfTrip;

    private double amount;
    private double startingX;
    private double startingY;
    private double endingX;
    private double endingY;

    public Trip(int tripId, int carId, int userId, int kmsRunForTrip, String timeOfTrip, String dateOfTrip, double amount, double startingX, double startingY, double endingX, double endingY) {
        this.tripId = tripId;
        this.carId = carId;
        this.userId = userId;
        this.kmsRunForTrip = kmsRunForTrip;
        this.timeOfTrip = timeOfTrip;
        this.dateOfTrip = dateOfTrip;
        this.amount = amount;
        this.startingX = startingX;
        this.startingY = startingY;
        this.endingX = endingX;
        this.endingY = endingY;
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

    public String getTimeOfTrip() {
        return timeOfTrip;
    }

    public void setTimeOfTrip(String timeOfTrip) {
        this.timeOfTrip = timeOfTrip;
    }

    public String getDateOfTrip() {
        return dateOfTrip;
    }

    public void setDateOfTrip(String dateOfTrip) {
        this.dateOfTrip = dateOfTrip;
    }

    public double getStartingX() {
        return startingX;
    }

    public void setStartingX(double startingX) {
        this.startingX = startingX;
    }

    public double getStartingY() {
        return startingY;
    }

    public void setStartingY(double startingY) {
        this.startingY = startingY;
    }

    public double getEndingX() {
        return endingX;
    }

    public void setEndingX(double endingX) {
        this.endingX = endingX;
    }

    public double getEndingY() {
        return endingY;
    }

    public void setEndingY(double endingY) {
        this.endingY = endingY;
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


}
