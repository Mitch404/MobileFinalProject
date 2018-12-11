package com.mitchrussell.finalproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ConversionDB {
    public static final String DB_NAME = "conversion.sqlite";
    public static final int    DB_VERSION = 1;

    private static class DBHelper extends SQLiteOpenHelper {

        public DBHelper(Context context, String name,
                        SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            // create tables
            db.execSQL("CREATE TABLE conversions (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, volume INTEGER NOT NULL DEFAULT 0, tempDelta INTEGER NOT NULL DEFAULT 0, cal INTEGER NOT NULL DEFAULT 0, isMetric INTEGER NOT NULL DEFAULT 1)");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE \"conversions\"");
            Log.d("Conversions", "Upgrading db from version "
                + oldVersion + " to " + newVersion);

            onCreate(db);
        }
    }

    private SQLiteDatabase db;
    private DBHelper dbHelper;

    public ConversionDB(Context context) {
        dbHelper = new DBHelper(context, DB_NAME, null, DB_VERSION);
        openWriteableDB();
        closeDB();
    }
    private void openReadableDB() { db = dbHelper.getReadableDatabase(); }

    private void openWriteableDB() { db = dbHelper.getWritableDatabase(); }

    private void closeDB() {
        if(db != null)
            db.close();
    }

    ArrayList<HashMap<String, String>> getMetricConversions() {

        ArrayList<HashMap<String, String>> data = new ArrayList<HashMap<String, String>>();
        openReadableDB();
        Cursor cursor = db.rawQuery("SELECT id, volume, tempDelta, cal FROM conversions WHERE isMetric = 1", null);
        while (cursor.moveToNext()) {
            HashMap<String, String> map = new HashMap<String, String>();
            map.put("id", cursor.getString(0));
            map.put("volume", cursor.getString(1));
            map.put("tempDelta", cursor.getString(2));
            map.put("calories", cursor.getString(3));
            data.add(map);
        }
        if (cursor != null)
            cursor.close();
        closeDB();

        return data;
    }

    ArrayList<HashMap<String, String>> getImperialConversions() {

        ArrayList<HashMap<String, String>> data = new ArrayList<HashMap<String, String>>();
        openReadableDB();
        Cursor cursor = db.rawQuery("SELECT id, volume, tempDelta, cal FROM conversions WHERE isMetric = 0", null);
        while (cursor.moveToNext()) {
            HashMap<String, String> map = new HashMap<String, String>();
            map.put("id", cursor.getString(0));
            map.put("volume", cursor.getString(1));
            map.put("tempDelta", cursor.getString(2));
            map.put("calories", cursor.getString(3));
            data.add(map);
        }
        if (cursor != null)
            cursor.close();
        closeDB();

        return data;
    }

    void insertConversion (int volume, int tempDelta, int calories, boolean isMetric) throws Exception {
        openWriteableDB();
        ContentValues content = new ContentValues();
        content.put("volume", volume);
        content.put("tempDelta", tempDelta);
        content.put("cal", calories);

        int isMetricNum = 0;

        if (isMetric)
            isMetricNum = 1;

        content.put("isMetric", isMetricNum);

        long nResult = db.insert("conversions", null, content);
        if(nResult == -1) throw new Exception("no data");
        closeDB();
    }

}
