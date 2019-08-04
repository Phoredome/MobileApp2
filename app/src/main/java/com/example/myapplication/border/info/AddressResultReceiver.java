package com.example.myapplication.border.info;

import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;
import android.provider.SyncStateContract;
import android.util.Log;

import com.example.myapplication.R;

public class AddressResultReceiver extends ResultReceiver {
    public AddressResultReceiver(Handler handler) {
        super(handler);
    }

    @Override
    protected void onReceiveResult(int resultCode, Bundle resultData) {

        if (resultData == null) {
            return;
        }

        // Display the address string
        // or an error message sent from the intent service.
        String addressOutput = resultData.getString(Constants.RESULT_DATA_KEY);
        if (addressOutput == null) {
            addressOutput = "";
        }
        Log.d("AddressResultReceiver", addressOutput);
        //TODO displayAddressOutput();

        // Show a toast message if an address was found.
        if (resultCode == Constants.SUCCESS_RESULT) {
            Log.d("AddressResultReceiver","E.T. Found Home!!!!");
        }
    }
}

final class Constants {
    public static final int SUCCESS_RESULT = 0;
    public static final int FAILURE_RESULT = 1;
    public static final String PACKAGE_NAME =
            "com.example.myapplication.manager";
    public static final String RECEIVER = PACKAGE_NAME + ".RECEIVER";
    public static final String RESULT_DATA_KEY = PACKAGE_NAME +
            ".RESULT_DATA_KEY";
    public static final String LOCATION_DATA_EXTRA = PACKAGE_NAME +
            ".LOCATION_DATA_EXTRA";
}