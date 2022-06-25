package com.example.dairaapp.Views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.dairaapp.Model.Event;
import com.example.dairaapp.Presenter.DAOEvent;
import com.example.dairaapp.Presenter.EventDeleteAdpater;
import com.example.dairaapp.Presenter.EventUpdateAdpater;
import com.example.dairaapp.Presenter.RecyclerViewUpdateEvent;
import com.example.dairaapp.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class DeleteEvent extends AppCompatActivity {

    RecyclerView recyclerView;
    EventDeleteAdpater adapter;
    DAOEvent dao;
    LinearLayoutManager manager;
    private StorageReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_event);

        reference = FirebaseStorage.getInstance().getReference().child("images/");

        recyclerView = findViewById(R.id.rvDeleteEvent);
        recyclerView.setHasFixedSize(true);
        manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        adapter = new EventDeleteAdpater(this);
        recyclerView.setAdapter(adapter);
        dao = new DAOEvent();
        loadData();
    }

    @Override
    protected void onStart() {
        super.onStart();
        while(this.isActivityTransitionRunning())
            loadData();
    }

    private void loadData() {

        dao.get().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<Event> events = new ArrayList<>();

                for(DataSnapshot data: snapshot.getChildren()){
                    Event event = data.getValue(Event.class);
                    event.setKey(data.getKey());
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
        Intent intent = new Intent(DeleteEvent.this, EventPanel.class);
        startActivity(intent);
    }
}