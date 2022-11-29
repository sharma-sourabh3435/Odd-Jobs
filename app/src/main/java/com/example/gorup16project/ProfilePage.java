package com.example.gorup16project;

import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class ProfilePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_page);

        Button switchToLocation = findViewById(R.id.Location);
        switchToLocation.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switchActivities();
        }
    });



}

    private void switchActivities() {
        Intent switchToCreate = new Intent(ProfilePage.this, MapsActivity.class);
        startActivity(switchToCreate);
    }


}
