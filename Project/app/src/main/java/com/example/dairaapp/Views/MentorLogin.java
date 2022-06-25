package com.example.dairaapp.Views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.dairaapp.Model.Admin;
import com.example.dairaapp.Model.DAOAdmin;
import com.example.dairaapp.Model.Mentor;
import com.example.dairaapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MentorLogin extends AppCompatActivity {

    EditText emailText, passwordText, nameText;
    Button loginBtn;
    ProgressBar pb;
    private FirebaseAuth mAuth;

    DAOAdmin dao = new DAOAdmin();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mentor_login);

        emailText = findViewById(R.id.mentoremail);
        passwordText = findViewById(R.id.mentorpassword);
        nameText = findViewById(R.id.mentorname);
        loginBtn = findViewById(R.id.mentorloginbtn);
        mAuth = FirebaseAuth.getInstance();
        pb = findViewById(R.id.loginpb);

        loginBtn.setOnClickListener(v ->{
            Mentor mentor = new Mentor(nameText.getText().toString(), emailText.getText().toString(),passwordText.getText().toString());
            validate(nameText,emailText,passwordText);

            String name = nameText.getText().toString().trim();
            String email = emailText.getText().toString().trim();
            String password = passwordText.getText().toString().trim();
            Log.d("TAG",name + email + password);

            mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        pb.setVisibility(View.VISIBLE);
                        Intent intent = new Intent(MentorLogin.this,MentorMenu.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(MentorLogin.this, "Failed to Login", Toast.LENGTH_SHORT).show();
                        pb.setVisibility(View.GONE);
                    }
                }
            });
        });
    }
    private void validate(EditText nameText, EditText emailText,EditText passwordText) {
        String email = emailText.getText().toString();
        String password = passwordText.getText().toString();
        String name = nameText.getText().toString();

        if(email.isEmpty()){
            emailText.setError("Please enter the email");
            emailText.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            emailText.setError("Please enter a valid email");
            emailText.requestFocus();
            return;
        }
        if(password.isEmpty()){
            passwordText.setError("Please enter the password");
            passwordText.requestFocus();
            return;
        }
        if(name.isEmpty()){
            passwordText.setError("Please enter the name");
            passwordText.requestFocus();
            return;
        }
    }

}
