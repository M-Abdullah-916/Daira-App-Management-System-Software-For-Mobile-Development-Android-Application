package com.example.dairaapp.Views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.dairaapp.Model.Mentor;
import com.example.dairaapp.Model.OC;
import com.example.dairaapp.Presenter.DAOMentor;
import com.example.dairaapp.Presenter.DAOOc;
import com.example.dairaapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class AddOC extends AppCompatActivity {

    EditText ocName,ocEmail, ocPassword,ocMentor;
    Button addOc;
    ProgressBar pb;
    DAOMentor dao;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_oc);

        dao = new DAOMentor();
        ocName = findViewById(R.id.ocName);
        ocEmail = findViewById(R.id.ocEmail);
        ocPassword = findViewById(R.id.ocPassword);
        ocMentor = findViewById(R.id.ocMentor);
        addOc = findViewById(R.id.addOcBtn);
        mAuth = FirebaseAuth.getInstance();
        pb = findViewById(R.id.addOcpb);

        addOc.setOnClickListener(v->{
            OC oc = new OC(ocName.getText().toString(),ocEmail.getText().toString(),ocPassword.getText().toString());
            validate(ocName,ocEmail,ocPassword,ocMentor);

            String name = ocName.getText().toString();
            String email = ocEmail.getText().toString();
            String password = ocPassword.getText().toString();
            mAuth.createUserWithEmailAndPassword(email,password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                OC user = new OC(name,email,password);
                                FirebaseDatabase.getInstance("https://dairaapp-b516f-default-rtdb.firebaseio.com/").getReference("Users")
                                        .child("OCs").child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                        .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if(task.isSuccessful()){
                                            Toast.makeText(AddOC.this, "You're now Registered", Toast.LENGTH_SHORT).show();

                                            Intent intent = new Intent(AddOC.this,OCPanel.class);
                                            startActivity(intent);
                                        }else{
                                            Toast.makeText(AddOC.this, "Registered Not Successful", Toast.LENGTH_SHORT).show();

                                        }
                                    }
                                });
                            }else{
                                Toast.makeText(AddOC.this, "Registered Not Successful", Toast.LENGTH_SHORT).show();

                            }

                        }
                    });
        });
    }
    private void validate(EditText ocName,EditText ocEmail,EditText ocPassword,EditText ocMentor) {
        String name = ocName.getText().toString();
        String email = ocEmail.getText().toString();
        String password = ocPassword.getText().toString();
        String mentor = ocMentor.getText().toString();

        if(name.isEmpty()){
            ocName.setError("Please enter a name");
            ocName.requestFocus();
            return;
        }
        if(email.isEmpty()){
            ocEmail.setError("Please enter the email");
            ocEmail.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            ocEmail.setError("Please enter a valid email");
            ocEmail.requestFocus();
            return;
        }
        if(password.isEmpty()){
            ocPassword.setError("Please enter the password");
            ocPassword.requestFocus();
            return;
        }
        if(!dao.getMentor(mentor)){
            ocMentor.setError("Mentor Not Found");
            ocMentor.requestFocus();
            return;
        }
    }
}