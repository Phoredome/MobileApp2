package com.example.myapplication.border.pages;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.manager.LoginManager;
import com.example.myapplication.R;


public class CreateAccount extends AppCompatActivity {


    public LoginManager lm;
    Bundle a,b;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.activity_register);

        lm = new LoginManager(getApplicationContext());
        Button register = findViewById(R.id.regBtn);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signup();
            }
        });



    }

    public void signup() {
        Log.d("Before validate user", "Before validate user");
        if (!validateUser()) {
            failedSignUp();
            return;
        }

        Log.d("User tested", "User tested");
        Button register = findViewById(R.id.regBtn);
        register.setEnabled(false);

        EditText regUser = findViewById(R.id.userName);
        EditText regPass = findViewById(R.id.password);
        EditText regFName = findViewById(R.id.fName);
        EditText regLName = findViewById(R.id.LName);
        EditText regEmail = findViewById(R.id.email);

        String first = regFName.getText().toString();
        String last = regLName.getText().toString();
        String user = regUser.getText().toString();
        String pass = regPass.getText().toString();
        String email = regEmail.getText().toString();


        if((lm.createAccount(user,pass,first,last,email))) {
            Log.d("createAccount-signup", "user created");
            Intent intent = new Intent(getApplicationContext(),LoginPage.class);

            Intent i = getIntent();

            Toast.makeText(getApplicationContext(), "User created!" , Toast.LENGTH_LONG).show();

            startActivity(intent);
            finish();
        }
    }


    public void showToast(Context mContext, String message) {
        Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
    }

    public void failedSignUp() {
        showToast(getBaseContext(), "Login failed. Please try again");

        Button register = findViewById(R.id.regBtn);

        register.setEnabled(true);
    }

    public boolean validateUser() {

        Log.d("Startingvalidation", "Starting user validation");
        boolean valid = true;
        EditText regUser = findViewById(R.id.userName);
        EditText regPass = findViewById(R.id.password);
        EditText regFName = findViewById(R.id.fName);
        EditText regLName = findViewById(R.id.LName);
        EditText regEmail = findViewById(R.id.email);

        String name = regFName.getText().toString();
        String lastName = regLName.getText().toString();
        String email = regEmail.getText().toString();
        String username = regUser.getText().toString();
        String password = regPass.getText().toString();

        if (name.isEmpty() || name.length() < 2) {
            regFName.setError("Your first name needs at least 2 characters. Please try again.");
            valid = false;
        } else {
            regFName.setError(null);
        }

        if (lastName.isEmpty() || lastName.length() < 2) {
            regLName.setError("Your last name needs at least 2 characters. Please try again.");
            valid = false;
        } else {
            regLName.setError(null);
        }

        if (username.isEmpty() || username.length() < 3) {

            regUser.setError("Your first name needs at least 2 characters. Please try again.");
            valid = false;

        } else {
            regUser.setError(null);
        }

        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            regEmail.setError("Enter a valid email address.");
            valid = false;
        } else {
            regEmail.setError(null);
        }

        if (password.isEmpty() || password.length() < 4) {
            regPass.setError("Your password needs to be at least 4 characters long");
            valid = false;

        } else {
            regPass.setError(null);
        }

        return valid;
    }


}

