package com.example.dairaapp.Presenter;

import com.example.dairaapp.Model.Event;
import com.example.dairaapp.Model.SubEvent;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class DAOSubEvent {
    private DatabaseReference databaseReference;
    FirebaseDatabase db;
    boolean check = false;

    public DAOSubEvent() {
        db = FirebaseDatabase.getInstance("https://dairaapp-b516f-default-rtdb.firebaseio.com/");
        databaseReference = db.getReference("Events").child("SubEvents");
        check = false;
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

    public boolean getParent(String parent){
        DatabaseReference ref = db.getReference();
        Query query = ref.child("Events").orderByValue().equalTo(parent);

        ValueEventListener valueEventListener = new ValueEventListener()
        {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot)
            {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren())
                {
                    check = true;

                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError)
            {
                    check=false;
            }
        };
        query.addValueEventListener(valueEventListener);
        return check;
    }
}

