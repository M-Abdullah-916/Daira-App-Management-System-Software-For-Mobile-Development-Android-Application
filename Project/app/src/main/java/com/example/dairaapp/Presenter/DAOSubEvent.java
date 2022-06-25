package com.example.dairaapp.Presenter;

import com.example.dairaapp.Model.Event;
import com.example.dairaapp.Model.SubEvent;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.HashMap;

public class DAOSubEvent {
    private DatabaseReference databaseReference;

    public DAOSubEvent() {
        FirebaseDatabase db = FirebaseDatabase.getInstance("https://dairaapp-b516f-default-rtdb.firebaseio.com/");
        databaseReference = db.getReference("SubEvents");
    }

    public Task<Void> add(SubEvent event){
        return databaseReference.push().setValue(event);
    }

    public Task<Void> update(String key, HashMap<String,Object> hashmap){
        return databaseReference.child(key).updateChildren(hashmap);
    }

    public Query get(){
        return databaseReference.orderByKey();
    }
}
