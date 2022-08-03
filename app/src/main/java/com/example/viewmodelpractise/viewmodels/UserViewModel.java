package com.example.viewmodelpractise.viewmodels;

import android.app.Application;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.viewmodelpractise.db.DatabaseCallRepository;
import com.example.viewmodelpractise.models.User;
import com.example.viewmodelpractise.views.newUser.NewUserActivity;

import java.util.List;

public class UserViewModel extends AndroidViewModel {

    private final DatabaseCallRepository dbRepository;
    private final MutableLiveData<String> toastMessageObserver;
    private final MutableLiveData<Boolean> finishActivityObserver;
    private LiveData<List<User>> userList;

    public UserViewModel(Application application) {
        super(application);
        toastMessageObserver = new MutableLiveData<>();
        finishActivityObserver = new MutableLiveData<>();
        dbRepository = new DatabaseCallRepository(application);
    }

    public LiveData<List<User>> getUserList() {
        if (userList == null) {
            userList = new MutableLiveData<>();
            userList = dbRepository.getAllUsers();
        }
        return userList;
    }

    public MutableLiveData<String> getToastObserver() {
        return toastMessageObserver;
    }

    public MutableLiveData<Boolean> getFinishActivityObserver() {
        return finishActivityObserver;
    }

    public void startNewUserScreen(View view) {
        view.getContext().startActivity(new Intent(view.getContext(), NewUserActivity.class));
    }

    public void saveUser(User user) {
        if (isInputDataValid(user)) {
            dbRepository.insertUser(user);
            toastMessageObserver.setValue("Saved Successfully");
            finishActivityObserver.setValue(true);
        } else {
            toastMessageObserver.setValue("Name and Mobile Number can not be empty");
        }
    }

    public boolean isInputDataValid(User user) {
        return !TextUtils.isEmpty(user.getName()) && !TextUtils.isEmpty(user.getMobileNumber());
    }
}