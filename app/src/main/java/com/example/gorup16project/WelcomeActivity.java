package com.example.gorup16project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class WelcomeActivity extends AppCompatActivity {

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
                switchToLoginPage();
            }
        });

        Button switchToPreferences = findViewById(R.id.setPreferences);
        switchToPreferences.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchToUserPrefs();
            }
        });
    }

    private void switchToLoginPage() {
        Intent switchToLogin = new Intent(WelcomeActivity.this, MainActivity.class);
        startActivity(switchToLogin);
    }

    private void switchToUserPrefs() {
        Intent switchToPreferences = new Intent(WelcomeActivity.this, SetPreferencesEmployee.class);
        startActivity(switchToPreferences);
    }
}
