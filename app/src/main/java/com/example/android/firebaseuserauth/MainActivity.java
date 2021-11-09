package com.example.android.firebaseuserauth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    EditText t1,t2;
    Button submit;
    ProgressBar bar;
    TextView ar;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        t1 = findViewById(R.id.login_email);
        t2 = findViewById(R.id.login_pwd);
        submit = findViewById(R.id.login_button);
        bar = findViewById(R.id.bar);
        ar = findViewById(R.id.textView2);

        ar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,login.class);
                startActivity(intent);
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = t1.getText().toString();
                String password = t2.getText().toString();
              

                bar.setVisibility(View.VISIBLE);
        ;

                mAuth = FirebaseAuth.getInstance();

                mAuth.createUserWithEmailAndPassword(email,password)
                        .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    bar.setVisibility(View.INVISIBLE);
                                    t1.setText("");
                                    t2.setText("");
                                    Toast.makeText(MainActivity.this, "Registered successfully", Toast.LENGTH_SHORT).show();

                                } else {
                                    bar.setVisibility(View.INVISIBLE);
                                    t1.setText("");
                                    t2.setText("");
                                    Toast.makeText(MainActivity.this, "Error Occur", Toast.LENGTH_SHORT).show();


                                }
                            }
                        });

            }
        });


    }
}