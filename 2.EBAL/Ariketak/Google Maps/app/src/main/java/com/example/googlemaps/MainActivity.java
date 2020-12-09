package com.example.googlemaps;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;


import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends FragmentActivity implements OnMapReadyCallback
 {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
// Obtenemos el mapa de forma asíncrona (notificará cuando esté listo)
        SupportMapFragment mapFragment = (SupportMapFragment)
                getSupportFragmentManager().findFragmentById(R.id.mapa);
        mapFragment.getMapAsync(this);
    }

     @Override
     public void onMapReady(GoogleMap googleMap) {
         GoogleMap mapa = googleMap;
         LatLng oElorrieta = new LatLng(43.283531, -2.965031); // Elorrieta 43.283531, -2.965031
         mapa.addMarker(new MarkerOptions().position(oElorrieta).title("Marker Elorrieta"));
                 mapa.moveCamera(CameraUpdateFactory.newLatLng(oElorrieta));


     }
 }