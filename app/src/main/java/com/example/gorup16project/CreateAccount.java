package com.example.gorup16project;

import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class CreateAccount extends AppCompatActivity {
    Button switchToLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_account);


        switchToLogin = findViewById(R.id.backToLoginButton);
        switchToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchActivities();
            }
        });
    }

    private void switchActivities() {
        Intent switchToLogin = new Intent(CreateAccount.this, MainActivity.class);
        startActivity(switchToLogin);
    }
}
