package com.example.myapplication.entities;

public class Station
{
    private int stationId;
    private int carsAtStation;
    private double locationX;
    private double locationY;

    private boolean stationActive;

    public Station(int stationId, int carsAtStation, double locationx, double locationY, boolean stationActive) {
        this.stationId = stationId;
        this.carsAtStation = carsAtStation;
        this.locationX = locationx;
        this.locationY = locationY;
        this.stationActive = stationActive;
    }

    public int getStationId() {
        return stationId;
    }

    public void setStationId(int stationId) {
        this.stationId = stationId;
    }

    public int getCarsAtStation() {
        return carsAtStation;
    }

    public void setCarsAtStation(int carsAtStation) {
        this.carsAtStation = carsAtStation;
    }

    public double getLocationx() {
        return locationX;
    }

    public void setLocationx(double locationx) {
        this.locationX = locationx;
    }

    public double getLocationY() {
        return locationY;
    }

    public void setLocationY(double locationY) {
        this.locationY = locationY;
    }

    public boolean isStationActive() {
        return stationActive;
    }

    public void setStationActive(boolean stationActive) {
        this.stationActive = stationActive;
    }
}
