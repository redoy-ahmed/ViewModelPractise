package com.example.viewmodelpractise.views.newUser;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.example.viewmodelpractise.R;
import com.example.viewmodelpractise.databinding.ActivityNewUserBinding;
import com.example.viewmodelpractise.viewmodels.UserViewModel;

public class NewUserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user);
        ActivityNewUserBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_new_user);

        //View Model initialization
        UserViewModel mViewModel = new ViewModelProvider(this).get(UserViewModel.class);
        binding.setViewModel(mViewModel);

        mViewModel
                .getSelectedUser()
                .observe(this, binding::setModel);

        mViewModel
                .getFinishActivityObserver()
                .observe(this, result -> {
                    finish();
                });

        mViewModel
                .getToastObserver()
                .observe(this, message -> {
                    Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
                });
    }
}