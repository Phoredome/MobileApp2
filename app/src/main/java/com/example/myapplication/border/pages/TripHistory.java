package com.example.myapplication.border.pages;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.controller.controller.CarController;

import com.example.myapplication.controller.RecyclerViewAdapter;
import com.example.myapplication.entities.Car;
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
    Bundle b;
    CarController cc;

    private static final String MAP_VIEW_BUNDLE_KEY = "MapViewBundleKey";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.activity_history);
        cc = new CarController(getApplicationContext());

        Intent i = getIntent();
        b = i.getExtras();

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

                switch (id){
                    case R.id.nav_home:
                        Intent i = new Intent(getApplicationContext(), MainActivity.class);
                        i.putExtras(b);
                        startActivity(i);
                        break;
                    case R.id.nav_account:
                        Intent j = new Intent(getApplicationContext(), myAccount.class);
                        j.putExtras(b);
                        startActivity(j);
                        break;
                    case R.id.nav_history:
                        Intent k = new Intent(getApplicationContext(), TripHistory.class);
                        k.putExtras(b);
                        startActivity(k);
                        break;
                    case R.id.nav_car_controller:
                        Intent a = new Intent(getApplicationContext(), AdminMap.class);
                        a.putExtras(b);
                        startActivity(a);
                        break;
                    case R.id.nav_car_info:
                        Intent n = new Intent(getApplicationContext(), AdminCarInfo.class);
                        n.putExtras(b);
                        startActivity(n);
                        break;
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
        ArrayList<Car> allCars = cc.getAllCars();
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(allCars, getApplication(),gmap);
        rv2.setLayoutManager(new LinearLayoutManager(this));
        rv2.setAdapter(adapter);
        gmap.setMinZoomPreference(12);
        LatLng ny = new LatLng(40.7143528, -74.0059731);
        gmap.moveCamera(CameraUpdateFactory.newLatLng(ny));

//        cc.initializeCars(gmap);
      //  cc.moveCar(gmap, cc.getCarById(1), 40.7143527, -74.0059731);
    }


}