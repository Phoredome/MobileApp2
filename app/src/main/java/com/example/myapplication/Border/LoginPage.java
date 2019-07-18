
package com.example.myapplication.Border;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.Controller.LoginManager;
import com.example.myapplication.R;

public class LoginPage extends AppCompatActivity
{



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.activity_login);

        final EditText usernameInput = (EditText) findViewById(R.id.usernameText);
        final EditText passInput = (EditText) findViewById(R.id.passwordText);

        Log.d("test", "hello World: ");
        Button signUpBtn = findViewById(R.id.signUpBtn);
        Button loginBtn = findViewById(R.id.loginBtn);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                LoginManager lm = new LoginManager();


                switch(validateUser(usernameInput.getText().toString(),passInput.getText().toString()))
                {
                    case 0:
                        if(lm.check(usernameInput.getText().toString(),passInput.getText().toString()))
                        {
                            Intent i = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(i);
                            finish();
                        }
                        break;
                    case 1:
                        usernameInput.setError("Enter a valid username longer than 2 characters");
                        usernameInput.setError(null);
                        break;
                    case 2:
                        passInput.setError("Please enter a password longer than 4 characters.");
                        passInput.setError(null);
                        break;
                }

            }
        });


        signUpBtn.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Signup button", "signup button");
                Intent i = new Intent(getApplicationContext(), CreateAccount.class);
                startActivity(i);
                finish();
            }
        }));
    }

    public void login() {
       /* if (!validateUser()) {
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
                }, 0);*/
    }

    public void loginSuccess() {

        Button loginBtn = findViewById(R.id.loginBtn);
        loginBtn.setEnabled(true);
        finish();
    }

    public void loginFailed() {
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();
        Button loginBtn = findViewById(R.id.loginBtn);

        loginBtn.setEnabled(true);
    }

    /*public String getUsername()
    {
        String user = usernameInput.getText().toString();
        return user;
    }

    public String getPassword() {
        String pass = passInput.getText().toString();
        return pass;
    }*/

    private int validateUser(String user, String pass) {
        int valid = 0;


        if (user.isEmpty() || user.length() < 2)
            valid = 1;

        else if(pass.isEmpty() || pass.length() < 4)
            valid = 2;

        return valid;
    }

}