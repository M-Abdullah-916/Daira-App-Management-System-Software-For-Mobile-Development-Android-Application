package com.example.dairaapp.Presenter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dairaapp.Model.Mentor;
import com.example.dairaapp.Model.OC;
import com.example.dairaapp.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ShowOcAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    ArrayList<OC> list = new ArrayList<>();
    DAOOc dao;
    DatabaseReference dbReference;

    public ShowOcAdapter(Context context) {
        this.context = context;
    }

    public void setItems(ArrayList<OC> oc){
        list.addAll(oc);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.occardsingle,parent,false);
        return new ShowOcVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ShowOcVH vh = (ShowOcVH) holder;
        OC oc = list.get(position);
        dao = new DAOOc();
        vh.name.setText(oc.getName());
        vh.email.setText(oc.getEmail());
        vh.password.setText(oc.getPassword());
        dbReference = FirebaseDatabase.getInstance().getReference("Users").child("OCs");

    }


    @Override
    public int getItemCount() {
        return list.size();
    }
}

