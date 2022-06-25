package com.example.dairaapp.Views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.dairaapp.Model.Mentor;
import com.example.dairaapp.Model.OC;
import com.example.dairaapp.Presenter.DAOMentor;
import com.example.dairaapp.Presenter.DAOOc;
import com.example.dairaapp.Presenter.MentorDeleteAdapter;
import com.example.dairaapp.Presenter.OCDeleteAdapter;
import com.example.dairaapp.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DeleteOC extends AppCompatActivity {

    RecyclerView recyclerView;
    OCDeleteAdapter adapter;
    DAOOc dao;
    LinearLayoutManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_oc);

        recyclerView = findViewById(R.id.rvdeleteoc);
        recyclerView.setHasFixedSize(true);
        manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        adapter = new OCDeleteAdapter(this);
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