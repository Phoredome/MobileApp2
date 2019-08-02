package com.example.myapplication.border.info;

import android.os.AsyncTask;
import android.util.Log;

import com.example.myapplication.entities.Car;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class GetDistanceProbe extends AsyncTask<ArrayList<Future<Car>>, Void, ArrayList<Car>> {

    private final String TAG = this.getClass().getName();
    public DistanceListener dlistener;

    private final int LISTSIZE = 10;

    public GetDistanceProbe(DistanceListener dlistener) {
        this.dlistener = dlistener;
    }

    @Override
    protected ArrayList<Car> doInBackground(ArrayList<Future<Car>> ...params) {
        ArrayList<Car> cars = new ArrayList<>();

        ArrayList<Car> carList = new ArrayList<>();
        for (Future<Car> f:params[0])
        {
            try
            {
            carList.add(f.get());
            }
            catch (ExecutionException | InterruptedException REEE)
            {
                Log.d("GetDistanceProbe", "CAR:" + carList.size());
                Log.d("GetDistanceProbe", REEE.getMessage());
            }
        }

        for (int i = 0; i < LISTSIZE; i++)
        {
            Car c = null;
            for (int j = 0; j <carList.size(); j++)
            {
                Car futureCar = carList.get(j);
                if(c == null || c.getDistance() < futureCar.getDistance())
                {
                    carList.remove(j);
                    c = futureCar;
                }
            }
            if(c != null)
                cars.add(c);
        }
        return cars;
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
