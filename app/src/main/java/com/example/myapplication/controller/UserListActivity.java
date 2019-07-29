package com.example.myapplication.controller;

import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.entities.Car;

import java.util.ArrayList;

public class UserListActivity
{

    // Initialize contacts
    ArrayList<Car> cars = Car.createCarList(20);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // ...
        // Lookup the recyclerview in activity layout
        RecyclerView rvContacts = (RecyclerView) findViewById(R.id.rvContacts);

        // Create adapter passing in the sample user data
        ListManager.ContactsAdapter adapter = new ListManager.ContactsAdapter(cars);
        // Attach the adapter to the recyclerview to populate items
        rvContacts.setAdapter(adapter);
        // Set layout manager to position the items
        rvContacts.setLayoutManager(new LinearLayoutManager(this));
        // That's all!
    }
}
