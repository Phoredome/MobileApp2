package com.example.myapplication.Controller;

public class CarRemote implements Runnable {

    double xCoord;
    double yCoord;

    CarRemote(double xCoord, double yCoord)
    {
        this.xCoord = xCoord;
        this.yCoord = yCoord;
    }

    public void run()
    {

    }

    public void hi()
    {
        CarRemote cr = new CarRemote(143.00, 150.00);
        new Thread(cr).start();
    }
}
