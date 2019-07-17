
package com.example.myapplication.Border;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.Controller.LoginManager;
import com.example.myapplication.R;

public class LoginActivity extends AppCompatActivity
{

    LoginManager lm = new LoginManager();

    final EditText usernameInput = (EditText) findViewById(R.id.usernameText);
    final EditText passInput = (EditText) findViewById(R.id.passwordText);

    final Button signUpBtn = findViewById(R.id.signUpBtn);
    final Button loginBtn = findViewById(R.id.loginBtn);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validateUser() == true)
                {
                    if(lm.check(usernameInput.getText().toString(),passInput.getText().toString()) == true)
                    {
                        Intent i = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(i);
                        finish();
                    }
                }

            }
        });

        signUpBtn.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), Register.class);
                startActivity(i);
                finish();
            }
        }));
    }

    public void login() {
        if (!validateUser()) {
            loginFailed();
            return;
        }

        loginBtn.setEnabled(false);

//        String user = usernameInput.getText().toString();
  //      String pass = passInput.getText().toString();

        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        loginSuccess();
                    }
                }, 0);
    }

    public void loginSuccess() {
        loginBtn.setEnabled(true);
        finish();
    }

    public void loginFailed() {
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();

        loginBtn.setEnabled(true);
    }


    public boolean validateUser() {
        boolean valid = true;

        String user = usernameInput.getText().toString();
        String pass = passInput.getText().toString();

        if (user.isEmpty() || user.length() < 2) {
            usernameInput.setError("Enter a valid username longer than 2 characters");
            valid = false;
        } else {
            usernameInput.setError(null);
        }

        if(pass.isEmpty() || pass.length() < 4) {
            passInput.setError("Please enter a password longer than 4 characters.");
            valid = false;
        } else {
            passInput.setError(null);
        }
        return valid;
    }

    public String getUsername()
    {
        String user = usernameInput.getText().toString();
        return user;
    }

    public String getPassword() {
        String pass = passInput.getText().toString();
        return pass;
    }

}