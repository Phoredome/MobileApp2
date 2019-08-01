package com.example.myapplication.border.info;
import com.example.myapplication.R;
import com.example.myapplication.controller.CarController;
import com.example.myapplication.entities.Car;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.Marker;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;


public class InfoWindow implements GoogleMap.InfoWindowAdapter
{

    private ArrayList<Car> list;
    CarController cc;
    Car carObj;
    Context context;
    LayoutInflater inflater;

    public InfoWindow(ArrayList<Car> list, Context context) {
        this.list = list;
        this.context = context.getApplicationContext();
    }


    @Override
    public View getInfoWindow(Marker marker) {
        return null;
    }

    @Override
    public View getInfoContents(Marker marker) {
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.activity_info_window, null);

        TextView carId = (TextView) v.findViewById(R.id.carIdTxt);
        TextView licP = (TextView) v.findViewById(R.id.licensePlateTxt);

        Log.d("marker ID", marker.getId());

        String markerID = marker.getId().substring(1);
        carId.setText(String.valueOf(list.get(Integer.parseInt(markerID)-1).getCarID()));
        licP.setText(list.get(Integer.parseInt(markerID)-1).getLicensePlate());

        return v;

        //get marker location
        //get all cars object
        //find the car at marker location


    }

/*
    @Override
    public void onInfoWindowClick(Marker marker) {
        Log.d("OnInfoWindowClick", "Hi! " + marker);

        Toast.makeText(this, "Info window clicked",
                Toast.LENGTH_SHORT).show();
    }
*/

}
