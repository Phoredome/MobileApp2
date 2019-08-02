package com.example.myapplication.border.pages;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.solver.widgets.Helper;

import com.example.myapplication.R;
import com.example.myapplication.border.dao.UserDAO;
import com.example.myapplication.entities.User;
import com.example.myapplication.manager.LoginManager;
import com.google.android.material.navigation.NavigationView;

public class MyAccount extends AppCompatActivity {


    public LoginManager lm;
    Bundle b, a;
    UserDAO ud;
    Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.activity_myaccount);

        final NavigationView navigationView = findViewById(R.id.nav_myaccount);

        i = getIntent();
        b = i.getExtras();
        a = i.getExtras();
        ud = new UserDAO(getApplicationContext());

        Boolean status = b.getBoolean("status");

        Integer userId = a.getInt("userId");


        Log.d("myaccount", userId +"");
        final User user = ud.getUserById(userId);

        String first = user.getFirstName();
        String last = user.getLastName();
        String userN = user.getUserName();

        final TextView firstName;
        String pass = user.getPassword();
        String email = user.getEmail();
        firstName = findViewById(R.id.fName_up);
        firstName.setHint(first);

        final TextView lastName;
        lastName = findViewById(R.id.LName_up);
        lastName.setHint(last);

        final TextView userName;
        userName = findViewById(R.id.userName_up);
        userName.setHint(userN);

        final TextView password;
        password = findViewById(R.id.password_up);
        password.setHint(pass);

        final TextView eMail;
        eMail = findViewById(R.id.email_up);
        eMail.setHint(email);


        if (status) {
            navigationView.inflateMenu(R.menu.activity_admin_drawer);

        } else {
            navigationView.inflateMenu(R.menu.activity_main_drawer);
        }


        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                int id = menuItem.getItemId();
                Intent i = null;

                switch (id) {
                    case R.id.nav_home:
                        i = new Intent(getApplicationContext(), MainActivity.class);
                        break;
                    case R.id.nav_account:
                        i = new Intent(getApplicationContext(), MyAccount.class);
                        break;
                    case R.id.nav_history:
                        i = new Intent(getApplicationContext(), TripHistory.class);
                        break;
                    case R.id.nav_car_controller:
                        i = new Intent(getApplicationContext(), AdminMap.class);
                        break;
                    case R.id.nav_car_info:
                        i = new Intent(getApplicationContext(), AdminCarInfo.class);
                        break;
                }

                if (i != null) {

                    i.putExtras(b);
                    i.putExtras(a);
                    startActivity(i);

                }
                return false;

            }
        });


       Button update = (Button)findViewById(R.id.updateBtn);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Integer userId = user.getUserId();
                String profileFirst = firstName.getText().toString();
                String profileLast = lastName.getText().toString();
                String profileUser = userName.getText().toString();
                String profilePass = password.getText().toString();
                String profileEmail = eMail.getText().toString();

                ud.updateAccount(userId, profileFirst, profileLast,profileUser, profilePass, profileEmail);

//                Intent intent = new Intent(MyAccount.this, LoginPage.class);
//                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                startActivity(intent);

                finish();
            }




        });
    }

    }

