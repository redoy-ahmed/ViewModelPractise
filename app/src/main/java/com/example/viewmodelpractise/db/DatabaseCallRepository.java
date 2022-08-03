package com.example.viewmodelpractise.db;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.viewmodelpractise.dao.UserDAO;
import com.example.viewmodelpractise.models.User;

import java.util.List;

public class DatabaseCallRepository {

    private final UserDAO userDAO;

    public DatabaseCallRepository(Application application) {
        DatabaseInitializer db = DatabaseInitializer.getDatabase(application);
        userDAO = db.userDAO();
    }

    public void insertUser(User user) {
        DatabaseInitializer.databaseWriteExecutor.execute(() -> {
            userDAO.insert(user);
        });
    }

    public LiveData<List<User>> getAllUsers() {
        return userDAO.getAllUsers();
    }
}
