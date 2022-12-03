package com.example.gorup16project;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

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

        create.setOnClickListener(view -> {
            String em = email.getText().toString();
            String pass = password.getText().toString();
            String user = username.getText().toString();

            if (validationHandler(em, pass, user)){
                mAuth.createUserWithEmailAndPassword(em, pass).
                        addOnCompleteListener(task -> {

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
                                                Objects.requireNonNull(task.getException()).getMessage(),
                                        Toast.LENGTH_SHORT).show();
                            }
                        });
            }

        });

        switchToLogin = findViewById(R.id.backToLoginButton);
        switchToLogin.setOnClickListener(view -> switchActivities());

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

    private boolean validationHandler(String em, String pass, String user){
        if(!validateEmail(em)){
            return false;
        } else if(!validatePass(pass)){
            return false;
        } else return validateUser(user);
    }

    private boolean validateEmail(String em){
        if (TextUtils.isEmpty(em)) {
            email.setError("Email can not be empty");
            email.requestFocus();
            return false;
        }
        return true;
    }

    private boolean validatePass(String pass){
        if(TextUtils.isEmpty(pass)) {
            password.setError("Password can not be empty");
            password.requestFocus();
            return false;
        }
        return true;
    }

    private boolean validateUser(String user){
        if(TextUtils.isEmpty(user)) {
            username.setError("Username can not be empty");
            username.requestFocus();
            return false;
        }
        return true;
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
