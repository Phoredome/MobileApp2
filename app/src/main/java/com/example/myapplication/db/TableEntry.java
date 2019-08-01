package com.example.myapplication.db;

import android.provider.BaseColumns;

public class TableEntry implements BaseColumns {

    public static final String USER_TABLE_NAME = "User";
    public static final String USER_COLUMN_NAME_USER_ID = "userId";
    public static final String USER_COLUMN_NAME_PHONE_NUMBER = "phoneNumber";
    public static final String USER_COLUMN_NAME_FN = "firstName";
    public static final String USER_COLUMN_NAME_LN = "lastName";
    public static final String USER_COLUMN_NAME_EMAIL = "email";
    public static final String USER_COLUMN_NAME_ADDRESS = "address";
    public static final String USER_COLUMN_NAME_CITY = "city";
    public static final String USER_COLUMN_NAME_COUNTRY = "country";
    public static final String USER_COLUMN_NAME_DATE_OF_BIRTH = "dateOfBirth";
    public static final String USER_COLUMN_NAME_PAYMENT_OPTIONS = "paymentOptions";
    public static final String USER_COLUMN_NAME_USERNAME = "userName";
    public static final String USER_COLUMN_NAME_PASSWORD = "password";
    public static final String USER_COLUMN_NAME_STATUS = "status";

    public static final String CAR_TABLE_NAME = "Car";
    public static final String CAR_COLUMN_NAME_CAR_ID = "carId";
    public static final String CAR_COLUMN_NAME_COST_OF_RUNNING = "costOfRunning";
    public static final String CAR_COLUMN_NAME_SEATS = "seats";
    public static final String CAR_COLUMN_NAME_DOORS = "doors";
    public static final String CAR_COLUMN_NAME_SERVICE_TIME = "serviceTime";
    public static final String CAR_COLUMN_NAME_KMS_RUN = "kmsRun";
    public static final String CAR_COLUMN_NAME_KMS_SINCE_LAST_SERVICE = "kmsSinceLastService";
    public static final String CAR_COLUMN_NAME_VEHICLE_TYPE = "vehicleType";
    public static final String CAR_COLUMN_NAME_LICENSE_PLATE = "licensePlate";
    public static final String CAR_COLUMN_NAME_IN_USE = "inUse";
    public static final String CAR_COLUMN_NAME_IN_SERVICE = "inActiveService";
    public static final String CAR_COLUMN_NAME_COORDX = "coordX";
    public static final String CAR_COLUMN_NAME_COORDY = "coordY";

    public static final String TRIP_TABLE_NAME = "Trip";
    public static final String TRIP_COLUMN_NAME_TRIP_ID = "tripId";
    public static final String TRIP_COLUMN_NAME_CAR_ID = "carId";
    public static final String TRIP_COLUMN_NAME_USER_ID = "userId";
    public static final String TRIP_COLUMN_NAME_KMS_RUN_FOR_TRIP = "kmsRunForTrip";
    public static final String TRIP_COLUMN_NAME_DATE_OF_TRIP = "dateOfTrip";
    public static final String TRIP_COLUMN_NAME_TIME_OF_TRIP = "timeOfTrip";
    public static final String TRIP_COLUMN_NAME_AMOUNT = "amount";
    public static final String TRIP_COLUMN_NAME_STARTINGY = "startingY";
    public static final String TRIP_COLUMN_NAME_STARTINGX = "startingX";
    public static final String TRIP_COLUMN_NAME_ENDINGY = "endingY";
    public static final String TRIP_COLUMN_NAME_ENDINGX = "endingX";


}
