package com.example.myapplication.border.pages;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.border.dao.TripDAO;
import com.example.myapplication.border.info.RecyclerItemClickListener;
import com.example.myapplication.controller.entityController.CarController;

import com.example.myapplication.controller.entityController.MapController;
import com.example.myapplication.controller.adapters.RecyclerViewAdapter_Trip;
import com.example.myapplication.controller.entityController.TripController;
import com.example.myapplication.entities.Trip;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class TripHistory extends AppCompatActivity implements OnMapReadyCallback {

    private MapView mapView;
    private GoogleMap gmap;
    Bundle a, b;
    CarController cc;
    TripController tc;
    TripDAO td;
    MapController mc;

    private static final String MAP_VIEW_BUNDLE_KEY = "MapViewBundleKey";
    private Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.activity_history);

        cc = new CarController(getApplicationContext());
        tc = new TripController(getApplicationContext());
        td = new TripDAO(getApplicationContext());
        mc = new MapController();

        Intent i = getIntent();

        b = i.getExtras();
        a = i.getExtras();

        Integer userId = a.getInt("userId");
        Boolean status = b.getBoolean("status");


        final NavigationView navigationView = findViewById(R.id.nav_history);

        if(status){
            navigationView.inflateMenu(R.menu.activity_admin_drawer);

        } else {
            navigationView.inflateMenu(R.menu.activity_main_drawer);
        }




        Bundle mapViewBundle = null;
        if (savedInstanceState != null) {
            mapViewBundle = savedInstanceState.getBundle(MAP_VIEW_BUNDLE_KEY);
        }

        mapView = findViewById(R.id.mapView);
        mapView.onCreate(mapViewBundle);
        mapView.getMapAsync(this);


        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {


                int id = menuItem.getItemId();
                Intent i = null;

                switch (id) {
                    case R.id.nav_home:
                        i = new Intent(getApplicationContext(), MainActivity.class);
                        break;
                    case R.id.nav_account:
                        i = new Intent(getApplicationContext(), MyAccount.class);
                        break;
                    case R.id.nav_history:
                        i = new Intent(getApplicationContext(), TripHistory.class);
                        break;
                    case R.id.nav_logout:
                        i = new Intent(TripHistory.this, LoginPage.class);
                        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(i);
                        finish();
                        break;
                    case R.id.nav_car_controller:
                        i = new Intent(getApplicationContext(), AdminCarController.class);
                        break;
                    case R.id.nav_car_info:
                        i = new Intent(getApplicationContext(), AdminCarInfo.class);
                        break;
                }

                if (i != null) {

                    i.putExtras(b);
                    i.putExtras(a);
                    startActivity(i);

                }

                return false;
            }
        });

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        Bundle mapViewBundle = outState.getBundle(MAP_VIEW_BUNDLE_KEY);
        if (mapViewBundle == null) {
            mapViewBundle = new Bundle();
            outState.putBundle(MAP_VIEW_BUNDLE_KEY, mapViewBundle);
        }

        mapView.onSaveInstanceState(mapViewBundle);
    }


    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mapView.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mapView.onStop();
    }

    @Override
    protected void onPause() {
        mapView.onPause();
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        mapView.onDestroy();
        super.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        gmap = googleMap;

        RecyclerView rv2 = findViewById(R.id.recyclerView2);
        ArrayList<Trip> allTrips = tc.getAllTrips();
        RecyclerViewAdapter_Trip adapter = new RecyclerViewAdapter_Trip(allTrips, getApplication(),gmap);
        rv2.setLayoutManager(new LinearLayoutManager(this));
        rv2.setAdapter(adapter);

        rv2.addOnItemTouchListener(
                new RecyclerItemClickListener(context, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        TextView carHistory = findViewById(R.id.historyInfo);

                        Trip t = td.getTripById(position+1);
                        StringBuilder sp = new StringBuilder();
                        sp.append("Trip id: " + t.getTripId()+ "\n");
                        sp.append("Trip date: " + t.getDateOfTrip()+ "\n");
                        sp.append("Trip timestamp: " + t.getTimeOfTrip()+ "\n");
                        sp.append("Trip amount: " + t.getAmount()+ "\n");
                        sp.append("Trip kilometers: " + t.getKmsRunForTrip());

                       carHistory.setText(sp);
                       mc.setMarker(gmap,"START", t.getStartingX(), t.getStartingY());
                       mc.setMarker(gmap, "FINISH", t.getEndingX(), t.getEndingY());
                       gmap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(t.getStartingX(), t.getStartingY())));
                    }
                })
        );


        gmap.setMinZoomPreference(12);
        LatLng van = new LatLng(49.267279, -123.218318);
        gmap.moveCamera(CameraUpdateFactory.newLatLng(van));
        // cc.initializeCars(gmap);
        // cc.moveCar(gmap, cc.getCarById(1), 40.7143527, -74.0059731);
    }





}