package com.example.dairaapp.Presenter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dairaapp.R;

public class ShowOcVH extends RecyclerView.ViewHolder {
    public TextView name,email,password;

    public ShowOcVH(@NonNull View itemView) {
        super(itemView);
        name = itemView.findViewById(R.id.ocnamedetail);
        email = itemView.findViewById(R.id.ocemaildetail);
        password = itemView.findViewById(R.id.ocpassworddetail);
    }
}
