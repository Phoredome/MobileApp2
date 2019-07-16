package com.example.myapplication.Border;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.ui.login.LoginActivity;

public class Register extends AppCompatActivity {


    final EditText regUser = (EditText) findViewById(R.id.reg_userN);
    final EditText regPass = (EditText) findViewById(R.id.reg_pass);
    final EditText regFName = (EditText) findViewById(R.id.reg_first);
    final EditText regLName = (EditText) findViewById(R.id.reg_last);
    final EditText regEmail = (EditText) findViewById(R.id.reg_email);


    final Button register = (Button) findViewById(R.id.regBtn);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);



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

        String name = regFName.getText().toString();
        String lastname = regLName.getText().toString();
        String email = regEmail.getText().toString();
        String username = regUser.getText().toString();
        String password = regPass.getText().toString();

    }

    public void showToast(Context mContext, String message){
        Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
    }

    public void failedSignUp() {
        showToast(getBaseContext(), "Login failed. Please try again");
    }

    public void successfulSignUp () {
        register.setEnabled(true);
        setResult(RESULT_OK, null);
        finish();
    }

    public boolean validateUser() {
        boolean valid = true;

        String name = regFName.getText().toString();
        String lastname = regLName.getText().toString();
        String email = regEmail.getText().toString();
        String username = regUser.getText().toString();
        String password = regPass.getText().toString();


        if (name.isEmpty() || name.length() < 2) {
            regFName.setError("Your first name needs at least 2 characters. Please try again.");
            valid = false;
        } else {
            regFName.setError(null);
        }

        if(lastname.isEmpty() || lastname.length() < 2){
            regLName.setError("Your last name needs at least 2 characters. Please try again.");
            valid = false;
        } else {
            regLName.setError(null);
        }

        return false;
    }

    public String getUserName() {
        String user = regUser.getText().toString();
        return  user;
    }

    public String getPassword() {
        String pass = regPass.getText().toString();
        return pass;
    }


}
