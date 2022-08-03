package com.example.viewmodelpractise.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.viewmodelpractise.dao.UserDAO;
import com.example.viewmodelpractise.models.User;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {User.class}, version = DatabaseConstant.DB_VERSION, exportSchema = false)
public abstract class DatabaseInitializer extends RoomDatabase {

    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public abstract UserDAO userDAO();

    private static DatabaseInitializer instance;

    public static DatabaseInitializer getDatabase(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context, DatabaseInitializer.class, DatabaseConstant.DB_NAME)
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}
