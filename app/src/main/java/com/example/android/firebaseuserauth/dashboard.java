package com.example.android.firebaseuserauth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class dashboard extends AppCompatActivity {
    TextView t1;
    Button b1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        t1 = findViewById(R.id.email_home);

        t1.setText(getIntent().getStringExtra("email").toString());

       b1 = findViewById(R.id.dash_button);
       b1.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               FirebaseAuth.getInstance().signOut();
               startActivity(new Intent(dashboard.this,MainActivity.class));

           }
       });
    }
}