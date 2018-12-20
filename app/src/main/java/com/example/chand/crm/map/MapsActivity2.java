package com.example.chand.crm.map;

import android.Manifest;
import android.app.Dialog;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import android.support.v7.widget.Toolbar;

import com.example.chand.crm.R;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity2 extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (googleServicesAvailable()){
            Toast.makeText(this,"Perfect!!!!",Toast.LENGTH_LONG).show();
            setContentView(R.layout.activity_maps2);
            Toolbar toolbar = findViewById(R.id.toolbar2);
            setSupportActionBar(toolbar);
            toolbar.setSubtitle("Your Location");
//            SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
//                    .findFragmentById(R.id.mapfragment);
//            mapFragment.getMapAsync(this);

                initMap();

            }   else    {
            //layout not supported
        }
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.

    }

    private void initMap() {
//        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.mapfragment);
//        mapFragment.getMapAsync(this);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.mapfragment);
        mapFragment.getMapAsync(this);
    }

    public boolean googleServicesAvailable(){
        GoogleApiAvailability apiAvailability = GoogleApiAvailability.getInstance();
        int isAvalable = apiAvailability.isGooglePlayServicesAvailable(this);
        if (isAvalable == ConnectionResult.SUCCESS){
            return true;
        }else if (apiAvailability.isUserResolvableError(isAvalable)){
            Dialog dialog = apiAvailability.getErrorDialog(this, isAvalable, 0);
            dialog.show();
        }else {
            Toast.makeText(this,"Cant connect to play services",Toast.LENGTH_LONG).show();
        }
            return false;
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setOnMyLocationButtonClickListener(onMyLocationButtonClickListener);
        mMap.setOnMyLocationClickListener(onMyLocationClickListner);
        enableMyLocationIfPermitted();
        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.setMinZoomPreference(11);
        try {
            // Customise the styling of the base map using a JSON object defined
            // in a raw resource file.
            boolean success = googleMap.setMapStyle(
                    MapStyleOptions.loadRawResourceStyle(
                            this, R.raw.mapstyle2));

            if (!success) {
                Log.e("MapsActivity2", "Style parsing failed.");
            }
        } catch (Resources.NotFoundException e) {
            Log.e("MapsActivity2", "Can't find style. Error: ", e);
        }




        // Add a marker in Sydney and move the camera
//        LatLng India = new LatLng(28.6139, 77.2090);
//        mMap.addMarker(new MarkerOptions().position(India).title("Marker in India"));
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(India));
    }
    private void enableMyLocationIfPermitted(){
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.ACCESS_FINE_LOCATION},
                    LOCATION_PERMISSION_REQUEST_CODE);
        } else if (mMap != null) {
            mMap.setMyLocationEnabled(true);
        }
        }
        private void showDefaultLocation(){
            Toast.makeText(this, "Location permission not granted, " +
                            "showing default location",
                    Toast.LENGTH_SHORT).show();
            LatLng India = new LatLng(28.6139, 77.2090);
            mMap.addMarker(new MarkerOptions().position(India).title("Marker in India"));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(India));
            }
            public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults){
                switch (requestCode) {
                    case LOCATION_PERMISSION_REQUEST_CODE: {
                        if (grantResults.length > 0
                                && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                            enableMyLocationIfPermitted();
                        } else {
                            showDefaultLocation();
                        }
                        return;
                    }

                }
            }
            private GoogleMap.OnMyLocationButtonClickListener onMyLocationButtonClickListener =
                    new GoogleMap.OnMyLocationButtonClickListener() {
                        @Override
                        public boolean onMyLocationButtonClick() {
                            mMap.setMinZoomPreference(15);
                            return false;
                        }
                    };
    private GoogleMap.OnMyLocationClickListener onMyLocationClickListner =
            new GoogleMap.OnMyLocationClickListener() {
                @Override
                public void onMyLocationClick(@NonNull Location location) {
                    mMap.setMinZoomPreference(12);
                    CircleOptions circleOptions = new CircleOptions();
                    circleOptions.center(new LatLng(location.getLatitude(),
                            location.getLongitude()));

                    circleOptions.radius(200);
                    circleOptions.fillColor(Color.BLUE);
                    circleOptions.strokeWidth(6);

                    mMap.addCircle(circleOptions);

                }
            };


            }

