package com.example.viewmodelpractise.views.home;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.example.viewmodelpractise.R;
import com.example.viewmodelpractise.databinding.ActivityMainBinding;
import com.example.viewmodelpractise.viewmodels.UserViewModel;

import tech.okcredit.layout_inflator.OkLayoutInflater;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private UserRecyclerViewAdapter recyclerViewAdapter;
    private OkLayoutInflater okLayoutInflater;
    private UserViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loading_layout);

        okLayoutInflater = new OkLayoutInflater(this);
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
                        recyclerViewAdapter = new UserRecyclerViewAdapter(this, userList);
                        binding.setAdapter(recyclerViewAdapter);
                    });

            return binding.getRoot();
        });
    }
}