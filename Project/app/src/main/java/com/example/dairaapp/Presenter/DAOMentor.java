package com.example.dairaapp.Presenter;

import com.example.dairaapp.Model.Admin;
import com.example.dairaapp.Model.Mentor;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.HashMap;

public class DAOMentor {
    private DatabaseReference databaseReference;

    public DAOMentor(){
        FirebaseDatabase db = FirebaseDatabase.getInstance("https://dairaapp-b516f-default-rtdb.firebaseio.com/");
        databaseReference = db.getReference("Users").child("Mentors");
    }

    public Task<Void> add(Mentor mentor){return databaseReference.push().setValue(mentor);
    }

    public Task<Void> update(String key, HashMap<String,Object> hashmap){
        return databaseReference.child(key).updateChildren(hashmap);
    }

    public Query get(){
        return databaseReference.orderByKey();
    }
}
