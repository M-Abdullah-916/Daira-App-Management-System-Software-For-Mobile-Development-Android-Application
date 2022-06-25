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
import com.example.dairaapp.R;
import com.example.dairaapp.SplashScreen;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class AdminLogin extends AppCompatActivity {

    EditText emailText, passwordText;
    Button loginBtn;
    ProgressBar pb;
    private FirebaseAuth mAuth;

    DAOAdmin dao = new DAOAdmin();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);


        emailText = findViewById(R.id.adminemail);
        passwordText = findViewById(R.id.adminpassword);
        loginBtn = findViewById(R.id.adminloginbtn);
        mAuth = FirebaseAuth.getInstance();
        pb = findViewById(R.id.loginpb);


        loginBtn.setOnClickListener(v ->{
            Admin admin = new Admin(emailText.getText().toString(),passwordText.getText().toString());
            validate(emailText,passwordText);

            String email = emailText.getText().toString().trim();
            String password = passwordText.getText().toString().trim();
            Log.d("TAG",email + password);

            /*mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        pb.setVisibility(View.VISIBLE);
                        Intent intent = new Intent(AdminLogin.this,AdminMenu.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(AdminLogin.this, "Failed to Login", Toast.LENGTH_SHORT).show();
                        pb.setVisibility(View.GONE);
                    }
                }
            });*/

            mAuth.createUserWithEmailAndPassword(email,password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                Admin user = new Admin(email,password);
                                FirebaseDatabase.getInstance("https://smdporject-default-rtdb.firebaseio.com/").getReference("Users")
                                        .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                        .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if(task.isSuccessful()){
                                            Toast.makeText(AdminLogin.this, "You're now Registered", Toast.LENGTH_SHORT).show();

                                            Intent intent = new Intent(AdminLogin.this,SplashScreen.class);
                                            startActivity(intent);
                                        }else{
                                            Toast.makeText(AdminLogin.this, "Registered Not Successful", Toast.LENGTH_SHORT).show();

                                        }
                                    }
                                });
                            }else{
                                Toast.makeText(AdminLogin.this, "Registered Not Successful", Toast.LENGTH_SHORT).show();

                            }

                        }
                    });

            Intent intent = new Intent(AdminLogin.this, SplashScreen.class);
        });

    }

    private void validate(EditText emailText,EditText passwordText) {
        String email = emailText.getText().toString();
        String password = passwordText.getText().toString();

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
    }
}