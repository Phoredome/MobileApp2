
package com.example.myapplication.Border;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.Controller.DBController;
import com.example.myapplication.Controller.LoginManager;
import com.example.myapplication.DB.MyDB;
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

                LoginManager lm = new LoginManager(getApplicationContext());

                switch(lm.validateUser(usernameInput.getText().toString(),passInput.getText().toString()))
                {
                    case 0:
                        if(lm.check(usernameInput.getText().toString(),passInput.getText().toString()))
                        {
                            Intent i = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(i);
                            finish();
                        }
                        else
                            Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();
                        break;
                    case 1:
                        usernameInput.setError("Enter a valid username longer than 2 characters");
                        break;
                    case 2:
                        passInput.setError("Please enter a password longer than 4 characters.");
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

    /*public void login() {
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
    }*/

    public void loginSuccess() {

        Button loginBtn = findViewById(R.id.loginBtn);
        loginBtn.setEnabled(true);
        finish();
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
}