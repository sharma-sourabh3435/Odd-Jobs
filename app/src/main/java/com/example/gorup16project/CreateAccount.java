package com.example.gorup16project;

import android.os.Bundle;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class CreateAccount extends AppCompatActivity {
    Button switchToLogin;
    Button createAccount;
    TextView email;
    TextView password;

    protected FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_account);

        mAuth = FirebaseAuth.getInstance();
        createAccount = findViewById(R.id.button);
        email = findViewById(R.id.editTextTextEmailAddress2);
        password = findViewById(R.id.editTextTextPassword);
        createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //createUser();
                String em = email.getText().toString();
                String pass = password.getText().toString();

                if (TextUtils.isEmpty(em)) {
                    email.setError("Email can not be empty");
                    email.requestFocus();
                }

                else if(TextUtils.isEmpty(pass)) {
                    password.setError("Password can not be empty");
                    password.requestFocus();
                }

                else {
                    mAuth.createUserWithEmailAndPassword(em, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()) {
                                Toast.makeText(CreateAccount.this, "user registered successfully",
                                        Toast.LENGTH_SHORT).show();
                                //take user to login page
                                startActivity(new Intent(CreateAccount.this, MainActivity.class));
                            }
                            else {
                                Toast.makeText(CreateAccount.this, "Boo" + task.getException().getMessage(),
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }

            }
        });

        switchToLogin = findViewById(R.id.backToLoginButton);
        switchToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchActivities();
            }
        });

    }

    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        //set ==
        if(currentUser != null) {
            //refactor
            startActivity(new Intent(CreateAccount.this, welcomeMainPage.class));
        }
    }

    private void switchActivities() {
        Intent switchToLogin = new Intent(CreateAccount.this, MainActivity.class);
        startActivity(switchToLogin);
    }


}
