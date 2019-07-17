package com.example.myapplication.Border;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import com.example.myapplication.Entities.User;

public class CarDAO {

    SQLiteDatabase wdb2;
    Car reg = new Register();

    public Integer registerUser() {

        ContentValues values = new ContentValues();
        values.put("firstName", reg.getFName());
        values.put("lastName", reg.getLName());
        values.put("email", reg.getEmail());

        wdb.insert("User", null, values);

        return 0;
    }

    public User getUser(String uname)
    {
        return null;
    }

}
