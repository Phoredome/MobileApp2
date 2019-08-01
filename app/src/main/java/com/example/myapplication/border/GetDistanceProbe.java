package com.example.myapplication.border;

import android.os.AsyncTask;
import android.util.Log;

import com.example.myapplication.border.dao.ISync;
import com.example.myapplication.entities.Car;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class GetDistanceProbe extends AsyncTask<String, Void, String> {

    private final String TAG = this.getClass().getName();
    public DistanceListener dlistener;
    public ISync delegate = null;


    public GetDistanceProbe(DistanceListener dlistener) {
        this.dlistener = dlistener;
    }

    protected String doInBackground(String ...params) {
        try {
            // This is getting the url from the string we passed in
            URL url = new URL(params[0]);

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

    // automatically called by doInBackground
    @Override
    protected void onPostExecute(String result) {
        dlistener.getDistance(result);    // this is calling the callback
        delegate.processFinish(result);
    }

    public interface DistanceListener {
        void getDistance(String result);
    }
}
