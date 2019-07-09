package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Register extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        EditText regUser = (EditText) findViewById(R.id.reg_userN);
        EditText regPass = (EditText) findViewById(R.id.reg_pass);
        EditText regFName = (EditText) findViewById(R.id.reg_first);
        EditText regLName = (EditText) findViewById(R.id.reg_last);
        EditText regEmail = (EditText) findViewById(R.id.reg_email);
        EditText regAdd = (EditText) findViewById(R.id.reg_address);

        Button register = (Button) findViewById(R.id.regBtn);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

}
