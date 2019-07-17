package com.example.myapplication.Border;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TableLayout;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import com.example.myapplication.Entities.User;

public class UserDAO {

    SQLiteDatabase wdb;
    Register reg = new Register();

    public Integer registerUser() {

        ContentValues values = new ContentValues();
        values.put("firstName", reg.getFName());
        values.put("lastName", reg.getLName());
        values.put("email", reg.getEmail());
        values.put("userName", reg.getUsername());
        values.put("password", reg.getPassword());

        wdb.insert("User", null, values);

        return 0;
    }

    public User getUser(String uname)
    {
        return null;
    }
}
