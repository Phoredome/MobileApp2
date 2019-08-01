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



public class InfoWindow implements GoogleMap.InfoWindowAdapter {
        //GoogleMap.OnInfoWindowClickListener,
        //OnMapReadyCallback {


    private GoogleMap mMap;
    CarController cc;
    Car carObj;
    Context context;
    LayoutInflater inflater;

    public InfoWindow(Context context) {
        this.context = context;
    }


    @Override
    public View getInfoContents(Marker marker) {
        return null;
    }

    @Override
    public View getInfoWindow(Marker marker) {
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.activity_info_window, null);

        TextView carId = (TextView) v.findViewById(R.id.carIdTxt);
        TextView licP = (TextView) v.findViewById(R.id.licensePlateTxt);

        carId.setText(marker.getSnippet());
        licP.setText(marker.getSnippet());

        return v;

        //get marker location
        //get all cars object
        //find the car at marker location


    }


   /* @Override
    public void onMapReady(GoogleMap map) {
        mMap = map;
        // Add markers to the map and do other map setup.

        // Set a listener for info window events.
        mMap.setOnInfoWindowClickListener(this);
    }

    @Override
    public void onInfoWindowClick(Marker marker) {
        Log.d("OnInfoWindowClick", "Hi! " + marker);

        Toast.makeText(this, "Info window clicked",
                Toast.LENGTH_SHORT).show();
    }
*/

}
