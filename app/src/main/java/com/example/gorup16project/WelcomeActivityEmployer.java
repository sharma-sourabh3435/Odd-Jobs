package com.example.gorup16project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class WelcomeActivityEmployer extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_employer);
        Toast myToast = Toast.makeText(this, "Welcome! You're Signed In As Employer", Toast.LENGTH_LONG);
        myToast.show();

        Button switchToLogin = findViewById(R.id.Logout);
        switchToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchToLoginPage();
            }
        });
        Button switchToCreateJob = findViewById(R.id.createJobBtn);
        switchToCreateJob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchToCreatePosting();
            }
        });
    }

    private void switchToLoginPage() {
        Intent switchToLogin = new Intent(WelcomeActivityEmployer.this,
                MainActivity.class);
        startActivity(switchToLogin);
    }

    private void switchToCreatePosting(){
        Intent switchToPosting = new Intent(WelcomeActivityEmployer.this,
                CreateJobPosting.class);
        startActivity(switchToPosting);
    }
}
