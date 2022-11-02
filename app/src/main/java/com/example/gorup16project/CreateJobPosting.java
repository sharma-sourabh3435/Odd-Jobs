package com.example.gorup16project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class CreateJobPosting extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.createjob);

        Button goBack = findViewById(R.id.back2employer);
        goBack.setOnClickListener(view -> backToEmployer());

        Button  logout = findViewById(R.id.logoutOfPosting);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logoutOfPosting();
            }
        });

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

}