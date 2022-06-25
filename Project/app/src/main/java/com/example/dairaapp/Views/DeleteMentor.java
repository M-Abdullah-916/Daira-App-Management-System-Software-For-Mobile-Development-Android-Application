package com.example.dairaapp.Views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.dairaapp.Model.Event;
import com.example.dairaapp.Model.Mentor;
import com.example.dairaapp.Presenter.DAOEvent;
import com.example.dairaapp.Presenter.DAOMentor;
import com.example.dairaapp.Presenter.EventDeleteAdpater;
import com.example.dairaapp.Presenter.MentorDeleteAdapter;
import com.example.dairaapp.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class DeleteMentor extends AppCompatActivity {


    RecyclerView recyclerView;
    MentorDeleteAdapter adapter;
    DAOMentor dao;
    LinearLayoutManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_mentor);



        recyclerView = findViewById(R.id.rvdeletementors);
        recyclerView.setHasFixedSize(true);
        manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        adapter = new MentorDeleteAdapter(this);
        recyclerView.setAdapter(adapter);
        dao = new DAOMentor();
        loadData();
    }

    private void loadData() {

        dao.get().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<Mentor> mentors = new ArrayList<>();

                for(DataSnapshot data: snapshot.getChildren()){
                    Mentor mentor = data.getValue(Mentor.class);
                    mentor.setKey(data.getKey());
                    mentors.add(mentor);

                }
                adapter.setItems(mentors);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


}