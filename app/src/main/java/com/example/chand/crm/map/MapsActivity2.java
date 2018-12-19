package com.example.chand.crm.map;

import android.app.Dialog;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.example.chand.crm.R;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity2 extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (googleServicesAvailable()){
            Toast.makeText(this,"Perfect!!!!",Toast.LENGTH_LONG).show();
            setContentView(R.layout.activity_maps2);
                initMap();
        }else{
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
        try {
            // Customise the styling of the base map using a JSON object defined
            // in a raw resource file.
            boolean success = googleMap.setMapStyle(
                    MapStyleOptions.loadRawResourceStyle(
                            this, R.raw.mapstyls));

            if (!success) {
                Log.e("MapsActivity2", "Style parsing failed.");
            }
        } catch (Resources.NotFoundException e) {
            Log.e("MapsActivity2", "Can't find style. Error: ", e);
        }

        // Add a marker in Sydney and move the camera
        LatLng India = new LatLng(28.6139, 77.2090);
        mMap.addMarker(new MarkerOptions().position(India).title("Marker in India"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(India));
    }
}
