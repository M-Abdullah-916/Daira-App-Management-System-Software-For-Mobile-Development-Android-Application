package com.example.dairaapp.Views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.dairaapp.Model.Admin;
import com.example.dairaapp.Model.Mentor;
import com.example.dairaapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class AddMentor extends AppCompatActivity {

    EditText mentorName,mentorEmail, mentorPassword;
    Button addMentor;
    ProgressBar pb;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_mentor);

        mentorName = findViewById(R.id.mentorName);
        mentorEmail = findViewById(R.id.mentorEmail);
        mentorPassword = findViewById(R.id.mentorPassword);
        addMentor = findViewById(R.id.addMentorBtn);
        mAuth = FirebaseAuth.getInstance();
        pb = findViewById(R.id.addMentorpb);

        addMentor.setOnClickListener(v->{
            Mentor mentor = new Mentor(mentorName.getText().toString(),mentorEmail.getText().toString(),mentorPassword.getText().toString());
            validate(mentorName,mentorEmail,mentorPassword);

            String name = mentorName.getText().toString();
            String email = mentorEmail.getText().toString();
            String password = mentorPassword.getText().toString();

            /*mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        pb.setVisibility(View.VISIBLE);
                        Intent intent = new Intent(AddMentor.this,AdminMenu.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(AddMentor.this, "Failed to Register", Toast.LENGTH_SHORT).show();
                        pb.setVisibility(View.GONE);
                    }
                }
            });*/
            mAuth.createUserWithEmailAndPassword(email,password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                Mentor user = new Mentor(name,email,password);
                                FirebaseDatabase.getInstance("https://dairaapp-b516f-default-rtdb.firebaseio.com/").getReference("Users")
                                        .child("Mentors").child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                        .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if(task.isSuccessful()){
                                            Toast.makeText(AddMentor.this, "You're now Registered", Toast.LENGTH_SHORT).show();

                                            Intent intent = new Intent(AddMentor.this,MentorPanel.class);
                                            startActivity(intent);
                                        }else{
                                            Toast.makeText(AddMentor.this, "Registered Not Successful", Toast.LENGTH_SHORT).show();

                                        }
                                    }
                                });
                            }else{
                                Toast.makeText(AddMentor.this, "Registered Not Successful", Toast.LENGTH_SHORT).show();

                            }

                        }
                    });
        });

    }

    private void validate(EditText mentorName,EditText mentorEmail,EditText mentorPassword) {
        String name = mentorName.getText().toString();
        String email = mentorEmail.getText().toString();
        String password = mentorPassword.getText().toString();

        if(name.isEmpty()){
            mentorName.setError("Please enter a name");
            mentorName.requestFocus();
            return;
        }
        if(email.isEmpty()){
            mentorEmail.setError("Please enter the email");
            mentorEmail.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            mentorEmail.setError("Please enter a valid email");
            mentorEmail.requestFocus();
            return;
        }
        if(password.isEmpty()){
            mentorPassword.setError("Please enter the password");
            mentorPassword.requestFocus();
            return;
        }
    }
}