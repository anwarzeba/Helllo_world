package com.example.dell.weather;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        mMap = mapFragment.getMap();

       mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
           @Override
           public void onMapClick(LatLng latLng) {
               MarkerOptions markerOptions = new MarkerOptions();
               markerOptions.position(latLng);
               markerOptions.title(latLng.latitude + " : " + latLng.longitude);
               mMap.clear();
               mMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
               mMap.addMarker(markerOptions);

           }
       });


//        public void onSearch(View view) {
//        EditText textlocation = (EditText) findViewById(R.id.TextFieldAddrr);
//        String location = textlocation.getText().toString();
//        List<Address> addressList = null;
//
//        if(location != null && !location.equals("")){
//            Geocoder geocoder = new Geocoder(this);
//            try {
//               addressList = geocoder.getFromLocationName(location,1);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            Address address = addressList.get(0);
//            LatLng latLng = new LatLng(address.getLatitude(),address.getLongitude());
//            mMap.addMarker(new MarkerOptions().position(latLng).title("Marker"));
//            mMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
//
//        }
    }




    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        LatLng india = new LatLng(20.5937, 78.9629);
        mMap.addMarker(new MarkerOptions().position(india).title("Marker in India"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(india));
        mMap.setMyLocationEnabled(true);


        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            mMap.setMyLocationEnabled(true);
           // mMap.addMarker(new MarkerOptions().position().title("current Location"));
        } else {
            // Show rationale and request permission.
        }




    }

}
