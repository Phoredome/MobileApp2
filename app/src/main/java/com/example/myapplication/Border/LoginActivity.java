package com.example.mainlayout;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final EditText usernameInput = (EditText) findViewById(R.id.usernameText);
        final EditText passInput = (EditText) findViewById(R.id.passwordText);

        final Button signUpBtn = (Button) findViewById(R.id.signUpBtn);
        final Button loginBtn = findViewById(R.id.loginBtn);


        LoginPage();

        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}
