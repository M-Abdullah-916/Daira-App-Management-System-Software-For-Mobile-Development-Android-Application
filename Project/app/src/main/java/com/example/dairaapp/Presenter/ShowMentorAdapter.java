package com.example.dairaapp.Presenter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dairaapp.Model.Mentor;
import com.example.dairaapp.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ShowMentorAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    ArrayList<Mentor> list = new ArrayList<>();
    DAOMentor dao;
    DatabaseReference dbReference;

    public ShowMentorAdapter(Context context) {
        this.context = context;
    }

    public void setItems(ArrayList<Mentor> mentor){
        list.addAll(mentor);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.singlementor,parent,false);
        return new ShowMentorVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ShowMentorVH vh = (ShowMentorVH) holder;
        Mentor mentor = list.get(position);
        dao = new DAOMentor();
        vh.name.setText(mentor.getName());
        vh.email.setText(mentor.getEmail());
        vh.password.setText(mentor.getPassword());
        dbReference = FirebaseDatabase.getInstance().getReference("Users").child("Mentors");

    }


    @Override
    public int getItemCount() {
        return list.size();
    }
}
