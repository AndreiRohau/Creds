package com.devcolibri.translator;

import android.app.Application;
import android.arch.persistence.room.Room;

import com.devcolibri.translator.db.AppDatabase;

public class MyCredentialsApp extends Application {
    private static AppDatabase database;

    @Override
    public void onCreate() {
        super.onCreate();
        database = Room.databaseBuilder(getApplicationContext(),
                                        AppDatabase.class, "app-database").build();
    }

    public static AppDatabase getDatabase() {
        return database;
    }
}
