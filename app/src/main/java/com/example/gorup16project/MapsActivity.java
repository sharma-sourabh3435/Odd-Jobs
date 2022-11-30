package com.example.gorup16project;


import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;

import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;
import java.util.Locale;


/*
 Google map citation
 Author: Masud Rahman
 URL: https://git.cs.dal.ca/masud/csci3130-cookbook
 */


public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {
    Location location;
    String latlong = new String("44.654685,-63.593184");
    String city = new String("Halifax");

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
//        getLocation();
//        getAddress();
        this.captureIntent();
    }

//    // gets the users location
//    protected void getLocation(){
//        location = new Location("");
//        double longitude = location.getLongitude();
//        double latitude = location.getLatitude();
//        latlong = new String(longitude+", " +latitude);
//    }

////    // gets the users location
//    protected void getAddress(){
//        Geocoder geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());
//        try {
//            List<Address> listAddresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
//            if (null != listAddresses && listAddresses.size() > 0) {
//                city = listAddresses.get(0).getAdminArea();
//
//            }
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }




    protected void captureIntent() {
        try {
            Intent latlongItent = getIntent();
            if (latlongItent != null) {
                if (latlongItent.hasExtra("taskLocation"))
                    this.latlong = latlongItent.getStringExtra("taskLocation");
                if (latlongItent.hasExtra("city"))
                    this.city = latlongItent.getStringExtra("city");
            }
        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }




    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        String[] parts = latlong.split(",");
        double latitude = Double.parseDouble(parts[0].trim());
        double longitude = Double.parseDouble(parts[1].trim());

        LatLng itemLocation = new LatLng(latitude, longitude);
        mMap.addMarker(new MarkerOptions().position(itemLocation).title(this.city));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(itemLocation));
    }
}
