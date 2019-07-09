<<<<<<< HEAD:app/src/main/java/com/example/myapplication/Border/LoginActivity.java
package com.example.myapplication.Border;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.myapplication.R;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final EditText usernameInput = (EditText) findViewById(R.id.usernameText);
        final EditText passInput = (EditText) findViewById(R.id.passwordText);

        final Button signUpBtn = (Button) findViewById(R.id.signUpBtn);
        final Button loginBtn = findViewById(R.id.loginBtn);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }



    }

=======
package com.example.myapplication.Controller;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.myapplication.R;;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final EditText usernameInput = (EditText) findViewById(R.id.usernameText);
        final EditText passInput = (EditText) findViewById(R.id.passwordText);

        final Button loginBtn = findViewById(R.id.loginBtn);
    }
}
>>>>>>> c5ed3fbfdf4344be7561b0b51b60c25a1c17acaa:app/src/main/java/com/example/myapplication/Controller/LoginActivity.java
