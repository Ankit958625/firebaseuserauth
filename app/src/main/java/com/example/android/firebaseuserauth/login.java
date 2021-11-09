package com.example.android.firebaseuserauth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class login extends AppCompatActivity {
    EditText t1,t2;
    Button submit;
    ProgressBar bar;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        t1 = findViewById(R.id.login_email);
        t2=findViewById(R.id.login_pwd);
        submit = findViewById(R.id.login_button);
        bar= findViewById(R.id.bar);
        mAuth = FirebaseAuth.getInstance();

             submit.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {
                     bar.setVisibility(View.VISIBLE);
                     String email = t1.getText().toString();
                     String password = t2.getText().toString();

                     mAuth.signInWithEmailAndPassword(email,password)
                             .addOnCompleteListener(login.this, new OnCompleteListener<AuthResult>() {
                                 @Override
                                 public void onComplete(@NonNull Task<AuthResult> task) {
                                     if (task.isSuccessful()) {
                                         bar.setVisibility(View.INVISIBLE);
                                         Intent intent = new Intent(login.this,dashboard.class);
                                         intent.putExtra("email",mAuth.getCurrentUser().getEmail());

                                         startActivity(intent);
                                         Toast.makeText(login.this, "successful", Toast.LENGTH_SHORT).show();

                                     } else {
                                         bar.setVisibility(View.INVISIBLE);
                                         t1.setText("");
                                         t2.setText("");
                                         Toast.makeText(login.this, "Invalid email/password", Toast.LENGTH_SHORT).show();

                                     }
                                 }
                             });

                 }
             });
    }
}