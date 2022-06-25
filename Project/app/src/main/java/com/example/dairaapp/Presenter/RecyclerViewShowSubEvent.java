package com.example.dairaapp.Presenter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.dairaapp.Model.Event;
import com.example.dairaapp.Model.SubEvent;
import com.example.dairaapp.R;
import com.example.dairaapp.Views.EventPanel;
import com.example.dairaapp.Views.SubEventPanel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class RecyclerViewShowSubEvent extends AppCompatActivity {

    RecyclerView recyclerView;
    SubEventListAdapter adapter;
    DAOSubEvent dao;
    LinearLayoutManager manager;
    boolean isLoading = false;
    private StorageReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_show_sub_event);

        reference = FirebaseStorage.getInstance().getReference().child("images/");

        recyclerView = findViewById(R.id.rvs);
        recyclerView.setHasFixedSize(true);
        manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        adapter = new SubEventListAdapter(this);
        recyclerView.setAdapter(adapter);
        dao = new DAOSubEvent();
        loadData();
    }

    private void loadData() {
        dao.get().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<SubEvent> events = new ArrayList<>();
                for(DataSnapshot data: snapshot.getChildren()){
                    SubEvent event = data.getValue(SubEvent.class);
                    events.add(event);

                }
                adapter.setItems(events);
                adapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }

    public void GoBack(View view) {
        Intent intent = new Intent(RecyclerViewShowSubEvent.this, SubEventPanel.class);
        startActivity(intent);
    }
}