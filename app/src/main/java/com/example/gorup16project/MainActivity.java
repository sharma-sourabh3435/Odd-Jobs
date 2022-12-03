package com.example.gorup16project;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

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
        firebaseDB = FirebaseDatabase.getInstance(Config.FIREBASE_URL);
        firebaseDBRef = firebaseDB.getReference();

        //Check and Uncheck the password on login page
        showcheck_btn.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if(isChecked) {
                password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            } else {
                password.setTransformationMethod(PasswordTransformationMethod.getInstance());
            }
        });

        //change between login and create account page
        switchToSecondActivity = findViewById(R.id.buttonCreateAccount);
        switchToSecondActivity.setOnClickListener(view -> switchActivities());

         loginButton = findViewById(R.id.buttonLogin);
         loginButton.setOnClickListener(view -> {
             String em = email.getText().toString();
             String pass = password.getText().toString();

             if (validationHandler(em, pass)){
                 mAuth.signInWithEmailAndPassword(em, pass).addOnCompleteListener(this,
                         task -> {
                             if (task.isSuccessful()) {


                                 Toast.makeText(MainActivity.this, "User: "+firebaseDBRef.child("users").child("name").get() +"login successful",
                                         Toast.LENGTH_SHORT).show();
                                 startActivity(new Intent(MainActivity.this, welcomeMainPage.class));
                                 connectToFirebase();

                             }
                             else {
                                 Toast.makeText(MainActivity.this, "Boo" +
                                                 Objects.requireNonNull(task.getException()).getMessage(),
                                         Toast.LENGTH_SHORT).show();
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



    private void switchActivities() {
        Intent switchToCreate = new Intent(MainActivity.this, CreateAccount.class);
        startActivity(switchToCreate);
    }


    private void connectToFirebase(){
        firebaseDB = FirebaseDatabase.getInstance(Config.FIREBASE_URL);
        firebaseDBRef = firebaseDB.getReference();
    }

    private boolean validationHandler(String em, String pass){
        if(!validateEmail(em)){
            return false;
        } else return validatePass(pass);
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




}


