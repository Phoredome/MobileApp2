package com.example.myapplication.border;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
<<<<<<< HEAD

import androidx.appcompat.app.AppCompatActivity;
=======

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;

public class TripHistory extends AppCompatActivity
{
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.activity_user_history);
    }
>>>>>>> f41261853dcea5c68a3f21924a8c84bb53195ffe

import com.example.myapplication.R;

public class TripHistory extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.activity_user_history);





    }
}