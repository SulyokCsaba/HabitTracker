package com.example.android.habittracker;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class HabitDbHelper extends SQLiteOpenHelper {

    private final String LOG_TAG = "HabitDbHelper";
    //To Avoid problems in SQL queries constants are used for most used commands
    private static final String TYPE_TEXT = " TEXT";
    private static final String TYPE_INT = " INTEGER";
    private static final String NOT_NULL = " NOT NULL";
    private static final String PRIMARY_KEY = " PRIMARY KEY AUTOINCREMENT";
    private static final String COMMA = ",";

    private String queryCreateTable = "CREATE TABLE " + HabitContract.HabitEntry.TABLE + " ( " +
            HabitContract.HabitEntry.ID + TYPE_INT + PRIMARY_KEY + COMMA +
            HabitContract.HabitEntry.HABIT_NAME + TYPE_TEXT + NOT_NULL + COMMA +
            HabitContract.HabitEntry.HABIT_DAYS + TYPE_INT + NOT_NULL+ COMMA +
            HabitContract.HabitEntry.HABIT_CAT + TYPE_TEXT + NOT_NULL+ ");";


    //We created the database with the Name and Version predefinided
    Context context;
    public HabitDbHelper(Context context) {
        super(context, HabitContract.DB_NAME, null, HabitContract.DB_VERSION);
        this.context = context;
        Log.i(LOG_TAG, "Created database:" + getDatabaseName());
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(queryCreateTable);
        Log.i(LOG_TAG, "Table Created!");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + HabitContract.HabitEntry.TABLE);
        onCreate(db);
        Log.i(LOG_TAG, "Table Updated!");
    }


    // A boolean is used to check if is true then the entire database was successfully deleted.
    public boolean deleteDatabase(){
        return context.deleteDatabase(HabitContract.DB_NAME);
    }

    //For Deleting a Row
    public void deleteHabit() {
        String queryDelete = "DELETE FROM " + HabitContract.HabitEntry.TABLE;
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(queryDelete);
    }

}