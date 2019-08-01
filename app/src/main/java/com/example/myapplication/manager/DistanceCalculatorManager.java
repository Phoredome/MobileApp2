package com.example.myapplication.manager;

import android.os.AsyncTask;
import android.util.Log;

import com.example.myapplication.entities.Car;

import com.example.myapplication.border.info.GetDistanceProbe;
import org.json.JSONException;
import org.json.JSONObject;

public class DistanceCalculatorManager
{

    public Car c;
    public DistanceCalculatorManager(Car c)
    {
        this.c = c;
    }

}
