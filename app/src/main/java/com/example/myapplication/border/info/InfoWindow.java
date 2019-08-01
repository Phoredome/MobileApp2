package com.example.myapplication.border.info;

import android.app.Activity;
import android.os.Bundle;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.Marker;

import android.util.Log;

import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.myapplication.R;

public abstract class InfoWindow extends AppCompatActivity implements
        GoogleMap.OnInfoWindowClickListener,
        OnMapReadyCallback {

    private GoogleMap mMap;

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
