package com.example.myapplication.manager;

import android.content.Context;
import android.util.Log;

import com.example.myapplication.border.dao.UserDAO;
import com.example.myapplication.entities.User;

public class LoginManager
{
    UserDAO ud;

    public LoginManager(Context context) {
        ud = new UserDAO(context);
    }

    public Boolean check(String uname, String pword)
    {
        User user = null;
        try {
            // Get the User from the database, will be null if User/pass
            // didn't match any account
            user = ud.getUser(uname);
            if (user != null && user.getPassword().equals(pword))
                return true;
            else // User is null or password is wrong
                    return false;
        } catch(Exception e) {
            Log.e("Server Connection Error", e.getMessage(), e);
        }
        // If they didn't cancel logging in by this point, they must *be*
        // logged in. Show the main window, including admin tab if appropriate
        return false;
    }

    public int validateUser(String user, String pass) {
        int valid = 0;

        if (user.isEmpty() || user.length() < 2)
            valid = 1;

        else if(pass.isEmpty() || pass.length() < 4)
            valid = 2;

        return valid;
    }

/**
 * Attempt to create an account in the database. A bool will be returned
 * relating to whether the account was created or not.
 * @param username The User's desired username
 * @param password The User's desired password
 * @return True or false depending on if insertion was completed<br>
 * Returns null if a hibernate exception was encountered
 */
    public Boolean createAccount(String username,
                                 String password,
                                 String fName,
                                 String lName,
                                 String email)
    {
        Log.d("Picky", "Picky");
        try {
            Log.d("new userDAO", "new userDAO");
            // First check if the User exists already
            User userId = ud.getUser(username);
            if ( userId!= null) {
                Log.d("user null", userId.getUserName());
                //return false;
            }
             ud.createAccount(username, password, fName, lName, email);
            Log.d("account created", "account created");
             return true;
        } catch (Exception e) {
            Log.d("LoginManager", "please dont get here: " + e);
            return false;
        }
    }

    public User getUserByName(String userName) {
        User user = ud.getUser(userName);
        return user;
    }

}
