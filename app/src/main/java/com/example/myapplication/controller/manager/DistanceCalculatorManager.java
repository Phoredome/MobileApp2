package com.example.myapplication.controller.manager;

import android.util.Log;

import com.example.myapplication.border.info.GetDistanceProbe;

import org.json.JSONException;
import org.json.JSONObject;

public class DistanceCalculatorManager
{

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
        GetDistanceProbe gdw = new GetDistanceProbe(context);
        gdw.execute(mSite); // will call doInBackground
    }

    // this is for the callback
    public double getDistance(String result) {
        // time to get the distance
        String dist = "0";
        try {
            JSONObject jo = new JSONObject(result);
            dist = jo.getJSONObject("route").getString("distance");
        } catch (JSONException e) {
            Log.d("error", e.getMessage());
        }
        return Double.parseDouble(dist); //in miles
    }
}
