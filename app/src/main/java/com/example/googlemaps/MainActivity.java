package com.example.googlemaps;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.Projection;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;

public class MainActivity
        extends AppCompatActivity
        implements OnMapReadyCallback, GoogleMap.OnMapClickListener {

    GoogleMap mapa;
    int contador;
    ArrayList<LatLng> puntos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        contador = 0;
        puntos = new ArrayList<LatLng>();

    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mapa = googleMap;
        mapa.getUiSettings().setZoomControlsEnabled(true);
        mapa.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        CameraUpdate camUpd1 = CameraUpdateFactory
                .newLatLngZoom(new LatLng(
                        -1.012244955648867, -79.46963594864485),15);  //zoom de 20

        mapa.moveCamera(camUpd1);
        mapa.setOnMapClickListener(this);


    }

    @Override
    public void onMapClick(@NonNull LatLng latLng) {
        Projection proj = mapa.getProjection();
        Point coord = proj.toScreenLocation(latLng);

        TextView lblLatitud = findViewById(R.id.lblLatitude);
        lblLatitud.setText(String.format("%.4f",latLng.latitude));

        TextView lblLongitud = findViewById(R.id.lblLongitud);
        lblLongitud.setText(String.format("%.4f",latLng.longitude));

        mapa.addMarker(new MarkerOptions().position(latLng));

        mapa.addMarker(new MarkerOptions().position(latLng)
                .title("Marcador"));

        contador = contador + 1;
        puntos.add(latLng);
        if(contador == 4){
            PolylineOptions lineas = new PolylineOptions()
                    .add(puntos.get(0))
                    .add(puntos.get(1))
                    .add(puntos.get(2))
                    .add(puntos.get(3))
                    .add(puntos.get(0));
            lineas.width(8);
            lineas.color(Color.RED);

            mapa.addPolyline(lineas);
            contador = 0;
            puntos.clear();
        }

    }

    public void PintarRectanguloUTEQ(View view){

        mapa.addMarker(new MarkerOptions().position(
                new LatLng(-1.011888252898674, -79.47173551586393))
                .title("Punto 1"));
        mapa.addMarker(new MarkerOptions().position(
                new LatLng(-1.0130548316341261, -79.47184012201102))
                .title("Punto 2"));
        mapa.addMarker(new MarkerOptions().position(
                new LatLng(-1.0121281551986825, -79.46717704406673))
                .title("Punto 3"));
        mapa.addMarker(new MarkerOptions().position(
                new LatLng(-1.013237668145548, -79.46727628430006))
                .title("Punto 4"));



        PolylineOptions lineas = new PolylineOptions()
                .add(new LatLng(-1.011888252898674, -79.47173551586393))
                .add(new LatLng(-1.0130548316341261, -79.47184012201102))
                .add(new LatLng(-1.0121281551986825, -79.46717704406673))
                .add(new LatLng(-1.013237668145548, -79.46727628430006))
                .add(new LatLng(-1.011888252898674, -79.47173551586393));

        lineas.width(8);
        lineas.color(Color.RED);

        mapa.addPolyline(lineas);
    }
}