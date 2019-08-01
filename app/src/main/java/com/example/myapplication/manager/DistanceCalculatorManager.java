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
    private String site = "https://www.mapquestapi.com/directions/v2/route?";
    private String myKey = "c8ZKDbXkXeFKJh8ACe0R2zSQxBbVo7OF";


    public String assembleURL(String from, String to) {
        String result ="" ;
        result = site  + "key=" + myKey + "&from=" + from + "&to=" + to + "&outFormat=json&ambiguities=ignore&routeType=fastest";

        return result;
    }

    public void startSearch(GetDistanceProbe.DistanceListener context, double fromLat, double fromLng, double toLat, double toLng)
    {

        String from = fromLat + "," + fromLng;
        String to = toLat + "," + toLng;

        String mSite = assembleURL(from, to);
        AsyncTask<String, Void, String>  gdw = new GetDistanceProbe(context);
        gdw.execute(mSite); // will call doInBackground
    }

    // this is for the callback

    public void getDistance(String result) {
        // time to get the distance
        try {
            JSONObject jo = new JSONObject(result);
            String dist = jo.getJSONObject("route").getString("distance");
            Log.d("getDistance()", dist);
            c.setDistance(Integer.parseInt(dist));
        } catch (JSONException e) {
            Log.d("error", e.getMessage());
        }
    }
}
