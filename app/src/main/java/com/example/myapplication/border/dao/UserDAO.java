package com.example.myapplication.border.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.content.ContentValues;
import android.util.Log;

import com.example.myapplication.controller.controller.DBController;
import com.example.myapplication.entities.User;

import java.util.ArrayList;

public class UserDAO
{
    SQLiteDatabase wdb;
    SQLiteDatabase rdb;

    public UserDAO(Context context)
    {
        wdb = DBController.getWritable(context);
        rdb = DBController.getReadable(context);
    }

    public Boolean createAccount(String user, String pass, String fName, String lName, String email) {

        Log.d("Inside userdao", "Inside createaccount");
        ContentValues values = new ContentValues();
        values.put("firstName", fName);
        values.put("lastName", lName);
        values.put("email", email);
        values.put("userName", user);
        values.put("password", pass);
        values.put("status", "false");

        wdb.insert("User", null, values);

        return true;
    }

    public User getUser(String uname)
    {
        Log.d("inside user", "inside user");

        ArrayList<User> users = getUsers();
        User user = null;
        for (int i= 0; i < users.size(); i++) {
            String userName = users.get(i).getUserName();
            if (uname.equals(userName)) {
                user = users.get(i);
                Log.d("UserName", userName);

            }
        }
        return user;
    }

    public boolean editUser(String user, String pass, String fName, String lName, String email) {

        Log.d("Inside userdao", "Inside createaccount");
        ContentValues values = new ContentValues();
        values.put("firstName", fName);
        values.put("lastName", lName);
        values.put("email", email);
        values.put("userName", user);
        values.put("password", pass);

        wdb.insert("User", null, values);

        return true;
    }

    public ArrayList<User> getUsers() {
        ArrayList<User> users = new ArrayList<>();
        try {

            String selectQuery = "SELECT * FROM User;";
            Cursor cursor = rdb.rawQuery(selectQuery,null);
            Integer size = cursor.getCount();
            while(cursor.moveToNext()) {
                int index;
                index = cursor.getColumnIndexOrThrow("userId");
                int userId = cursor.getInt(index);

                index = cursor.getColumnIndexOrThrow("firstName");
                String firstName = cursor.getString(index);

                index = cursor.getColumnIndexOrThrow("lastName");
                String lastName = cursor.getString(index);

                index = cursor.getColumnIndexOrThrow("email");
                String email = cursor.getString(index);

                index = cursor.getColumnIndexOrThrow("address");
                String address = cursor.getString(index);

                index = cursor.getColumnIndexOrThrow("city");
                String city = cursor.getString(index);

                index = cursor.getColumnIndexOrThrow("country");
                String country = cursor.getString(index);

                index = cursor.getColumnIndexOrThrow("phoneNumber");
                int phoneNumber = cursor.getInt(index);

                index = cursor.getColumnIndexOrThrow("dateOfBirth");
                String dateOfBirth = cursor.getString(index);

                index = cursor.getColumnIndexOrThrow("paymentOptions");
                String paymentOptions = cursor.getString(index);

                index = cursor.getColumnIndexOrThrow("userName");
                String userName = cursor.getString(index);

                index = cursor.getColumnIndexOrThrow("password");
                String password = cursor.getString(index);

                index = cursor.getColumnIndexOrThrow("status");
                Boolean status = Boolean.parseBoolean(cursor.getString(index));
                User user = new User(userId,firstName,lastName,email,address,city,country,phoneNumber,dateOfBirth,paymentOptions,userName,password,status);
                users.add(user);

            }
            cursor.close();
        } catch (SQLException ex) {
            return null;
        }

        return users;
    }



}
