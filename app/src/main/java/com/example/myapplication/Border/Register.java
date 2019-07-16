package com.example.myapplication.Border;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.Controller.DBController;
import com.example.myapplication.Entities.User;
import com.example.myapplication.R;
import com.example.myapplication.ui.login.LoginActivity;


public class Register extends AppCompatActivity {

    SQLiteDatabase wdb;

    final EditText regUser = (EditText) findViewById(R.id.reg_userN);
    final EditText regPass = (EditText) findViewById(R.id.reg_pass);
    final EditText regFName = (EditText) findViewById(R.id.reg_first);
    final EditText regLName = (EditText) findViewById(R.id.reg_last);
    final EditText regEmail = (EditText) findViewById(R.id.reg_email);

    final TextView login = (TextView) findViewById(R.id.loginLink);
    final Button register = (Button) findViewById(R.id.regBtn);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

    wdb = DBController.getWritable(this);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signup();
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
                finish();
             //   overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);

            }
        });

    }

    public void signup() {
        if(!validateUser()) {
            failedSignUp();
            return;
        }

        register.setEnabled(false);

        final String name = regFName.getText().toString();
        final String lastName = regLName.getText().toString();
        final String email = regEmail.getText().toString();
        final String username = regUser.getText().toString();
        final String password = regPass.getText().toString();

        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {

                        signUpSuccess();
                        ContentValues values = new ContentValues();
                        values.put("firstName", name);
                        values.put("lastName", lastName);
                        values.put("email", email);
                        values.put("userName", username);
                        values.put("password", password);

                        wdb.insert("User", null, values);

                    }
                }, 0);
    }



    public void signUpSuccess() {
        register.setEnabled(true);
        setResult(RESULT_OK,null);
        finish();
    }


    public void failedSignUp() {
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();

        register.setEnabled(true);
    }

    public boolean validateUser() {
        boolean valid = true;

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

        if(lastName.isEmpty() || lastName.length() < 2){
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

    public String getPassword() {
        String pass = regPass.getText().toString();
        return pass;
    }





}
