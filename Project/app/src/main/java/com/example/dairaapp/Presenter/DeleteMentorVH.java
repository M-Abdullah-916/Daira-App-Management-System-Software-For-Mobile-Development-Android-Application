package com.example.dairaapp.Presenter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dairaapp.R;

public class DeleteMentorVH  extends RecyclerView.ViewHolder {
    public TextView name,email,password;
    ConstraintLayout layoutId;

    public DeleteMentorVH(@NonNull View itemView) {
        super(itemView);
        name = itemView.findViewById(R.id.mentornamedetail);
        email = itemView.findViewById(R.id.mentoremaildetail);
        password = itemView.findViewById(R.id.mentorpassworddetail);
        layoutId = itemView.findViewById(R.id.mentorcardsingle);
    }
}
