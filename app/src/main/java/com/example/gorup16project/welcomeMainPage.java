package com.example.gorup16project;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class welcomeMainPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_mainpage);

        //button for employee page
        Button continueAsEmployee = findViewById(R.id.toEmployeePage);
        continueAsEmployee.setOnClickListener(view -> switchActivities1());

        //button for employer page
        Button continueAsEmployer = findViewById(R.id.toEmployerPage);
        continueAsEmployer.setOnClickListener(view -> switchActivities2());
    }

    //switch to employee's login page
    private void switchActivities1(){
        Intent continueAsEmployee = new Intent(welcomeMainPage.this, WelcomeActivity.class);
        startActivity(continueAsEmployee);
    }
    //switch to employer's login page
    private void switchActivities2(){
        Intent continueAsEmployer = new Intent(welcomeMainPage.this, WelcomeActivityEmployer.class);
        startActivity(continueAsEmployer);
    }
}
