package com.example.viewmodelpractise.views.home;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.example.viewmodelpractise.R;
import com.example.viewmodelpractise.databinding.ActivityMainBinding;
import com.example.viewmodelpractise.viewmodels.UserViewModel;
import com.example.viewmodelpractise.views.newUser.NewUserActivity;

import tech.okcredit.layout_inflator.OkLayoutInflater;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private UserRecyclerViewAdapter recyclerViewAdapter;
    private UserViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loading_layout);

        OkLayoutInflater okLayoutInflater = new OkLayoutInflater(this);
        okLayoutInflater.inflate(R.layout.activity_main, null, (view, continuation) -> {
            binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
            binding = ActivityMainBinding.bind(view);
            setContentView(binding.getRoot());

            //View Model initialization
            mViewModel = new ViewModelProvider(this).get(UserViewModel.class);
            binding.setViewModel(mViewModel);
            binding.executePendingBindings();

            mViewModel
                    .getUserList()
                    .observe(this, userList -> {
                        recyclerViewAdapter = new UserRecyclerViewAdapter(mViewModel, userList);
                        binding.setAdapter(recyclerViewAdapter);
                    });

            mViewModel
                    .getOpenNewUserScreenObserver()
                    .observe(this, result -> {
                        newUserScreen();
                    });

            return binding.getRoot();
        });
    }

    private void newUserScreen() {
        Intent intent = new Intent(this, NewUserActivity.class);
        startActivity(intent);
    }
}