package com.example.gorup16project;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;

public class CreateJobPosting extends AppCompatActivity {

    TextView title;
    TextView pay;
    TextView location;
    TextView duration;
    Button postJob;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.createjob);
        title = findViewById(R.id.jobTitle);
        pay = findViewById(R.id.jobPay);
        location = findViewById(R.id.jobLocation);
        duration = findViewById(R.id.jobDuration);
        postJob=findViewById(R.id.postJobBtn);

        Button goBack = findViewById(R.id.back2employer);
        goBack.setOnClickListener(view -> backToEmployer());

        Button  logout = findViewById(R.id.logoutOfPosting);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logoutOfPosting();
            }
        });
        postJob.setOnClickListener(view -> writeToDB());

    }
    private void backToEmployer(){
        Intent switchToCreate = new Intent(CreateJobPosting.this, WelcomeActivityEmployer.class);
        startActivity(switchToCreate);
    }
    private void logoutOfPosting() {
        Intent switchToLogin = new Intent(CreateJobPosting.this,
                MainActivity.class);
        startActivity(switchToLogin);
    }

    private void writeToDB(){
        Map<String, Object> map = new HashMap<>();
        String dur = duration.getText().toString();
        String ttle = title.getText().toString();
        String py = pay.getText().toString();
        String loc = location.getText().toString();
        map.put("title",ttle);
        map.put("pay",py);
        map.put("duration",dur);
        map.put("location",loc);
        FirebaseDatabase.getInstance(Config.FIREBASE_URL).getReference()
                .child(Config.JOB_STRING)
                .push()
                .setValue(map).addOnSuccessListener(aVoid -> {
                    Toast.makeText(getApplicationContext(), "Job posted!", Toast.LENGTH_SHORT).show();
                    finish();
                })
                .addOnFailureListener(e ->
                        Toast.makeText(getApplicationContext(), "Job posting failed", Toast.LENGTH_SHORT).show());
    }

}