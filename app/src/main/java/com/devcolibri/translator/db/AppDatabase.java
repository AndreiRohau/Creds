package com.devcolibri.translator.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.devcolibri.translator.entity.Word;

@Database(entities = {Word.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract WordDao getPersonDao();
}