package com.example.gorup16project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class WelcomeActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FloatingActionButton profileFab;

        setContentView(R.layout.welcome_employee);

        Toast myToast = Toast.makeText(this, "Welcome! You're Signed In As Employee", Toast.LENGTH_LONG);
        myToast.show();

        profileFab = findViewById(R.id.profileBtn);
        profileFab.setOnClickListener(view ->  startActivity(new Intent(getApplicationContext(), ProfilePage.class)));
        Button switchToLogin = findViewById(R.id.Logout);
        switchToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchActivities();
            }
        });

        Button switchToPreferences = findViewById(R.id.setPreferences);
        switchToPreferences.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchActivities2();
            }
        });

        mRecyclerView = (RecyclerView) findViewById(R.id.re1);
        new firebaseDB().readJobs(new firebaseDB.DataStatus() {
            @Override
            public void DataIsLoaded(List<jobs> jobs, List<String> keys) {
                new RecyclerView_con().setConfig(mRecyclerView, WelcomeActivity.this, jobs, keys);
            }

            @Override
            public void DataIsInserted() {

            }

            @Override
            public void DataIsUpdated() {

            }

            @Override
            public void DataIsDeleted() {

            }
        });
    }

    private void switchActivities() {
        Intent switchToLogin = new Intent(WelcomeActivity.this, MainActivity.class);
        startActivity(switchToLogin);
    }

    private void switchActivities2() {
        Intent switchToPreferences = new Intent(WelcomeActivity.this, SetPreferencesEmployee.class);
        startActivity(switchToPreferences);
    }
}
