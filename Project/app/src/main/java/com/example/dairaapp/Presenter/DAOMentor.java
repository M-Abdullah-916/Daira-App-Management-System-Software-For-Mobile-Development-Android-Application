package com.example.dairaapp.Presenter;

import com.example.dairaapp.Model.Admin;
import com.example.dairaapp.Model.Mentor;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class DAOMentor {
    private DatabaseReference databaseReference;
    FirebaseDatabase db;
    boolean check = false;
    int counter;

    public DAOMentor(){
        db = FirebaseDatabase.getInstance("https://dairaapp-b516f-default-rtdb.firebaseio.com/");
        databaseReference = db.getReference("Users").child("Mentors");
        counter=0;
    }

    public Task<Void> add(Mentor mentor){return databaseReference.push().setValue(mentor);
    }

    public Task<Void> update(String key, HashMap<String,Object> hashmap){
        return databaseReference.child(key).updateChildren(hashmap);
    }

    public Query get(){
        return databaseReference.orderByKey();
    }

    public boolean getMentor(String parent){
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

    public void assignEvents(HashMap<String,String> values){
        databaseReference.setValue("AssignedEvents");
        databaseReference.child("AssignedValue" + counter).child("eventName").setValue(values.get("K1"));
        databaseReference.child("AssignedValue" + counter).child("ocName").setValue(values.get("K2"));
        databaseReference.child("AssignedValue" + counter).child("assignDate").setValue(values.get("K3"));
    }
}
