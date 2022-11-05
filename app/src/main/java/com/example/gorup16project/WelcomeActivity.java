package com.example.gorup16project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class WelcomeActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_employee);
        Toast myToast = Toast.makeText(this, "Welcome! You're Signed In As Employee", Toast.LENGTH_LONG);
        myToast.show();

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
