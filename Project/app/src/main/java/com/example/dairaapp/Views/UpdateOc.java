package com.example.dairaapp.Views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.dairaapp.Model.OC;
import com.example.dairaapp.Model.SubEvent;
import com.example.dairaapp.Presenter.DAOOc;
import com.example.dairaapp.Presenter.DAOSubEvent;
import com.example.dairaapp.Presenter.OCUpdateAdapter;
import com.example.dairaapp.Presenter.SubEventUpdateAdapter;
import com.example.dairaapp.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class UpdateOc extends AppCompatActivity {

    RecyclerView recyclerView;
    OCUpdateAdapter adapter;
    DAOOc dao;
    LinearLayoutManager manager;
    private StorageReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_oc);

        reference = FirebaseStorage.getInstance().getReference().child("images/");

        recyclerView = findViewById(R.id.rvupdateoc);
        recyclerView.setHasFixedSize(true);
        manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        adapter = new OCUpdateAdapter(this);
        recyclerView.setAdapter(adapter);
        dao = new DAOOc();
        loadData();
    }
    private void loadData() {

        dao.get().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<OC> ocs = new ArrayList<>();

                for(DataSnapshot data: snapshot.getChildren()){
                    OC oc = data.getValue(OC.class);
                    oc.setKey(data.getKey());
                    ocs.add(oc);
                }
                adapter.setItems(ocs);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}