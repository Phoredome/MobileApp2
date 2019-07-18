package com.example.myapplication.Controller;

import android.util.Log;

import com.example.myapplication.Border.LoginPage;
import com.example.myapplication.Border.UserDAO;
import com.example.myapplication.Entities.User;

public class LoginManager
{
    LoginPage la = new LoginPage();
    UserDAO ud = new UserDAO();

    public Boolean check(String uname, String pword)
    {
        User user = null;
        try {
            // Get the User from the database, will be null if User/pass
            // didn't match any account
            user = ud.getUser(uname);
            if (user != null && user.getPassword().equals(pword))
                if (user.getStatus()) {
                    return true;
                }
            else // User is null or password is wrong
                    return false;
        } catch(Exception e) {
            Log.e("Server Connection Error", e.getMessage(), e);
        }
        // If they didn't cancel logging in by this point, they must *be*
        // logged in. Show the main window, including admin tab if appropriate
        return false;
    }

/**
 * Attempt to create an account in the database. A bool will be returned
 * relating to whether the account was created or not.
 * @param username The User's desired username
 * @param password The User's desired password
 * @return True or false depending on if insertion was completed<br>
 * Returns null if a hibernate exception was encountered
 */
    public static Boolean createAccount(String username, String password)
    {
        try {
            UserDAO ud = new UserDAO();
            // First check if the User exists already
            if (ud.getUser(username) != null)
                return false;
             ud.createAccount(username, password);
             return true;
        } catch (Exception e) {
            return null;
        }
    }
}
