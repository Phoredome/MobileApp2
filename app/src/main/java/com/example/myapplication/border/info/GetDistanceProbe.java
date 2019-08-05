package com.example.myapplication.border.info;

import android.os.AsyncTask;
import android.util.Log;

import com.example.myapplication.entities.Car;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class GetDistanceProbe extends AsyncTask<ArrayList<Car>, Void, ArrayList<Car>> {

    private double x;
    private double y;
    private final String TAG = this.getClass().getName();
    public DistanceListener dlistener;

    private final int LISTSIZE = 10;

    public GetDistanceProbe(DistanceListener dlistener) {
        this.dlistener = dlistener;
    }

    public void setOrigin(double x, double y) {
        this.x = x;
        this.y = y;
    }

    @Override
    protected ArrayList<Car> doInBackground(ArrayList<Car> ...params) {

        ArrayList<Car> cars = new ArrayList<>();

        ArrayList<Future<Car>> futuristicCars = new ArrayList<>();

        ExecutorService pool = Executors.newCachedThreadPool();
        Log.d("inside do in background", "do in background");

        for (Car car : params[0])
        {
            callDistance(x,y,car);
        }

        for (int i = 0; i < LISTSIZE; i++)
        {
            Car c = null;
            for (int j = 0; j < params[0].size(); j++)
            {
                Car futureCar = params[0].get(j);
                if(c == null || c.getDistance() < futureCar.getDistance())
                {
                    params[0].remove(j);
                    c = futureCar;

                }
            }
            if(c != null)
                cars.add(c);
        }
        return cars;
    }

    public Car callDistance(double x, double y, Car c)
    {
        String from = x + "," + y;
        String to = c.getCoordX() + "," + c.getCoordY();

        String mSite = assembleURL(from, to);
        String result = null;
        try{
            result = reachSite(new URL(mSite));
        } catch (MalformedURLException me) {Log.d("GDP CallDistance", me.toString());}
        if(result == null)
        {
            Log.d("GDP result", result + " break in callDistance");
        }
        c.setDistance(extractDistance(result));

        return c;
    }

    //ASync Methods
    //===========================================================================================================================
    private String site = "https://www.mapquestapi.com/directions/v2/route?";
    private String myKey = "Dwer6PDU0As4DxxJv4go5I9LWPi7K4VS";


    public String assembleURL(String from, String to) {
        String result = "";
        result = site + "key=" + myKey + "&from=" + from + "&to=" + to + "&outFormat=json&ambiguities=ignore&routeType=fastest";

        return result;
    }

    protected String reachSite(URL url) {
        try {
            // This is getting the url from the string we passed in

            // Create the urlConnection
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setDoInput(true);

            urlConnection.setRequestProperty("Content-Type", "application/json");

            int statusCode = urlConnection.getResponseCode();

            if (statusCode ==  200) {

                InputStream inputStream = new BufferedInputStream(urlConnection.getInputStream());

                BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(inputStream));
                StringBuilder sb = new StringBuilder();
                String line;
                try {
                    while((line = bufferedReader.readLine()) != null) {
                        sb.append(line);
                    }
                } catch (IOException e) {
                    Log.d(TAG, e.getMessage());
                }
                Log.d(TAG, sb.toString());
                return sb.toString();

            } else {
                // Status code is not 200
                // Do something to handle the error
            }

        } catch (Exception e) {
            Log.d(TAG, e.getMessage());
        }
        return "";
    }

    private Double extractDistance(String result)
    {
        String dist = null;
        try {
            JSONObject jo = new JSONObject(result);
            dist = jo.getJSONObject("route").getString("distance");

        } catch (JSONException e) {
            Log.d("error", e.getMessage());
        }
        return Double.parseDouble(dist);
    }

    // automatically called by doInBackground
    @Override
    protected void onPostExecute(ArrayList<Car> result) {
        dlistener.setNearByList(result);    // this is calling the callback
    }

    public interface DistanceListener {
        void setNearByList(ArrayList<Car> result);
    }
}
