package com.example.viewmodelpractise.viewmodels;

import android.app.Application;
import android.text.TextUtils;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.viewmodelpractise.db.DatabaseCallRepository;
import com.example.viewmodelpractise.models.User;
import com.example.viewmodelpractise.utils.SingleLiveEvent;

import java.util.List;

public class UserViewModel extends AndroidViewModel {

    private final DatabaseCallRepository dbRepository;
    private final SingleLiveEvent<String> toastMessageObserver;
    private final SingleLiveEvent<Boolean> openNewUserScreenObserver;
    private final SingleLiveEvent<Boolean> finishActivityObserver;
    private final SingleLiveEvent<User> selectedUser;
    private LiveData<List<User>> userList;

    public UserViewModel(Application application) {
        super(application);
        toastMessageObserver = new SingleLiveEvent<>();
        openNewUserScreenObserver = new SingleLiveEvent<>();
        finishActivityObserver = new SingleLiveEvent<>();
        selectedUser = new SingleLiveEvent<>();
        dbRepository = new DatabaseCallRepository(application);
    }

    public LiveData<List<User>> getUserList() {
        if (userList == null) {
            userList = new MutableLiveData<>();
            userList = dbRepository.getAllUsers();
        }
        return userList;
    }

    public SingleLiveEvent<User> getSelectedUser() {
        return selectedUser;
    }

    public SingleLiveEvent<Boolean> getOpenNewUserScreenObserver() {
        return openNewUserScreenObserver;
    }

    public SingleLiveEvent<String> getToastObserver() {
        return toastMessageObserver;
    }

    public SingleLiveEvent<Boolean> getFinishActivityObserver() {
        return finishActivityObserver;
    }

    public void openNewUserScreen() {
        selectedUser.setValue(new User());
        openNewUserScreenObserver.setValue(true);
    }

    public void openNewUserScreen(User user) {
        selectedUser.setValue(user);
        openNewUserScreenObserver.setValue(true);
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