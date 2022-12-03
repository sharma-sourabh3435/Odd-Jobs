package com.example.gorup16project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class SetPreferencesEmployee extends AppCompatActivity {

    EditText jobTypePrefEdit;
    Button addPreference;
    ListView jobPreferencesLV;
    String userTypePref;
    String getUpdateUserKey;
    public static final String UPDATE_USER_KEY = "UPDATE_USER_KEY";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.preferences_employee);

        Button goBack = findViewById(R.id.backFromPreference);
        goBack.setOnClickListener(view -> {
            Intent backToWelcome = new Intent(SetPreferencesEmployee.this, WelcomeActivity.class);
            startActivity(backToWelcome);
        });

         addPreference = findViewById(R.id.addPreference);
         jobPreferencesLV = findViewById(R.id.jobPreferences);


        ArrayList<String> typePrefList = new ArrayList<>();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, typePrefList);
        jobPreferencesLV.setAdapter(adapter);


        //add preferred job type to list
        addPreference.setOnClickListener(view -> {
             jobTypePrefEdit = findViewById(R.id.setName);
             userTypePref = jobTypePrefEdit.getText().toString();

            writeToDB();
            if(!userTypePref.isEmpty()){
                typePrefList.add(userTypePref);
                adapter.notifyDataSetChanged();

                jobTypePrefEdit.setText("");

            }
        });

        Button clearPreferences = findViewById(R.id.clearPreferences);
        clearPreferences.setOnClickListener(view -> {
            typePrefList.clear();
            adapter.notifyDataSetChanged();
        });

    }

    private void writeToDB(){
        Map<String, Object> map = new HashMap<>();
        String pref = jobTypePrefEdit.getText().toString();

        map.put("preference",pref);
        FirebaseDatabase.getInstance(Config.FIREBASE_URL).getReference(Config.USER_STRING)
                .updateChildren(map);

    }

}


