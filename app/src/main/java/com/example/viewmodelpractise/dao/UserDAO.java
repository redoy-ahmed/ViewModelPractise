package com.example.viewmodelpractise.dao;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.viewmodelpractise.models.User;

import java.util.List;

@Dao
public interface UserDAO {

    @Insert
    long insert(User user);

    @Update
    int update(User user);

    @Delete
    int delete(User scientist);

    @Query("SELECT * FROM tblUser")
    LiveData<List<User>> getAllUsers();

    @Query("DELETE FROM tblUser")
    int deleteAll();
}
