package com.example.dairaapp.Presenter;

import androidx.annotation.NonNull;

import com.example.dairaapp.Model.Admin;
import com.example.dairaapp.Model.Event;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class DAOEvent {
    private DatabaseReference databaseReference;

    public DAOEvent(){
        FirebaseDatabase db = FirebaseDatabase.getInstance("https://dairaapp-b516f-default-rtdb.firebaseio.com/");
        databaseReference = db.getReference("Events");
    }

    public Task<Void> add(Event event){
        return databaseReference.push().setValue(event);
    }

    public Task<Void> update(String key, HashMap<String,Object> hashmap){
        return databaseReference.child(key).updateChildren(hashmap);
    }

    public Query get(){
        return databaseReference.orderByKey();
    }

}
