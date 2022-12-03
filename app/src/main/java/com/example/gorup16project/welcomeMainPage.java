package com.example.gorup16project;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class welcomeMainPage extends AppCompatActivity {

    private FirebaseAuth mAuth;
    Button logout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_mainpage);

        logout = findViewById(R.id.logoutButton);
        mAuth = FirebaseAuth.getInstance();

        logout.setOnClickListener(view -> {
            mAuth.signOut();
            startActivity(new Intent(welcomeMainPage.this, MainActivity.class));
        });

        //button for employee page
        Button continueAsEmployee = findViewById(R.id.toEmployeePage);
        continueAsEmployee.setOnClickListener(view -> switchToWelcomeEmployee());

        //button for employer page
        Button continueAsEmployer = findViewById(R.id.toEmployerPage);
        continueAsEmployer.setOnClickListener(view -> switchToWelcomeEmployer());
    }

    //switch to employee's login page
    private void switchToWelcomeEmployee(){
        Intent continueAsEmployee = new Intent(welcomeMainPage.this, WelcomeActivity.class);
        startActivity(continueAsEmployee);
    }
    //switch to employer's login page
    private void switchToWelcomeEmployer(){
        Intent continueAsEmployer = new Intent(welcomeMainPage.this, WelcomeActivityEmployer.class);
        startActivity(continueAsEmployer);
    }


}
