package com.example.dairaapp.Presenter;

import com.example.dairaapp.Model.Mentor;
import com.example.dairaapp.Model.OC;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.HashMap;

public class DAOOc {
    private DatabaseReference databaseReference;

    public DAOOc(){
        FirebaseDatabase db = FirebaseDatabase.getInstance("https://dairaapp-b516f-default-rtdb.firebaseio.com/");
        databaseReference = db.getReference("Users").child("Mentor").child("OCs");
    }

    public Task<Void> add(OC oc){return databaseReference.push().setValue(oc);
    }

    public Task<Void> update(String key, HashMap<String,Object> hashmap){
        return databaseReference.child(key).updateChildren(hashmap);
    }

    public Query get(){
        return databaseReference.orderByKey();
    }
}
