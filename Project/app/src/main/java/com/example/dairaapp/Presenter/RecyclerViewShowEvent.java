package com.example.dairaapp.Presenter;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.dairaapp.Model.Event;
import com.example.dairaapp.R;
import com.example.dairaapp.Views.EventPanel;
import com.example.dairaapp.Views.SubEventPanel;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class RecyclerViewShowEvent extends AppCompatActivity {

    RecyclerView recyclerView;
    EventListAdapter adapter;
    DAOEvent dao;
    LinearLayoutManager manager;
    boolean isLoading = false;
    private StorageReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_show_event);

        reference = FirebaseStorage.getInstance().getReference().child("images/");

        recyclerView = findViewById(R.id.rv);
        recyclerView.setHasFixedSize(true);
        manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        adapter = new EventListAdapter(this);
        recyclerView.setAdapter(adapter);
        dao = new DAOEvent();
        loadData();

    }

    private void loadData() {


        dao.get().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<Event> events = new ArrayList<>();

                for(DataSnapshot data: snapshot.getChildren()){
                    Event event = data.getValue(Event.class);
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
        Intent intent = new Intent(RecyclerViewShowEvent.this, EventPanel.class);
        startActivity(intent);
    }
}