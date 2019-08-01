package com.example.myapplication.border;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.myapplication.controller.CarController;
import com.example.myapplication.controller.MapController;
import com.example.myapplication.controller.RecyclerViewAdapter;
import com.example.myapplication.entities.Car;

import com.example.myapplication.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.material.navigation.NavigationView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback, GetDistanceProbe.DistanceListener {

    private MapView mapView;
    private GoogleMap gmap;
    private MapController mc;
    private CarController cc;
    private RecyclerView.Adapter mAdapter;
    private LatLng userLocation;

    private static final String MAP_VIEW_BUNDLE_KEY = "MapViewBundleKey";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cc = new CarController(getApplicationContext());

//       Toolbar toolbar = findViewById(R.id.toolbar);
//       setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        final NavigationView navigationView = findViewById(R.id.nav_view);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int id = menuItem.getItemId();

                switch (id){
                    case R.id.nav_home:
                        startActivity(new Intent(MainActivity.this, MainActivity.class));
                        break;
                    case R.id.nav_account:
                        startActivity(new Intent(MainActivity.this, myAccount.class));
                        break;
                    case R.id.nav_history:
                        startActivity(new Intent(MainActivity.this, TripHistory.class));
                        break;
                    case R.id.nav_car_controller:
                        startActivity(new Intent(MainActivity.this, AdminMap.class));
                        break;
                    case R.id.nav_car_info:
                        startActivity(new Intent(MainActivity.this, AdminCarInfo.class));
                        break;
                }
                return false;
            }
        });


        Bundle mapViewBundle = null;
        if (savedInstanceState != null) {
            mapViewBundle = savedInstanceState.getBundle(MAP_VIEW_BUNDLE_KEY);
        }

        mapView = findViewById(R.id.mapView);
        mapView.onCreate(mapViewBundle);
        mapView.getMapAsync(this);


        Spinner carSelect = findViewById(R.id.carSelect);
        ArrayList<String> carTypes = cc.getCarTypes();
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, carTypes);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        carSelect.setAdapter(arrayAdapter);

        carSelect.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        RecyclerView rv = findViewById(R.id.recyclerView);
        ArrayList<Car> allCars = cc.getAllCars();
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(allCars, getApplication());
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(adapter);


        Button searchBtn = findViewById(R.id.searchBtn);

        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final EditText usernameInput = (EditText) findViewById(R.id.addressTxt);

                LatLng latLng = mc.getLocationFromAddress(getApplicationContext(), usernameInput.getText().toString());
               // ArrayList<Car> nearByCars = cc.getNearByCars(latLng);
            }
        });

        cc.getNearByLocation(MainActivity.this, userLocation);

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
        gmap.setMinZoomPreference(12);
        LatLng ny = new LatLng(40.7143528, -74.0059731);
        gmap.moveCamera(CameraUpdateFactory.newLatLng(ny));

        cc.initializeCars(gmap);
        cc.moveCar(gmap, cc.getCarById(1), 40.7143527, -74.0059731);
    }

    @Override
    public void getDistance(String result) {
        // time to get the distance
        String dist = "0";
        try {
            JSONObject jo = new JSONObject(result);
            dist = jo.getJSONObject("route").getString("distance");
        } catch (JSONException e) {
            Log.d("error", e.getMessage());
        }
        Log.d("GetDistance()", dist); //in miles
    }



    /*
    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }*/


}