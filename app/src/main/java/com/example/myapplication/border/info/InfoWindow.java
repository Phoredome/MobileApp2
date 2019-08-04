package com.example.myapplication.border.info;
import com.example.myapplication.R;
import com.example.myapplication.border.dao.CarDAO;
import com.example.myapplication.controller.CarController;
import com.example.myapplication.controller.MapController;
import com.example.myapplication.controller.StationController;
import com.example.myapplication.controller.TripController;
import com.example.myapplication.entities.Car;
import com.example.myapplication.entities.Station;
import com.example.myapplication.entities.User;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;


public class InfoWindow implements GoogleMap.InfoWindowAdapter {

    private ArrayList<Car> list;
    GoogleMap gmap;
    CarController cc;
    MapController mc;
    Car carObj;
    CarDAO cd;
    Context context;
    LayoutInflater inflater;
    User user;
    StationController sc;
    TripController tc;


    public InfoWindow(GoogleMap gmap, User user, ArrayList<Car> list, Context context) {
        this.list = list;
        this.gmap = gmap;
        this.user = user;
        this.context = context.getApplicationContext();
        mc = new MapController();
        cc = new CarController(context);
        sc = new StationController(context);
        tc = new TripController(context);
        //cd = new CarDAO(context);
    }


    @Override
    public View getInfoWindow(Marker marker) {


        return null;
    }

    @Override
    public View getInfoContents(Marker marker) {
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.activity_info_window, null);

        final TextView carId = (TextView) v.findViewById(R.id.carIdTxt);
        TextView licP = (TextView) v.findViewById(R.id.licensePlateTxt);
        final EditText location = (EditText) v.findViewById(R.id.locationInput);

        Log.d("marker ID", marker.getId());

        String markerID = marker.getId().substring(1);
        int markerId = (list.get(Integer.parseInt(markerID)-1).getCarID());
        carId.setText(String.valueOf(markerId));
        licP.setText(list.get(Integer.parseInt(markerID)-1).getLicensePlate());
        final Car c = cc.getCarById(Integer.parseInt(carId.getText().toString()));

        Button service = v.findViewById(R.id.serviceBtn);
        service.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cc.serviceCar(c);
            };

        });

        Button sendToLot = v.findViewById(R.id.sendLotBtn);
        sendToLot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Station s = sc.getStationByID(0);

                cc.moveCar(gmap, c, s.getLocationX(), s.getLocationY());

            }
        });

        Button go = v.findViewById(R.id.goBtn);

        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LatLng ll = mc.getLocationFromAddress(context, location.getText().toString());
                tc.addTrip(context, c, user, ll);
                cc.moveCar(gmap, c, ll.latitude, ll.longitude);
            }
        });

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
