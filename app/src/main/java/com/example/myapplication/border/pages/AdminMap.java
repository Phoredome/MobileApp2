package com.example.myapplication.border.pages;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.myapplication.R;
import com.example.myapplication.controller.CarController;
import com.example.myapplication.controller.RecyclerViewAdapter;
import com.example.myapplication.entities.Car;
import com.example.myapplication.manager.DistanceCalculatorManager;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class AdminMap extends AppCompatActivity implements OnMapReadyCallback  {

    private MapView mapView;
    private GoogleMap gmap;
    Bundle b;
    CarController cc;

    private static final String MAP_VIEW_BUNDLE_KEY = "MapViewBundleKey";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.activity_admin_map);

        cc = new CarController(getApplicationContext());
        Intent i = getIntent();
        b = i.getExtras();

        Boolean status = b.getBoolean("status");

        final NavigationView navigationView = findViewById(R.id.nav_admin_map);


        if(status){
            navigationView.inflateMenu(R.menu.activity_admin_drawer);
            Log.d("admin map1", status.toString());

        } else {
            navigationView.inflateMenu(R.menu.activity_main_drawer);
            Log.d("admin map2", status.toString());

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

                Intent i = null;
                int id = menuItem.getItemId();

                switch (id){
                    case R.id.nav_home:
                        i = new Intent(getApplicationContext(), MainActivity.class);
                        break;
                    case R.id.nav_account:
                        i = new Intent(getApplicationContext(), myAccount.class);
                        break;
                    case R.id.nav_history:
                        i = new Intent(getApplicationContext(), TripHistory.class);
                        break;
                    case R.id.nav_car_controller:
                        i = new Intent(getApplicationContext(), AdminMap.class);
                        break;
                    case R.id.nav_car_info:
                        i = new Intent(getApplicationContext(), AdminCarInfo.class);
                        break;
                }
                return false;

            }
        });

        Button redistributeCar = findViewById(R.id.redistributeBtn);

        redistributeCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), DistanceCalculatorManager.class);
                startActivity(i);
                finish();
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

        RecyclerView rv = findViewById(R.id.adminMapRecyclerView);
        ArrayList<Car> allCars = cc.getAllCars();
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(allCars, getApplication(), gmap);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(adapter);

        gmap.setMinZoomPreference(12);
        LatLng van = new LatLng(49.267279, -123.218318);
        gmap.moveCamera(CameraUpdateFactory.newLatLng(van));
        cc.initializeCars(gmap);

    }


}