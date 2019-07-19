package com.example.myapplication.DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class MyDB extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 3;
    public static final String DATABASE_NAME = "CarApp.db";

    public MyDB(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        Log.d("MyDB","Constructor");
    }

    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        if(!db.isReadOnly()) {
            db.execSQL("PRAGMA foreign_keys=ON;");
        }
    }

    private static final String SQL_CREATE_ENTRIES_USER =
            "CREATE TABLE " + TableEntry.USER_TABLE_NAME + " (" +
                    TableEntry.USER_COLUMN_NAME_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    TableEntry.USER_COLUMN_NAME_FN + " TEXT NOT NULL," +
                    TableEntry.USER_COLUMN_NAME_LN + " TEXT NOT NULL," +
                    TableEntry.USER_COLUMN_NAME_EMAIL + " TEXT NOT NULL," +
                    TableEntry.USER_COLUMN_NAME_ADDRESS + " TEXT," +
                    TableEntry.USER_COLUMN_NAME_CITY + " TEXT," +
                    TableEntry.USER_COLUMN_NAME_COUNTRY + " TEXT," +
                    TableEntry.USER_COLUMN_NAME_PHONE_NUMBER + " NUMERIC," +
                    TableEntry.USER_COLUMN_NAME_DATE_OF_BIRTH + " NUMERIC," +
                    TableEntry.USER_COLUMN_NAME_PAYMENT_OPTIONS + " TEXT," +
                    TableEntry.USER_COLUMN_NAME_USERNAME + " TEXT NOT NULL," +
                    TableEntry.USER_COLUMN_NAME_PASSWORD + " TEXT NOT NULL," +
                    TableEntry.USER_COLUMN_NAME_STATUS + " TEXT NOT NULL)";

    private static final String SQL_CREATE_ENTRIES_CAR =
            "CREATE TABLE " + TableEntry.CAR_TABLE_NAME + " (" +
                    TableEntry.CAR_COLUMN_NAME_CAR_ID + " INTEGER PRIMARY KEY," +
                    TableEntry.CAR_COLUMN_NAME_VEHICLE_TYPE + " TEXT NOT NULL," +
                    TableEntry.CAR_COLUMN_NAME_COST_OF_RUNNING + " NUMERIC," +
                    TableEntry.CAR_COLUMN_NAME_SEATS + " NUMERIC NOT NULL," +
                    TableEntry.CAR_COLUMN_NAME_DOORS + " NUMERIC NOT NULL," +
                    TableEntry.CAR_COLUMN_NAME_CAPACITY + " NUMERIC NOT NULL," +
                    TableEntry.CAR_COLUMN_NAME_LICENSE_PLATE + " TEXT NOT NULL," +
                    TableEntry.CAR_COLUMN_NAME_SERVICE_TIME + " NUMERIC NOT NULL," +
                    TableEntry.CAR_COLUMN_NAME_KMS_RUN + " NUMERIC," +
                    TableEntry.CAR_COLUMN_NAME_KMS_SINCE_LAST_SERVICE + " NUMERIC," +
                    TableEntry.CAR_COLUMN_NAME_IN_USE + " NUMERIC," +
                    TableEntry.CAR_COLUMN_NAME_IN_SERVICE + " NUMERIC," +
                    TableEntry.CAR_COLUMN_NAME_COORDX + " NUMERIC, " +
                    TableEntry.CAR_COLUMN_NAME_COORDY + " NUMERIC)";

    private static final String SQL_CREATE_ENTRIES_TRIP =
            "CREATE TABLE " + TableEntry.TRIP_TABLE_NAME + " (" +
                    TableEntry.TRIP_COLUMN_NAME_TRIP_ID + " INTEGER PRIMARY KEY," +
                    TableEntry.TRIP_COLUMN_NAME_CAR_ID + " INTEGER," +
                    TableEntry.TRIP_COLUMN_NAME_USER_ID + " INTEGER," +
                    TableEntry.TRIP_COLUMN_NAME_AMOUNT + " NUMERIC NOT NULL," +
                    TableEntry.TRIP_COLUMN_NAME_KMS_RUN_FOR_TRIP + " NUMERIC NOT NULL," +
                    TableEntry.TRIP_COLUMN_NAME_TIME_OF_TRIP + " NUMERIC NOT NULL," +
                    TableEntry.TRIP_COLUMN_NAME_DATE_OF_TRIP + " NUMERIC NOT NULL," +
                    TableEntry.TRIP_COLUMN_NAME_STARTINGX + " NUMERIC NOT NULL," +
                    TableEntry.TRIP_COLUMN_NAME_STARTINGY + " NUMERIC NOT NULL," +
                    TableEntry.TRIP_COLUMN_NAME_ENDINGX + " NUMERIC NOT NULL," +
                    TableEntry.TRIP_COLUMN_NAME_ENDINGY + " NUMERIC NOT NULL," +
                    "FOREIGN KEY(carId) REFERENCES Car(carId)," +
                    "FOREIGN KEY(userId) REFERENCES User(userId))";

    private static final String SQL_DELETE_ENTRIES_USER =
            "DROP TABLE IF EXISTS " + TableEntry.USER_TABLE_NAME;

    private static final String SQL_DELETE_ENTRIES_CAR =
            "DROP TABLE IF EXISTS " + TableEntry.CAR_TABLE_NAME;

    private static final String SQL_DELETE_ENTRIES_TRIP =
            "DROP TABLE IF EXISTS " + TableEntry.TRIP_TABLE_NAME;

    private static final String SQL_INSERT_USER =
            "insert into User (userName, password, firstName, lastName, email, phoneNumber, address, city, country, dateOfBirth, paymentOptions, status) values ('klboo', 'klboo', 'Kristy', 'Le', 'Pikachuuu@Cars.com', '425-916-4490', ' 5321 Park Drive', 'Haoguantun', 'China', '04/07/1997', 'visa', 'true')";

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES_USER);
        db.execSQL(SQL_CREATE_ENTRIES_CAR);
        db.execSQL(SQL_CREATE_ENTRIES_TRIP);
        db.execSQL(SQL_INSERT_USER);
        Log.d("MyDB","onCreate");
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES_USER);
        db.execSQL(SQL_DELETE_ENTRIES_CAR);
        db.execSQL(SQL_DELETE_ENTRIES_TRIP);
        Log.d("MyDB","OnUpgrade");
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d("MyDB","onDowngrade");
        onUpgrade(db, oldVersion, newVersion);
    }
}
