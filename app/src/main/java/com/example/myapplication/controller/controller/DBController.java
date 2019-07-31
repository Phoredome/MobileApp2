package com.example.myapplication.controller.controller;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.view.Gravity;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.myapplication.db.MyDB;

public class DBController {
    private static SQLiteDatabase wdb = null;
    private static SQLiteDatabase rdb = null;
    private static MyDB db = null;
    public static SQLiteDatabase getWritable(Context context) {
        if (db == null) {
            db = new MyDB(context);
        }
        if (wdb == null) {
            wdb = db.getWritableDatabase();
        }
        return wdb;
    }
    public static SQLiteDatabase getReadable(Context context) {
        if (db == null) {
            db = new MyDB(context);
        }
        if (rdb == null) {
            rdb = db.getReadableDatabase();
        }
        return rdb;
    }

    final String carSelectQuery = "SELECT * FROM Car;";
    final String carSelectQueryDistance = "SELECT * FROM Car WHERE (abs(SELECT coordX, coordY FROM Car)/2) < 10;";
    final String carTypeSelectQuery = "SELECT * FROM Car WHERE vehicleType = ";
    final String userSelectQuery = "SELECT * FROM User WHERE userId = ";

    public static void DisplayAll(TableLayout theView, SQLiteDatabase wdb, Context context, String selectQuery) {
        theView.removeAllViews();
        try {
            Cursor cursor = wdb.rawQuery(selectQuery, null);
            String[] columnNames = cursor.getColumnNames();

            if (cursor != null) {
                cursor.moveToFirst();
                TextView data;
                TableRow row;

                row = new TableRow(context);
                for (int i=0; i < columnNames.length; i++) {
                    row.setPadding(2,2,2,2);
                    data = new TextView(context);
                    data.setTypeface(Typeface.DEFAULT);
                    data.setText(columnNames[i]);
                    row.addView(data);
                }
                theView.addView(row);

                int cnt = 0;
                do {
                    row = new TableRow(context);
                    row.setPadding(2,2,2,2);

                    for (int x = 0; x < cursor.getColumnCount(); x++) {
                        data = new TextView(context);
                        if (x==0) {
                            data.setTypeface(Typeface.DEFAULT);
                            data.setGravity(Gravity.CENTER_HORIZONTAL);
                        }
                        data.setText(cursor.getString(x));
                        row.addView(data);
                    }
                    theView.addView(row);
                } while (cursor.moveToNext());
                theView.setStretchAllColumns(true);
                cursor.close();
            }
        } catch (Exception ex) { }
    }

    public static void close() {
        if (db != null) {
            db.close();
        }
    }
}
