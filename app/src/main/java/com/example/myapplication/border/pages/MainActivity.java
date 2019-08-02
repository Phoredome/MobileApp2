package com.example.myapplication.border.pages;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.myapplication.border.info.GetDistanceProbe;
import com.example.myapplication.controller.CarController;
import com.example.myapplication.controller.MapController;
import com.example.myapplication.controller.RecyclerViewAdapter;
import com.example.myapplication.entities.Car;

import com.example.myapplication.R;
import com.example.myapplication.entities.User;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback, GetDistanceProbe.DistanceListener {

    private MapView mapView;
    private GoogleMap gmap;
    private MapController mc;
    private CarController cc;
    private CarController ccc;
    private RecyclerView.Adapter mAdapter;
    private User user;
    private RecyclerView rv;


    private static final String MAP_VIEW_BUNDLE_KEY = "MapViewBundleKey";
    Bundle f, l, b, a, p, e, add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cc = new CarController(getApplicationContext());


        Intent i = getIntent();
        b = i.getExtras();

        Boolean status = b.getBoolean("status");


        final NavigationView navigationView = findViewById(R.id.nav_view);

        if (status) {
            navigationView.inflateMenu(R.menu.activity_admin_drawer);
        } else {
            navigationView.inflateMenu(R.menu.activity_main_drawer);
        }

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
                    case R.id.nav_car_controller:
                        i = new Intent(getApplicationContext(), AdminMap.class);
                        break;
                    case R.id.nav_car_info:
                        i = new Intent(getApplicationContext(), AdminCarInfo.class);
                        break;
                }

                if (i != null) {

                    i.putExtras(b);
                    startActivity(i);

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
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, carTypes);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        carSelect.setAdapter(arrayAdapter);

        rv = findViewById(R.id.recyclerView);

        carSelect.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                rv.setAdapter(null);
                ArrayList<Car> carList = cc.getAllCars();
                ArrayList<Car> updatedList = new ArrayList<>();
                for (Car c: carList) {
                    if (c.getVehicleType().equals(arrayAdapter.getItem(i))){
                        updatedList.add(c);
                    }
                }
                RecyclerViewAdapter adapter = new RecyclerViewAdapter(updatedList, getApplication(),gmap);
                rv.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                rv.setAdapter(adapter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        Button searchBtn = findViewById(R.id.searchBtn);

        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final EditText usernameInput = (EditText) findViewById(R.id.addressTxt);

                LatLng latLng = mc.getLocationFromAddress(getApplicationContext(), usernameInput.getText().toString());
                // ArrayList<Car> nearByCars = cc.getNearByCars(latLng);
            }
        });

        cc.setAllCars();
        //cc.getCarDistance((GetDistanceProbe.DistanceListener) getApplicationContext());

       cc.getCarDistance(MainActivity.this);

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
        LatLngBounds.Builder builder = new LatLngBounds.Builder();
        builder.include(new LatLng(49.368971, -123.278548));
        builder.include(new LatLng(49.008310, -122.139316));
        LatLngBounds bounds = builder.build();
        gmap.setLatLngBoundsForCameraTarget(bounds);
        gmap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(49.232000, -123.023000)));

      //  gmap.setInfoWindowAdapter(new InfoWindow(MainActivity.this));


        rv = findViewById(R.id.recyclerView);
        ArrayList<Car> allCars = cc.getAllCars();
        if (shortList != null)
        {
            RecyclerViewAdapter adapter2 = new RecyclerViewAdapter(shortList,getApplication(), gmap);
            rv.setLayoutManager(new LinearLayoutManager(this));
            rv.setAdapter(adapter2);
        }
        else {
            RecyclerViewAdapter adapter = new RecyclerViewAdapter(allCars, getApplication(), gmap);
            rv.setLayoutManager(new LinearLayoutManager(this));
            rv.setAdapter(adapter);
        }

        gmap.setMinZoomPreference(12);
        cc.initializeCars(gmap);
    /*
    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }*/
    }

    ArrayList<Car> shortList;
    @Override
    public void setNearByList(ArrayList<Car> result) {
        shortList = result;
        // time to get the distance
        if (gmap != null)
        {
            rv.setAdapter(null);
            RecyclerViewAdapter adapter = new RecyclerViewAdapter(shortList,getApplication(), gmap);
            rv.setLayoutManager(new LinearLayoutManager(this));
            rv.setAdapter(adapter);
        }
    }
}