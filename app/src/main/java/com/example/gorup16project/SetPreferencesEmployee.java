package com.example.gorup16project;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;


public class SetPreferencesEmployee extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.preferences_employee);

        Button goBack = findViewById(R.id.back2employee);
        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent backToWelcome = new Intent(SetPreferencesEmployee.this, WelcomeActivity.class);
                startActivity(backToWelcome);
            }
        });

        Button addPreference = findViewById(R.id.addPreference);
        ListView jobPreferencesLV = findViewById(R.id.jobPreferencesLV);


        ArrayList<String> typePrefList = new ArrayList<>();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, typePrefList);
        jobPreferencesLV.setAdapter(adapter);


        //add preferred job type to list
        addPreference.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                EditText jobTypePrefEdit = findViewById(R.id.editTextJobTypePreference);
                String userTypePref = jobTypePrefEdit.getText().toString();
                if(!userTypePref.isEmpty()){
                    typePrefList.add(userTypePref);
                    adapter.notifyDataSetChanged();
                    jobTypePrefEdit.setText("");
                }
            }
        });

        Button clearPreferences = findViewById(R.id.clearPreferences);
        clearPreferences.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                typePrefList.clear();
                adapter.notifyDataSetChanged();
            }
        });

    }

}


