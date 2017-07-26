package com.example.android.habittracker;

import android.provider.BaseColumns;

public class HabitContract {
    public static final String DB_NAME = "habits.db";
    public static final int DB_VERSION = 1;

    public class HabitEntry implements BaseColumns {

        public static final String TABLE = "habits";
        public static final String ID = "id";
        public static final String HABIT_NAME = "title";
        public static final String HABIT_DAYS = "days";
        public static final String HABIT_CAT = "category";

    }
}
