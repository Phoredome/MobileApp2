package com.example.myapplication.entities;

public class Station
{
    private int stationID;
    private int carsAtStation;
    private int locationx;
    private int locationy;

    private boolean stationActive;


    public Station(int stationID, int carsAtStation, int locationx, int locationy, boolean stationActive) {
        this.stationID = stationID;
        this.carsAtStation = carsAtStation;
        this.locationx = locationx;
        this.locationy = locationy;
        this.stationActive = stationActive;
    }

    public int getStationID() {
        return stationID;
    }

    public void setStationID(int stationID) {
        this.stationID = stationID;
    }

    public int getCarsAtStation() {
        return carsAtStation;
    }

    public void setCarsAtStation(int carsAtStation) {
        this.carsAtStation = carsAtStation;
    }

    public int getLocationx() {
        return locationx;
    }

    public void setLocationx(int locationx) {
        this.locationx = locationx;
    }

    public int getLocationy() {
        return locationy;
    }

    public void setLocationy(int locationy) {
        this.locationy = locationy;
    }

    public boolean isStationActive() {
        return stationActive;
    }

    public void setStationActive(boolean stationActive) {
        this.stationActive = stationActive;
    }
}
