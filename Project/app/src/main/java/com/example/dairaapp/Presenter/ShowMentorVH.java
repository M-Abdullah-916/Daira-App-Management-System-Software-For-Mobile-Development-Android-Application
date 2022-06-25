package com.example.dairaapp.Presenter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dairaapp.R;

public class ShowMentorVH extends RecyclerView.ViewHolder {
    public TextView name,email,password;

    public ShowMentorVH(@NonNull View itemView) {
        super(itemView);
        name = itemView.findViewById(R.id.mentornamedetail);
        email = itemView.findViewById(R.id.mentoremaildetail);
        password = itemView.findViewById(R.id.mentorpassworddetail);
    }
}
