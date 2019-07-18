package com.example.myapplication.Border;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.content.ContentValues;

import com.example.myapplication.Entities.User;

import java.util.ArrayList;

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

        return 1;
    }

    public User getUser(String uname)
    {
        ArrayList<User> users = getUsers();
        User user = new User();
        for (int i= 0; i < users.size(); i++) {
            if (uname == users.get(i).getUserName()) {
                user = users.get(i);
            }
        }
        return user;
    }

    private ArrayList<User> getUsers() {
        ArrayList<User> users = new ArrayList<>();
        try {
            String selectQuery = "SELECT * FROM User ";
            Cursor cursor = wdb.rawQuery(selectQuery,null);
            int size = cursor.getCount();


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

                users.add(new User(userId,firstName,lastName,email,address,city,country,phoneNumber,dateOfBirth,paymentOptions,userName,password,status));
            }
            cursor.close();
        } catch (Exception ex) {

        }
        return users;
    }



}
