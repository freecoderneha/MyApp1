package com.example.android.myapp1.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.android.myapp1.data.AppContract.AppEntry;

/**
 * Database helper for Pets app. Manages database creation and version management.
 */
public class AppDbHelper extends SQLiteOpenHelper {

    public static final String LOG_TAG = AppDbHelper.class.getSimpleName();

    /** Name of the database file */
    private static final String DATABASE_NAME = "help.db";

    /**
     * Database version. If you change the database schema, you must increment the database version.
     */
    private static final int DATABASE_VERSION = 1;


    public AppDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * This is called when the database is created for the first time.
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create a String that contains the SQL statement to create the pets table
        String SQL_CREATE_PETS_TABLE =  "CREATE TABLE " + AppEntry.TABLE_NAME + " ("
                + AppEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + AppEntry.COLUMN_COMPLAIN_TITLE+ " TEXT NOT NULL, "
                + AppEntry.COLUMN_COMPLAIN_DESCRIPTION + " TEXT, "
                + AppEntry.COLUMN_COMPLAIN_CATEGORY + " INTEGER NOT NULL, "
                + AppEntry.COLUMN_COMPLAIN_ISSUE_TYPE + " INTEGER NOT NULL);";

        // Execute the SQL statement
        db.execSQL(SQL_CREATE_PETS_TABLE);
    }

    /**
     * This is called when the database needs to be upgraded.
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // The database is still at version 1, so there's nothing to do be done here.
    }
}