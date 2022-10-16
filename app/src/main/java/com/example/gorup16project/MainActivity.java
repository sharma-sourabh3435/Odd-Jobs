package com.example.gorup16project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

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
    EditText password;
    Button switchToSecondActivity;

    private static final String url = "https://group16-4df08-default-rtdb.firebaseio.com/";
    private FirebaseDatabase firebaseDB;
    private DatabaseReference firebaseDBRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        showcheck_btn = findViewById(R.id.checkBox);
        password = findViewById(R.id.editTextTextPassword3);

        firebaseDB = FirebaseDatabase.getInstance();
        firebaseDBRef = firebaseDB.getReference("server/saving-data/fireblog/posts");

        
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

        switchToSecondActivity = findViewById(R.id.buttonCreateAccount);
        switchToSecondActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                switchActivities();
            }
        });
    }
    private void switchActivities() {
        Intent switchToCreate = new Intent(MainActivity.this, CreateAccount.class);
        startActivity(switchToCreate);
    }



}


