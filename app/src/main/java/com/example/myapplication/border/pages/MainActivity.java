package com.example.myapplication.border.pages;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.border.info.AddressResultReceiver;
import com.example.myapplication.border.info.GetDistanceProbe;
import com.example.myapplication.border.info.MapWrapperLayout;
import com.example.myapplication.controller.adapters.OnInfoWindowElemTouchListener;
import com.example.myapplication.controller.entityController.CarController;
import com.example.myapplication.controller.entityController.MapController;
import com.example.myapplication.controller.adapters.RecyclerViewAdapter;
import com.example.myapplication.controller.entityController.StationController;
import com.example.myapplication.controller.entityController.TripController;
import com.example.myapplication.entities.Car;

import com.example.myapplication.R;
import com.example.myapplication.entities.Station;
import com.example.myapplication.entities.User;
import com.example.myapplication.manager.FetchAddressIntentService;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class MainActivity extends AppCompatActivity
        implements OnMapReadyCallback, GetDistanceProbe.DistanceListener, GoogleMap.OnInfoWindowClickListener, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {


    private MapView mapView;
    private GoogleMap gmap;
    private MapController mc;
    private CarController cc;
    private TripController tc;
    private StationController sc;
    private RecyclerView.Adapter mAdapter;
    private User user;
    private RecyclerView rv;
    private AddressResultReceiver resultReceiver;
    Station s;

    protected Location lastLocation;


    private MapWrapperLayout mapWrapperLayout;
    ViewGroup v;
    TextView carId, licP;
    EditText location;
    Button service, sendToLot, go;
    ArrayList<Car> allCars;



    private static final String MAP_VIEW_BUNDLE_KEY = "MapViewBundleKey";
    Bundle b, a;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cc = new CarController(getApplicationContext());
        mc = new MapController();
        sc = new StationController(getApplicationContext());
        tc = new TripController(getApplicationContext());
        allCars = cc.getAllCars();

        Intent i = getIntent();

        b = i.getExtras();
        a = i.getExtras();

        Integer userId = a.getInt("userId");
        Boolean status = b.getBoolean("status");
        String uName = a.getString("userN");



        final NavigationView navigationView = findViewById(R.id.nav_view);
        View headerView = navigationView.getHeaderView(0);
        TextView userNameTitle =  headerView.findViewById(R.id.nav_name);
        userNameTitle.setText("Welcome,  " + uName);

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
                    case R.id.nav_logout:
                        i = new Intent(MainActivity.this, LoginPage.class);
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


        Bundle mapViewBundle = null;
        if (savedInstanceState != null) {
            mapViewBundle = savedInstanceState.getBundle(MAP_VIEW_BUNDLE_KEY);
        }

        mapView = findViewById(R.id.mapView);
        mapWrapperLayout = findViewById(R.id.mapWrapperLayout);
        mapView.onCreate(mapViewBundle);
        mapView.getMapAsync(this);

        Spinner carSelect = findViewById(R.id.carSelect);
        ArrayList<String> carTypes = cc.getCarTypes();
        Set<String> uniqueCarTypes = new HashSet<String>(carTypes);
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

               EditText usernameInput = (EditText) findViewById(R.id.addressTxt);
               String address = usernameInput.getText().toString();
               Log.d("mainactivity onclick ", address);

               LatLng latLng = mc.getLocationFromAddress(MainActivity.this,address);

               Log.d("mainactivity", "on search btn click; " + latLng.latitude+ latLng.longitude);

               if(latLng != null) {
                    gmap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                }

               cc.getNearCarsFromLocation(MainActivity.this, latLng.latitude, latLng.longitude);
            }
        });

        cc.setAllCars();
        //cc.getCarDistance((GetDistanceProbe.DistanceListener) getApplicationContext());
        cc.getDistanceFromBase(MainActivity.this);
       //cc.getCarDistance(MainActivity.this);

        //LatLng ll = mc.getLocationFromAddress(MainActivity.this, "London");
        // Log.d("MA LocationFrom", ll + "");
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
        mapWrapperLayout.init(gmap, getPixelsFromDp(this, 39 + 20));
      //  gmap.setInfoWindowAdapter(new InfoWindow(MainActivity.this));

        rv = findViewById(R.id.recyclerView);


        v = (ViewGroup)getLayoutInflater().inflate(R.layout.activity_info_window, null);
        carId = (TextView) v.findViewById(R.id.carIdTxt);
        licP = (TextView) v.findViewById(R.id.licensePlateTxt);
        location = (EditText) v.findViewById(R.id.locationInput);
        service = v.findViewById(R.id.serviceBtn);
        sendToLot = v.findViewById(R.id.sendLotBtn);
        go = v.findViewById(R.id.goBtn);

        gmap.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {
            @Override
            public View getInfoWindow(Marker marker) {
                return null;
            }

            @Override
            public View getInfoContents(Marker marker) {

                String markerID = marker.getId().substring(1);
                int markerId = (allCars.get(Integer.parseInt(markerID)-1).getCarID());
                carId.setText(String.valueOf(markerId));
                licP.setText(allCars.get(Integer.parseInt(markerID)-1).getLicensePlate());
                //final Car c = cc.getCarById(Integer.parseInt(carId.getText().toString()));

                mapWrapperLayout.setMarkerWithInfoWindow(marker, v);
                return v;
            }
        });

        OnInfoWindowElemTouchListener infoWindow = new OnInfoWindowElemTouchListener(service) {
            @Override
            protected void onClickConfirmed(View v, Marker marker) {
                Car c = cc.getAllCars().get(Integer.parseInt(carId.getText().toString()));
                cc.serviceCar(c);
            }
        };
        service.setOnTouchListener(infoWindow);

        Log.d("SC Test", sc.getStationByID(0) +"");
        OnInfoWindowElemTouchListener infoWindow2 = new OnInfoWindowElemTouchListener(sendToLot) {
            @Override
            protected void onClickConfirmed(View v, Marker marker)
            {
                s = sc.getStationByID(2);
                Car c = cc.getAllCars().get(Integer.parseInt(carId.getText().toString()));
                Log.d("MainActivity-SendToLot",""+ s.getLocationX()+ s.getLocationY() );
                cc.moveCar(gmap, c, s.getLocationX(), s.getLocationY());
            }
        };

        sendToLot.setOnTouchListener(infoWindow2);

        OnInfoWindowElemTouchListener infoWindow3 = new OnInfoWindowElemTouchListener(go) {
            @Override
            protected void onClickConfirmed(View v, Marker marker) {
                String address = location.getText().toString();
                if(address.indexOf(',') > 0) {
                    LatLng ll = mc.getLocationFromAddress(getApplicationContext(), address);
                    Car c = cc.getAllCars().get(Integer.parseInt(carId.getText().toString()));
                    tc.addTrip(gmap, getApplicationContext(), c, user, ll);
                }else{
                    Car c = cc.getAllCars().get(Integer.parseInt(carId.getText().toString()));
                    tc.addTrip(gmap, getApplicationContext(), c, user, address);
                }
            }
        };

        go.setOnTouchListener(infoWindow3);


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
        //InfoWindow infoAdapter = new InfoWindow(gmap, user, allCars, getApplicationContext());


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
        if (gmap != null) {
            rv.setAdapter(null);
            RecyclerViewAdapter adapter = new RecyclerViewAdapter(shortList, getApplication(), gmap);
            rv.setLayoutManager(new LinearLayoutManager(this));
            rv.setAdapter(adapter);
        }
    }
    @Override
    public void onInfoWindowClick(Marker marker) {
        Toast.makeText(this, marker.getId(), Toast.LENGTH_SHORT).show();

    }

    protected void startIntentService() {
        Intent intent = new Intent(this, FetchAddressIntentService.class);
        intent.putExtra("com.example.myapplication.manager.RECEIVER", resultReceiver);
        intent.putExtra("com.example.myapplication.manager.LOCATION_DATA_EXTRA", lastLocation);
        startService(intent);
    }
/*
    private void fetchAddressButtonHander(View view) {
        fusedLocationClient.getLastLocation()
                .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        lastKnownLocation = location;

                        // In some rare cases the location returned can be null
                        if (lastKnownLocation == null) {
                            return;
                        }

                        if (!Geocoder.isPresent()) {
                            Toast.makeText(MainActivity.this,
                                    R.string.no_geocoder_available,
                                    Toast.LENGTH_LONG).show();
                            return;
                        }

                        // Start service and update UI to reflect new location
                        startIntentService();
                        updateUI();
                    }
                });
    }*/

    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    public static int getPixelsFromDp(Context context, float dp) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int)(dp * scale + 0.5f);
    }
}