package com.example.myapplication.Border;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;

import android.content.pm.ActivityInfo;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.Controller.DBController;
import com.example.myapplication.Controller.LoginManager;
import com.example.myapplication.DB.MyDB;
import com.example.myapplication.R;


public class CreateAccount extends AppCompatActivity {

    public SQLiteDatabase wdb;
    public MyDB db;
    public LoginManager lm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.activity_register);

        Button register = findViewById(R.id.regBtn);

        Log.d("Before db", "onCreate1");
        db = new MyDB(this);
        Log.d("After db", "After db");

        wdb = DBController.getWritable(this);
        Log.d("after wdb", "after wdb");
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

        EditText regUser = findViewById(R.id.txtUName);
        EditText regPass = findViewById(R.id.txtPName);

        final String username = regUser.getText().toString();
        final String password = regPass.getText().toString();

        if(!(lm.createAccount(username,password))) {
            Log.d("id", "user created");
        }
    }


    public void showToast(Context mContext, String message) {
        Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
    }

   public void signUpSuccess() {
        Button register = findViewById(R.id.regBtn);
        register.setEnabled(true);
        setResult(RESULT_OK, null);
        finish();
    }

    public void failedSignUp() {
        showToast(getBaseContext(), "Login failed. Please try again");

        Button register = findViewById(R.id.regBtn);

        register.setEnabled(true);
    }

    public boolean validateUser() {

        Log.d("Startingvalidation", "Starting user validation");
        boolean valid = true;
        EditText regUser = findViewById(R.id.txtUName);
        EditText regPass = findViewById(R.id.txtPName);
        EditText regFName = findViewById(R.id.txtFName);
        EditText regLName = findViewById(R.id.txtLName);
        EditText regEmail = findViewById(R.id.txtEmail);

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

    public String getFName() {
        EditText regFName = findViewById(R.id.txtFName);
        String first = regFName.getText().toString();
        return first;
    }
    public String getLName() {
        EditText regLName = findViewById(R.id.txtLName);
        String last = regLName.getText().toString();
        return last;
    }
    public String getUsername() {
        EditText regUser = findViewById(R.id.txtUName);
        String user = regUser.getText().toString();
        return user;
    }
    public String getPassword() {
        EditText regPass = findViewById(R.id.txtPName);
        String pass = regPass.getText().toString();
        return pass;
    }
    public String getEmail() {
        EditText regEmail = findViewById(R.id.txtEmail);
        String email = regEmail.getText().toString();
        return email;
    }

}

