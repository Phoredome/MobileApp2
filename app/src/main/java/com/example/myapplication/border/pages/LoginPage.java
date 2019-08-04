
package com.example.myapplication.border.pages;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.manager.LoginManager;
import com.example.myapplication.R;
import com.example.myapplication.entities.User;

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

                String userName = usernameInput.getText().toString();

                switch(lm.validateUser(userName,passInput.getText().toString()))
                {
                    case 0:
                        if(lm.check(userName,passInput.getText().toString()))
                        {
                            User user = lm.getUserByName(userName);

                            Bundle b = new Bundle();
                            Bundle a = new Bundle();
                            Bundle c = new Bundle();

                            b.putBoolean("status", user.getStatus());
                            a.putInt("userId", user.getUserId());
                            c.putString("userName", user.getUserName());

                            Intent i = new Intent(getApplicationContext(), MainActivity.class);
                            i.putExtras(b);
                            i.putExtras(a);
                            i.putExtras(c);

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