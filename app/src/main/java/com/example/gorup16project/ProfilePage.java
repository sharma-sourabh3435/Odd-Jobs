package com.example.gorup16project;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class ProfilePage extends AppCompatActivity {

    Button goBack;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_page);

        goBack = findViewById(R.id.backFromProfile);
        goBack.setOnClickListener(view -> startActivity(new Intent(getApplicationContext(), WelcomeActivity.class)));

        Button switchToLocation = findViewById(R.id.Location);
        switchToLocation.setOnClickListener(view -> switchToMaps());

}
    private void switchToMaps() {
        Intent switchToCreate = new Intent(ProfilePage.this, MapsActivity.class);
        startActivity(switchToCreate);
    }


}
