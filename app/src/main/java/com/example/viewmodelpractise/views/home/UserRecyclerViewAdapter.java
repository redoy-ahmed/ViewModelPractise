package com.example.viewmodelpractise.views.home;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.viewmodelpractise.BR;
import com.example.viewmodelpractise.R;
import com.example.viewmodelpractise.databinding.ListItemRowBinding;
import com.example.viewmodelpractise.models.User;
import com.example.viewmodelpractise.viewmodels.UserViewModel;

import java.util.List;

public class UserRecyclerViewAdapter extends RecyclerView.Adapter<UserRecyclerViewAdapter.ViewHolder> implements UserClickListener {

    private final UserViewModel userViewModel;
    private final List<User> userList;

    public UserRecyclerViewAdapter(UserViewModel userViewModel,List<User> userList) {
        this.userViewModel = userViewModel;
        this.userList = userList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ListItemRowBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.list_item_row, parent, false);

        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        User user = userList.get(position);
        holder.bind(user);
        holder.listItemRowBinding.setItemClickListener(this);
    }

    @Override
    public void userClicked(User user) {
        userViewModel.openNewUserScreen(user);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ListItemRowBinding listItemRowBinding;

        public ViewHolder(ListItemRowBinding listItemRowBinding) {
            super(listItemRowBinding.getRoot());
            this.listItemRowBinding = listItemRowBinding;
        }

        public void bind(Object obj) {
            listItemRowBinding.setVariable(BR.model, obj);
            listItemRowBinding.executePendingBindings();
        }
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }
}
