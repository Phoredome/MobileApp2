package com.example.myapplication.border.info;
import com.example.myapplication.controller.CarController;
import com.example.myapplication.entities.Car;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.Marker;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;



public class InfoWindow extends AppCompatActivity implements
        GoogleMap.OnInfoWindowClickListener,
        GoogleMap.InfoWindowAdapter,
        OnMapReadyCallback {

    Car carObj;
    private GoogleMap mMap;
    CarController cc;

    public InfoWindow(Car car) {
        carObj = car;
    }


    @Override
    public View getInfoContents(Marker marker) {
       /* CarController cc = (CarController) marker.getTag();

        View v = LayoutInflater.from(getApplicationContext())
        //        .inflate(R.layout.content_info_window, map, false);

        TextView carId = v.findViewById(R.id.carIdTxt);
        TextView licPlate = v.findViewById(R.id.licensePlateTxt);
        */
        return null;

    }

    @Override
    public View getInfoWindow(Marker marker) {
        return null;
    }


    @Override
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

}
