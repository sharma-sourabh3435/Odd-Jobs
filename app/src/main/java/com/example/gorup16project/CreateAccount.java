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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class CreateAccount extends AppCompatActivity {
    Button switchToLogin;
    Button create;
    TextView email;
    TextView password;
    TextView username;
    protected FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_account);

        mAuth = FirebaseAuth.getInstance();
        create = findViewById(R.id.button);
        email = findViewById(R.id.editTextTextEmailAddress2);
        password = findViewById(R.id.editTextTextPassword);
        username = findViewById(R.id.createUsername);

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String em = email.getText().toString();
                String pass = password.getText().toString();
                String user = username.getText().toString();

                if (TextUtils.isEmpty(em)) {
                    email.setError("Email can not be empty");
                    email.requestFocus();
                }

                else if(TextUtils.isEmpty(pass)) {
                    password.setError("Password can not be empty");
                    password.requestFocus();
                }else if(TextUtils.isEmpty(user)) {
                    username.setError("Username can not be empty");
                    username.requestFocus();
                }

                else {
                    mAuth.createUserWithEmailAndPassword(em, pass).
                            addOnCompleteListener(new OnCompleteListener<AuthResult>() {

                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if(task.isSuccessful()) {
                                writeToDB();
                                Toast.makeText(CreateAccount.this, "User: registered successfully",
                                        Toast.LENGTH_SHORT).show();
                                //take user to login page
                                startActivity(new Intent(CreateAccount.this,
                                        MainActivity.class));
                            }
                            else {
                                Toast.makeText(CreateAccount.this, "Boo" +
                                                task.getException().getMessage(),
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
        Intent switchLogin = new Intent(CreateAccount.this, MainActivity.class);
        startActivity(switchLogin);
    }

    private void writeToDB(){
        Map<String, Object> map = new HashMap<>();
        String em = email.getText().toString();
        String user = username.getText().toString();
        map.put("username",user);
        map.put("email",em);
        FirebaseDatabase.getInstance(Config.FIREBASE_URL).getReference()
                .child(Config.USER_STRING)
                .push()
                .setValue(map).addOnSuccessListener(aVoid -> {
                    Toast.makeText(getApplicationContext(), "Employee updated successfully", Toast.LENGTH_SHORT).show();
                    finish();
                })
                .addOnFailureListener(e ->
                        Toast.makeText(getApplicationContext(), "Employee update failed", Toast.LENGTH_SHORT).show());

    }


}
