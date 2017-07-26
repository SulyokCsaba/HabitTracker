package com.example.android.habittracker;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    SQLiteDatabase db;
    HabitDbHelper mHelper;
    ContentValues values = new ContentValues();
    private final String LOG_TAG = "MainActivity_DB";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Layout Related
        setContentView(R.layout.activity_main);
        TextView tvInfo = (TextView) findViewById(R.id.app_info);
        tvInfo.setText(getString(R.string.app_info));

        //Database stuff
        mHelper = new HabitDbHelper(this);
        db = mHelper.getWritableDatabase();

        //Using the different methods declared below
        insertData("Go to work", "14", "Gym");
        insertData("One hour per day fitness", "10", "Work");

        //updateData

        getData();
        deleteTable();
    }

    // Method to insert data into the database
    public boolean insertData(String habit, String days, String category) {
        values.put(HabitContract.HabitEntry.HABIT_NAME, habit);
        values.put(HabitContract.HabitEntry.HABIT_DAYS, days);
        values.put(HabitContract.HabitEntry.HABIT_CAT, category);
        long rowId = db.insert(HabitContract.HabitEntry.TABLE, null, values);
        return rowId != -1;
    }

    // Method to retrieve data of the database
    public Cursor getData() {
        Cursor cursor = db.query(HabitContract.HabitEntry.TABLE,null,null,null,null,null,null);
        cursor.moveToFirst();
        return cursor;
    }

    // Method to update the data into the database
    //https://stackoverflow.com/questions/9798473/sqlite-in-android-how-to-update-a-specific-row
    //TODO: This method crash if I modify it for another String Argument
    public boolean updateData(String habit, String days, String category) {
        ContentValues cv = new ContentValues();
        cv.put(HabitContract.HabitEntry.HABIT_DAYS, days);
        cv.put(HabitContract.HabitEntry.HABIT_CAT, category);
        db.update(HabitContract.HabitEntry.TABLE,cv,
                HabitContract.HabitEntry.HABIT_NAME + "= ?",
                new String[]{habit});
        return true;
    }

    // Method to delete the table that include all the data
    public Integer deleteTable() {
        return db.delete(HabitContract.HabitEntry.TABLE, null, null);
    }

}