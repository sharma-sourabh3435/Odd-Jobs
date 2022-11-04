package com.example.gorup16project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

/**
 * IDs for reference
 * textView2
 * editTextTextEmailAddress3
 * editTextTextPassword3
 * checkBox
 * buttonLogin
 * buttonCreateAccount
 */
public class MainActivity extends AppCompatActivity{

    CheckBox showcheck_btn;

    Button switchToSecondActivity, loginButton;
    TextView email;
    TextView password;


    private static final String URL = "https://group16-4df08-default-rtdb.firebaseio.com/";
    private FirebaseDatabase firebaseDB;
    private DatabaseReference firebaseDBRef;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        showcheck_btn = findViewById(R.id.checkBox);
        password = findViewById(R.id.editTextTextPassword3);
        email = findViewById(R.id.editTextTextEmailAddress3);

        mAuth = FirebaseAuth.getInstance();

        //Check and Uncheck the password on login page
        showcheck_btn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else {
                    password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });

        //change between login and create account page
        switchToSecondActivity = findViewById(R.id.buttonCreateAccount);
        switchToSecondActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchActivities();
            }
        });

         loginButton = findViewById(R.id.buttonLogin);
         loginButton.setOnClickListener(view -> {
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
                 mAuth.signInWithEmailAndPassword(em, pass).addOnCompleteListener(this,
                         new OnCompleteListener<AuthResult>() {
                     @Override
                     public void onComplete(@NonNull Task<AuthResult> task) {
                         if (task.isSuccessful()) {
                             FirebaseUser currentUser = mAuth.getCurrentUser();
                             Toast.makeText(MainActivity.this, "user login successful",
                                     Toast.LENGTH_SHORT).show();
                             startActivity(new Intent(MainActivity.this, welcomeMainPage.class));

                         }
                         else {
                             Toast.makeText(MainActivity.this, "Boo" +
                                             Objects.requireNonNull(task.getException()).getMessage(),
                                     Toast.LENGTH_SHORT).show();
                         }
                     }
                 });
             }
         });



    }


    //firebase authentication
    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user = mAuth.getCurrentUser();
        // if already signed in
        if(user != null) {
            startActivity(new Intent(MainActivity.this, welcomeMainPage.class));
        }
    }

    private void switchToActivites2() {
        Intent switchToCreate = new Intent(MainActivity.this, PayPal.class);
        startActivity(switchToCreate);
    }

    private void switchActivities() {
        Intent switchToCreate = new Intent(MainActivity.this, CreateAccount.class);
        startActivity(switchToCreate);
    }



}


