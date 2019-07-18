package com.example.myapplication.Border;

import android.database.sqlite.SQLiteDatabase;
import android.content.ContentValues;

import com.example.myapplication.Entities.User;

public class UserDAO {

    SQLiteDatabase wdb;
    CreateAccount reg = new CreateAccount();

    public Integer createAccount(String user, String pass) {

        ContentValues values = new ContentValues();
        values.put("firstName", reg.getFName());
        values.put("lastName", reg.getLName());
        values.put("email", reg.getEmail());
        values.put("userName", user);
        values.put("password", pass);

        wdb.insert("User", null, values);

        return 0;
    }

    public User getUser(String uname)
    {
        return null;
    }


}
